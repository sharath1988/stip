package edu.ucsd.som.stip.session;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.exception.SQLGrammarException;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import edu.ucsd.som.stip.entity.StipFund;
import edu.ucsd.som.stip.services.UploadRepository;

@Name("uploadExcel")
@Scope(ScopeType.CONVERSATION)
public class uploadExcel implements java.io.Serializable {

	private static final long serialVersionUID = 4485458136442501319L;

	//somdev4 created fields all are required
	@Logger private Log log;
	@In EntityManager entityManager;
	private String selectedQuarterYear;
	@In BadgIdentity identity;
	
	//document number input binding
	private String documentNumber;
	//field to determine to show the pop up if document number already exists for the selected quarter
	private boolean journalExist;
	//fields to store all the error messages
	private List<String> errorList=new ArrayList<String>();
	private boolean audits;
	private Double intRate;
	
	
	/**
	 * @param Richfaces Upload event 
	 * Uses richfaces upload event to read the uploaded excel file and to write the data into stipAmt table
	 * The STIP Amounts from GA is no longer compared against the Ledger Database.
	 */
	@SuppressWarnings("unchecked")
	public void parse(UploadEvent event) throws Exception {
		try {	
			UploadItem item = event.getUploadItem();
			log.info("FILE NAME : " + item.getFileName());
			log.info("CONTENT TYPE : " + item.getContentType());
			log.info("CONTENT File Size : " + item.getFileSize());
			ByteArrayInputStream data = new ByteArrayInputStream(item.getData());
			//checking for the sheet names
			// Qtr check is not required as it is selcted by user isValidQuarter(new ByteArrayInputStream(item.getData())) 
		    if(isValidFileName(data) && isValidColumnNames(new ByteArrayInputStream(item.getData())))
		    {
		    	//Making all the active stip funds inactive for the quarter
		    	entityManager.createQuery("delete from StipFund s where s.quarter=:qtr").setParameter("qtr",selectedQuarterYear).executeUpdate();
		    		log.info("inactivated all the previous stip funds for the quarter");
		    	
		    	ExportExcelData exportExcelData = new ExportExcelData();
		    	List<StipFund> stipFundList = exportExcelData.readExcel(new ByteArrayInputStream(item.getData()),selectedQuarterYear);
				if(stipFundList.size()>0)
				{
					for(StipFund stipFund: stipFundList)
					{
						//log.info("STIP AMT -- > " + stipFund.getQuarter()+ "  " + stipFund.getSourceFund()+ "  " 
							//		+ stipFund.getStipTotal() + "  " + stipFund.getRecipientFund());
						stipFund.setCreatedBy(identity.getAccountId());
						stipFund.setDateCreated(new Date());
						stipFund.setNotes("created from upload excel process");
						stipFund.setStatus(true);
						//setting doc num based on qrt
						int qrt=Integer.parseInt(selectedQuarterYear.substring(6,7));
						String docNum = null;
						if(qrt==1)
							docNum="09D97";
						else if(qrt==2)
							docNum="12D97";
						else if(qrt==3)
							docNum="03D97";
						else if(qrt==4)
							docNum="06D97";
						if(docNum==null)
						{
								FacesContext.getCurrentInstance().addMessage("ExcelParse", 
										new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot determine the document number. " +
																	"Please select the correct quarter year and tryagain!", null));
						}
						else
						{
							stipFund.setDocNum(docNum);
							// Commits stipAmt to the Database
							entityManager.persist(stipFund);
						}
						
					}	
					
					// update stip_amt table for LIN
					
					entityManager.createNativeQuery("call stipAmt_update('"+selectedQuarterYear+"')").executeUpdate();
				}
				
				FacesContext.getCurrentInstance().addMessage("uploadExcelForm", 
													new FacesMessage(FacesMessage.SEVERITY_INFO, "File upload Successful !", null));
				log.info("File upload Successful !");
		    	
		    }
		    else
		    {
		    	FacesContext.getCurrentInstance().addMessage("ExcelParse", 
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid file. Please review the file and make the necessary changes as suggested and submit again !", null));
		    }
		    
		    Iterator<FacesMessage> it=FacesContext.getCurrentInstance().getMessages();
		    //Clearing the previous messages
		    errorList=new ArrayList<String>();
		   while(it.hasNext())
		   {
			   FacesMessage mess= it.next();
			   if(mess.getSeverity().toString().equals("ERROR 2"))
			   {
				   errorList.add(mess.getSummary());
			   }
			   
		   }
		   if(errorList.isEmpty())
		   {
			   int selectedQuarter=Integer.parseInt(selectedQuarterYear.substring(selectedQuarterYear.length()-1, selectedQuarterYear.length()));
			   String selectedYear=selectedQuarterYear.substring(0,4);
			   
			   List<Object[]> ss=entityManager.createNativeQuery("select TABLE_NAME from information_schema.TABLES where TABLE_NAME like :tabName and TABLE_SCHEMA='stip'").
					   						   setParameter("tabName","Q"+selectedQuarter+"_"+selectedYear+"_adb" ).getResultList();
			   if(ss.isEmpty())
			   {
					   Date startTime=new Date();
					   
					 entityManager.createNativeQuery("call quarterAvgDailyBal("+selectedQuarter+","+selectedYear+")").executeUpdate();
					   Date endTime = new Date();
							   long diffMs = endTime.getTime()-startTime.getTime();
							   long diffSec = diffMs / 1000;
							   long min = diffSec / 60;
							   long sec = diffSec % 60;
						  System.out.println("Time call quarterAvgDailyBal("+selectedQuarter+","+selectedYear+") --- >" +min+" minutes and "+sec+" seconds.");
		
					   startTime=new Date();
					   entityManager.createNativeQuery(" call checkQtrAdb("+selectedQuarter+","+selectedYear+")").executeUpdate();
					   endTime= new Date();;
						   diffMs = endTime.getTime()-startTime.getTime();
						   diffSec = diffMs / 1000;
						   min = diffSec / 60;
						   sec = diffSec % 60;
						   System.out.println("Time call checkQtrAdb("+selectedQuarter+","+selectedYear+") --- >" +min+" minutes and "+sec+" seconds.");
						   String adbErrorTable="Errors_Q"+selectedQuarter+"_"+selectedYear+"_adb";
					   	int i=entityManager.createNativeQuery(" select * from "+adbErrorTable).getResultList().size();
					    System.out.println("errors-->"+i);
					    if(i>0)
					    {
					    	//TODO errors in adb error table notify lin
					    	/*errorList.add("Cannot perform stip process as there are Errors in adb tables.Contact BADG with following message"
					    					+ " please check "+adbErrorTable+" table in Data Base.");*/
					    	log.info(adbErrorTable, "Error!! please check "+adbErrorTable+" table in Data Base.");
					    }
			   }
			   
		   if(errorList.isEmpty())
		   {
			  this.setAudits(true);
		   }
		   }
		   
	} catch (Exception e) {
		log.error("Error in listener method in ExcelUploadController ", e);
		log.error( "There is an error while uploading the Excel. Please verify the format.If the error persists, contact BADG.");
	} finally {
	}
}
	
	/**
	 * @param byteArrayInputStream input byte stream from file
	 * @return boolean if the file is valid or not
	 * @throws IOException when the input stream cannot be read
	 * The file should contain 3 sheets with MEDGRP,HS,MCARE names
	 */
	private boolean isValidFileName(ByteArrayInputStream byteArrayInputStream) throws IOException {
        boolean isvalid=false;
 
        try {
             
      boolean isCountOk=false;
      boolean isMedGroupSheetPresent=false;
      boolean isHSSheetPresent=false;
      boolean isMCARESheetPresent=false;
          XSSFWorkbook wb = new XSSFWorkbook(byteArrayInputStream);
          if(wb.getNumberOfSheets()!=3)
          {
        	  FacesContext.getCurrentInstance().addMessage("isValidFile", 
						new FacesMessage(FacesMessage.SEVERITY_ERROR, 
						"Invalid number of sheets there should be 3 sheets with names MEDGRP,HS,MCARE respectively in the work book", null));
          }
          else
          {
        	  isCountOk=true;
          }
          Iterator<XSSFSheet> sheet= wb.iterator();
          while(sheet.hasNext())
          {
        	  XSSFSheet s=sheet.next();
        	  if(s.getSheetName().equals("MEDGRP"))
        	  {
        		  isMedGroupSheetPresent=true;
        	  }
        	  else if(s.getSheetName().equals("HS"))
        	  {
        		  isHSSheetPresent=true;
        	  }
        	  else if(s.getSheetName().equals("MCARE"))
        	  {
        		  isMCARESheetPresent=true;
        	  }
          }
          
          
          if(!isMedGroupSheetPresent)
          {
        	  FacesContext.getCurrentInstance().addMessage("isValidFile", 
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "MEDGRP sheet is not present", null));
          }
          
          if(!isHSSheetPresent)
          {
        	  FacesContext.getCurrentInstance().addMessage("isValidFile", 
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "HS sheet is not present", null));
          }
          
          if(!isMCARESheetPresent)
          {
        	  FacesContext.getCurrentInstance().addMessage("isValidFile", 
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "MCARE sheet is not present", null));
          }
          
          
          if(isCountOk && isMedGroupSheetPresent && isHSSheetPresent && isMCARESheetPresent)
          {
        	  isvalid=true;
          }

        }
        catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
        }
          return isvalid;
	}
	
	/**
	 * @param byteArrayInputStream input byte stream from file
	 * @return boolean if the file has the valid month/year mentioned in each sheets
	 * validating the quarter from the first row of each excel sheet
	 * not used as the user will select the quarter to process
	 */
	@SuppressWarnings("unused")
	private boolean isValidQuarter(ByteArrayInputStream data) 
	{
		boolean isValidQuarter=false;
	 try{
		XSSFWorkbook wb = new XSSFWorkbook(data);
		
		Iterator<XSSFSheet> sheets=wb.iterator();
		while(sheets.hasNext())
		{
			XSSFSheet sheet=sheets.next();
			Row firstRow=sheet.getRow(0);
			Cell firstCell=firstRow.getCell(0);
			if(firstCell.getCellType() == Cell.CELL_TYPE_STRING){
		  		String cellString = firstCell.getRichStringCellValue().getString();
		  		//checking the months mentioned are valid
		  		if(cellString.contains("Jul") && cellString.contains("Aug") && cellString.contains("Sep"))
		  		{
		  			isValidQuarter=true;
		  		}
		  		else if(cellString.contains("Oct") && cellString.contains("Nov") && cellString.contains("Dec"))
		  		{
		  			isValidQuarter=true;
		  		}
		  		else if(cellString.contains("Jan") && cellString.contains("Feb") && cellString.contains("Mar"))
		  		{
		  			isValidQuarter=true;
		  		}
		  		else if(cellString.contains("Apr") && cellString.contains("May") && cellString.contains("Jun"))
		  		{
		  			isValidQuarter=true;
		  		}
		  		else
		  		{
		  			FacesContext.getCurrentInstance().addMessage("isValidQuarter", 
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Quarter mentioned in the sheet "+sheet.getSheetName().trim()+" is not valid." +
									"Please correct the first row in the sheet and try again.It should be in the following format ' " +
									" STIP Distributions By Recipient VC for Run 303: Oct, Nov, Dec FY 18/19' ", null));
		  			isValidQuarter=false;
		  		}
		  		if(isValidQuarter)
		  		{
		  			//Checking if the year mentioned in valid.
		  			String currFiscYear=cellString.trim().substring(cellString.trim().trim().length()-2, cellString.trim().trim().length());
		  			String prevFiscYear=cellString.trim().substring(cellString.trim().trim().length()-5, cellString.trim().trim().length()-3);
		  			int fiscYr=Integer.parseInt(currFiscYear);
		  			int prevYr=Integer.parseInt(prevFiscYear);
		  			
		  			SimpleDateFormat sdf = new SimpleDateFormat("yy"); // Just the year, with 2 digits
		  			String formattedDate = sdf.format(Calendar.getInstance().getTime());
		  			int curYear=Integer.parseInt(formattedDate);
		  			
		  			if(fiscYr-prevYr!=1)
		  			{
		  				FacesContext.getCurrentInstance().addMessage("isValidQuarter", 
								new FacesMessage(FacesMessage.SEVERITY_ERROR, "Years difference should be one (eg:18/19) in the sheet "+sheet.getSheetName().trim()+" is not valid." +
										"Please correct the first row in the sheet and try again.It should be in the following format'" +
										" STIP Distributions By Recipient VC for Run 303: Oct, Nov, Dec FY 18/19; ", null));
		  				isValidQuarter=false;
		  			}
		  			
		  			if(fiscYr!=curYear+1 && fiscYr!=curYear)
		  			{
		  				FacesContext.getCurrentInstance().addMessage("isValidQuarter", 
								new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fiscal year cannot be less than current calender year "+sheet.getSheetName().trim()+" is not valid." +
										"Please correct the first row in the sheet and try again.It should be in the following format " +
										" 'STIP Distributions By Recipient VC for Run 303: Oct, Nov, Dec FY 18/19' ", null));
		  				isValidQuarter=false;
		  			}
		  			
		  		}
		  		
			}
			else
			{
				FacesContext.getCurrentInstance().addMessage("isValidQuarter", 
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "The first cell in the first row of the sheet"+sheet.getSheetName().trim()+
																	  " should be a text with quaters in the following format " +
																	  " 'STIP Distributions By Recipient VC for Run 303: Oct, Nov, Dec FY 18/19' ", null));
				isValidQuarter=false;
			}
			
		}
		}catch(IOException ex)
		{
			FacesContext.getCurrentInstance().addMessage("isValidQuarter", 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error file upload while reading the input file", null));
			ex.printStackTrace();
		}catch(NumberFormatException ex)
		{
			FacesContext.getCurrentInstance().addMessage("isValidQuarter", 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error file upload while converting number", null));
			ex.printStackTrace();
		}
		catch(Exception ex)
		{
			FacesContext.getCurrentInstance().addMessage("isValidQuarter", 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error file upload ", null));
			ex.printStackTrace();
		}
		
		return isValidQuarter;
	}
	
	
	/**
	 * @param byteArrayInputStream input byte stream from file
	 * @return boolean if the file has the valid column names in each sheets
	 * validating the column names each excel sheet
	 */
	private boolean isValidColumnNames(ByteArrayInputStream data) 
	{
	 boolean isValidColumns = false;
	 boolean isValidMedGrpSheetCols=false;
	 boolean isValidHSSheetCols=false;
	 boolean isValidMcareSheetCols=false;
	 try{
		XSSFWorkbook wb = new XSSFWorkbook(data);
		Iterator<XSSFSheet> sheets=wb.iterator();
		while(sheets.hasNext())
		{
			 boolean isvalidFirstColumn = false,isvalidFourthColumn = false,isvalidFifthColumn = false,isvalidSixthColumn = false,
	 			isvalidSeventhColumn = false,isvalidEighthColumn = false,isvalidNinthColumn = false,
	 			isvalidTenthColumn = false,isvalidEleventhColumn=false;
			XSSFSheet sheet=sheets.next();
			
			Row thirdRow=sheet.getRow(2);
			Cell firstCell=thirdRow.getCell(0);
			Cell fourthCell=thirdRow.getCell(3);
			Cell fifthCell=thirdRow.getCell(4);
			Cell sixthCell=thirdRow.getCell(5);
			Cell seventhCell=thirdRow.getCell(6);
			Cell eighthCell=thirdRow.getCell(7);
			Cell ninthCell=thirdRow.getCell(8);
			Cell tenthCell=thirdRow.getCell(9);
			Cell eleventhCell=thirdRow.getCell(10);
			if(firstCell.getCellType() == Cell.CELL_TYPE_STRING && firstCell.getRichStringCellValue().getString().trim().equals("Recipient Fund"))
			{
				isvalidFirstColumn=true;
			}
			else
			{
				FacesContext.getCurrentInstance().addMessage("isValidColumnNames", 
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "First cell name is invalid it should be Recipient Fund", null));
			}
			
			
			if(fourthCell.getCellType() == Cell.CELL_TYPE_STRING && fourthCell.getRichStringCellValue().getString().trim().equals("Recipient Fund Title"))
			{
				isvalidFourthColumn=true;
			}
			else
			{
				FacesContext.getCurrentInstance().addMessage("isValidColumnNames", 
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fourth cell name is invalid it should be Recipient Fund Title", null));
			}
			
			
			
			if(fifthCell.getCellType() == Cell.CELL_TYPE_STRING && fifthCell.getRichStringCellValue().getString().trim().equals("Source Fund"))
			{
				isvalidFifthColumn=true;
			}
			else
			{
				FacesContext.getCurrentInstance().addMessage("isValidColumnNames", 
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fifth cell name is invalid it should be Source Fund", null));
			}
			
			if(sixthCell.getCellType() == Cell.CELL_TYPE_STRING && sixthCell.getRichStringCellValue().getString().trim().equals("G/I"))
			{
				isvalidSixthColumn=true;
			}
			else
			{
				FacesContext.getCurrentInstance().addMessage("isValidColumnNames", 
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sixth cell name is invalid it should be G/I", null));
			}
			
			if(seventhCell.getCellType() == Cell.CELL_TYPE_STRING && seventhCell.getRichStringCellValue().getString().trim().equals("Group Fund"))
			{
				isvalidSeventhColumn=true;
			}
			else
			{
				FacesContext.getCurrentInstance().addMessage("isValidColumnNames", 
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seventh cell name is invalid it should be Group Fund", null));
			}
			
			if(eighthCell.getCellType() == Cell.CELL_TYPE_STRING && eighthCell.getRichStringCellValue().getString().trim().equals("Stip Total"))
			{
				isvalidEighthColumn=true;
			}
			else
			{
				FacesContext.getCurrentInstance().addMessage("isValidColumnNames", 
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Eight cell name is invalid it should be Stip Total", null));
			}
			
			if(ninthCell.getCellType() == Cell.CELL_TYPE_STRING)
			{  
				if(sheet.getSheetName().trim().equals("MEDGRP"))
				{
					if(ninthCell.getRichStringCellValue().getString().trim().equals("Fund 60100"))
					{
						isvalidNinthColumn=true;
					}
					else
					{
						FacesContext.getCurrentInstance().addMessage("isValidColumnNames", 
								new FacesMessage(FacesMessage.SEVERITY_ERROR, "ninth cell of sheet MEDGRP is invalid it should be Fund 60100", null));
					}
				}
				else if(sheet.getSheetName().trim().equals("HS"))
				{
					if(ninthCell.getRichStringCellValue().getString().trim().equals("Fund 69991"))
					{
						isvalidNinthColumn=true;
					}
					else
					{
						FacesContext.getCurrentInstance().addMessage("isValidColumnNames", 
								new FacesMessage(FacesMessage.SEVERITY_ERROR, "ninth cell of sheet HS is invalid it should be Fund 69991", null));
					}
				}
				
				else if(sheet.getSheetName().trim().equals("MCARE"))
				{
					if(ninthCell.getRichStringCellValue().getString().trim().equals("Fund 60250"))
					{
						isvalidNinthColumn=true;
					}
					else
					{
						FacesContext.getCurrentInstance().addMessage("isValidColumnNames", 
								new FacesMessage(FacesMessage.SEVERITY_ERROR, "ninth cell name of sheet MCARE is invalid it should be Fund 60250", null));
					}
				}
				
				else
					
				{
					FacesContext.getCurrentInstance().addMessage("isValidColumnNames", 
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Sheet Name", null));
				}
				
			}
			else
			{
				FacesContext.getCurrentInstance().addMessage("isValidColumnNames", 
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "ninth cell name is invalid", null));
			}
			
			if(tenthCell.getCellType() == Cell.CELL_TYPE_STRING && tenthCell.getRichStringCellValue().getString().trim().equals("Other"))
			{
				isvalidTenthColumn=true;
			}
			else
			{
				FacesContext.getCurrentInstance().addMessage("isValidColumnNames", 
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "tenth cell name is invalid it should be Other", null));
			}
			
			if(eleventhCell.getCellType() == Cell.CELL_TYPE_STRING && eleventhCell.getRichStringCellValue().getString().trim().equals("Cap Amount"))
			{
				isvalidEleventhColumn=true;
			}
			else
			{
				FacesContext.getCurrentInstance().addMessage("isValidColumnNames", 
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Eleventh cell name is invalid it should be Cap Amount", null));
			}
			
			if(sheet.getSheetName().trim().equals("MEDGRP"))
			{
				if(isvalidFirstColumn&&isvalidFourthColumn&&isvalidFifthColumn&&isvalidSixthColumn&&
	 			isvalidSeventhColumn&&isvalidEighthColumn&&isvalidNinthColumn&&
	 			isvalidTenthColumn&&isvalidEleventhColumn)
				{
					 isValidMedGrpSheetCols=true;
				}
				else
				{
					FacesContext.getCurrentInstance().addMessage("isValidColumnNames", 
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong column name in sheet MEDGRP.Please correct and try again", null));
				}
			}
			else if(sheet.getSheetName().trim().equals("HS"))
			{
				if(isvalidFirstColumn&&isvalidFourthColumn&&isvalidFifthColumn&&isvalidSixthColumn&&
			 			isvalidSeventhColumn&&isvalidEighthColumn&&isvalidNinthColumn&&
			 			isvalidTenthColumn&&isvalidEleventhColumn)
						{
							isValidHSSheetCols=true;
						}
				else
				{
					FacesContext.getCurrentInstance().addMessage("isValidColumnNames", 
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong column name in sheet HS.Please correct and try again", null));
				}
			}
			else if(sheet.getSheetName().trim().equals("MCARE"))
			{
				if(isvalidFirstColumn&&isvalidFourthColumn&&isvalidFifthColumn&&isvalidSixthColumn&&
			 			isvalidSeventhColumn&&isvalidEighthColumn&&isvalidNinthColumn&&
			 			isvalidTenthColumn&&isvalidEleventhColumn)
						{
							isValidMcareSheetCols=true;
						}
				else
				{
					FacesContext.getCurrentInstance().addMessage("isValidColumnNames", 
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong column name in sheet MCARE.Please correct and try again", null));
				}
			}
			else
			{
				FacesContext.getCurrentInstance().addMessage("isValidColumnNames", 
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sheet Name not valid", null));
			}
			
		}
		
		

		if(isValidMedGrpSheetCols &&  isValidHSSheetCols && isValidMcareSheetCols)
		{
			isValidColumns=true;
		}
		else
		{
			FacesContext.getCurrentInstance().addMessage("isValidColumnNames", 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid column names in the sheets ", null));
		}
		
		
			
	 }catch(IOException ex)
		{
			FacesContext.getCurrentInstance().addMessage("isValidColumnNames", 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error file upload while reading the input file", null));
			ex.printStackTrace();
		}catch(NumberFormatException ex)
		{
			FacesContext.getCurrentInstance().addMessage("isValidColumnNames", 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error file upload while converting number", null));
			ex.printStackTrace();
		}
		catch(Exception ex)
		{
			FacesContext.getCurrentInstance().addMessage("isValidColumnNames", 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error file upload ", null));
			ex.printStackTrace();
		}
		
		return isValidColumns;
	}
	
	
	/**
	 * @return none
	 * will assigns all the error messages to uploadExcelForm:upload as the faces messages are lost for each ajax request
	 * adding all the errors to error list and finally assigning the errors to the form element to display
	 */
	public String loadInfoFileHook( )  
	 {  
		String returnStr=null;
		log.info("inside loadinfo file hook");
		 Iterator<FacesMessage> it=FacesContext.getCurrentInstance().getMessages();
		 while(it.hasNext())
		   {
			   FacesMessage mess= it.next();
			   if(mess.getSeverity().toString().equals("ERROR 2"))
			   {
				   errorList.add(mess.getSummary());
			   }
			   
		   }
		 for(String error:errorList)
		 reportMessageToMessageHandler(FacesMessage.SEVERITY_ERROR, error, error, "uploadExcelForm:upload");  
		 if(this.getSelectedQuarterYear()==null || this.getIntRate()==null)
			 reportMessageToMessageHandler(FacesMessage.SEVERITY_ERROR, "invalid year/int rate", "invalid year/int rate", "uploadExcelForm:upload"); 
   		 if(this.getSelectedQuarterYear()!=null && this.getIntRate()!=null && errorList.isEmpty())
			 return  "/admin/views/audits.xhtml?quarterYear="+this.getSelectedQuarterYear()+"&intRate="+this.getIntRate();
		 else
			 return returnStr;
	 }
	
	/**
	 * @return none
	 * Adding faces messages to components
	 */
	public void reportMessageToMessageHandler(FacesMessage.Severity severity, String summary, String detail, String componentID)  
	 {  
	 FacesContext context = FacesContext.getCurrentInstance();  
	 FacesMessage message = new FacesMessage(severity, summary, detail);  
	 context.addMessage(componentID, message);  
	 } 
	
	/**
	 * @param None.
	 * @return Previous four quarters for which the STIP process can be done.
	 */
	@SuppressWarnings("unchecked")
	public List<String> getPrevFourQuarters()
	{
		List<String> returnList=new ArrayList<String>();
		//List<String> finalReturnList=new ArrayList<String>();
		try
		{
		
		//current fiscal year from dtl trans
		Integer fisicalYr=(Integer)entityManager.createNativeQuery("select max(accountingPeriod) from Ledger.dtlTrans").getSingleResult();
		Integer currentFiscalyear=Integer.parseInt(String.valueOf(fisicalYr).substring(0,4));
		//current fiscal month
		Integer currentFiscalmonth=Integer.parseInt(String.valueOf(fisicalYr).substring(4,6));
		//If current fisicalPeriod is in Q1 
		
		if(currentFiscalmonth==0 || currentFiscalmonth==1 || currentFiscalmonth==2 || currentFiscalmonth==3)
		{
			returnList.add(String.valueOf(currentFiscalyear-1).concat("_Q1"));
			returnList.add(String.valueOf(currentFiscalyear-1).concat("_Q2"));
			returnList.add(String.valueOf(currentFiscalyear-1).concat("_Q3"));
			returnList.add(String.valueOf(currentFiscalyear-1).concat("_Q4"));
		}
		//If current fisicalPeriod is in Q2
		else if(currentFiscalmonth==4 || currentFiscalmonth==5 || currentFiscalmonth==6)
		{
			returnList.add(String.valueOf(currentFiscalyear).concat("_Q1"));
			returnList.add(String.valueOf(currentFiscalyear-1).concat("_Q4"));
			returnList.add(String.valueOf(currentFiscalyear-1).concat("_Q3"));
			returnList.add(String.valueOf(currentFiscalyear-1).concat("_Q2"));
			
		}
		
		//If current fisicalPeriod is in Q3
		else if(currentFiscalmonth==7 || currentFiscalmonth==8 || currentFiscalmonth==9)
		{
			returnList.add(String.valueOf(currentFiscalyear).concat("_Q2"));
			returnList.add(String.valueOf(currentFiscalyear).concat("_Q1"));
			returnList.add(String.valueOf(currentFiscalyear-1).concat("_Q4"));
			returnList.add(String.valueOf(currentFiscalyear-1).concat("_Q3"));
			
		}
		
		//If current fisicalPeriod is in Q4
		else if(currentFiscalmonth==10 || currentFiscalmonth==11 || currentFiscalmonth==12 || currentFiscalmonth==13 || currentFiscalmonth==14 )
		{
			returnList.add(String.valueOf(currentFiscalyear).concat("_Q3"));
			returnList.add(String.valueOf(currentFiscalyear).concat("_Q2"));
			returnList.add(String.valueOf(currentFiscalyear).concat("_Q1"));
			returnList.add(String.valueOf(currentFiscalyear-1).concat("_Q4"));
		}
		//Error
		else
		{
			FacesContext.getCurrentInstance().addMessage("getPrevFourQuarters",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot determine quarter! Please check with BADG", null));
		}
		
		} catch(Exception e)
		{
			FacesContext.getCurrentInstance().addMessage("getPrevFourQuarters",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "error occured in getPrevFourQuarters()! Please check with BADG", null));
			e.printStackTrace();
			log.error("getPrevFourQuarters", e.getMessage());
		}
		//TODO remove test qtr from list
		returnList.add("2014_Q1");
		
		//check if the return quarters for which the journals are already created and not reversed and remove those quarters
		
		List<String> notReversedList=(List<String>)entityManager.createNativeQuery(UploadRepository.quarterListNotRev)
												.setParameter("inList",returnList).getResultList();
		returnList.removeAll(notReversedList);
		return returnList;
	}
	
	
	/**
	 * @param None.
	 * @return None.
	 * If document number exists for the quarter showing the alert
	 */
	@SuppressWarnings("unchecked")
	public void checkJournalAlreayExist()
	{
		try{
			String nonSingleJournal="stip"+this.selectedQuarterYear;
			String singleJournal="stipsingle"+this.selectedQuarterYear;
			List<Object[]> journalsForQtrWithDocNum=new ArrayList<Object[]>();
			BigInteger i =(BigInteger)entityManager.createNativeQuery("select  exists (select * from stip"+this.getSelectedQuarterYear()+" where status=1)").getSingleResult();
			BigInteger j =(BigInteger)entityManager.createNativeQuery("select  exists (select * from stipsingle"+this.getSelectedQuarterYear()+" where status=1)").getSingleResult();
			if(i.intValue()==1 && j.intValue() ==1)
			{
				journalsForQtrWithDocNum=entityManager.createNativeQuery("select * from "+nonSingleJournal+" where status=1 union select * from "+singleJournal+" where status=1").getResultList();
				if(journalsForQtrWithDocNum.size()>0)
				{
					this.journalExist=true;
				}
				else
					this.journalExist=false;
			}
		}catch(SQLGrammarException e)
		{
			log.info("no journals created yet");
		}
		catch(Exception e)
		{
			log.info("no journals created yet");
		}
		
	}
	
	public String getSelectedQuarterYear() {
		return selectedQuarterYear;
	}
	public void setSelectedQuarterYear(String selectedQuarterYear) {
		this.selectedQuarterYear = selectedQuarterYear;
	}
	
	public List<String> getErrorList() {
		return errorList;
	}
	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	public boolean isJournalExist() {
		return journalExist;
	}
	public void setJournalExist(boolean journalExist) {
		this.journalExist = journalExist;
	}

	public boolean isAudits() {
		return audits;
	}

	public void setAudits(boolean audits) {
		this.audits = audits;
	}

	public Double getIntRate() {
		return intRate;
	}

	public void setIntRate(Double intRate) {
		this.intRate = intRate;
	}

}
