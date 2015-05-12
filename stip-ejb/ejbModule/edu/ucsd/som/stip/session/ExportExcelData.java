package edu.ucsd.som.stip.session;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Scope;

import edu.ucsd.som.stip.entity.StipFund;

/**
 * @author somdev2/somdev4
 *
 */
@Scope(ScopeType.CONVERSATION)
public class ExportExcelData {
		
	/**
	 * @param file
	 * @throws IOException
	 * Condition to check mal-formed Excel and show message on UI to correct the Excel and Upload by User
	 */
	public List<StipFund> readExcel(InputStream file,String selectedQuarterYear) throws IOException{				
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet sheetMedGrp = wb.getSheet("MEDGRP");
		XSSFSheet sheetHS = wb.getSheet("HS");
		//Not required
		//XSSFSheet sheetMCARE = wb.getSheet("MCARE");
		List<StipFund> stipFundList = new ArrayList<StipFund>();
		parseSheet(sheetMedGrp, stipFundList,selectedQuarterYear);		
		parseSheet(sheetHS, stipFundList,selectedQuarterYear);		
		//parseSheet(sheetMCARE, stipFundList,selectedQuarterYear);		
		return stipFundList;
	}	
	
	/**
	 * @param sheet
	 * @param stipAmtList
	 * @param selectedQuarterYear
	 * 
	 */
	public void parseSheet(XSSFSheet sheet, List<StipFund> stipFundList,String selectedQuarterYear){
		System.getProperty("sun.arch.data.model");
		for (Row row : sheet) {						
			StipFund stipFundObj = new StipFund();
			boolean cellFund60100 = false;	
			boolean cellFund69991 = false;
			//boolean cellFund60250 = false;
			for (Cell cell : row) {		    	  				  
				  stipFundObj.setQuarter(selectedQuarterYear);
				  				  
				  CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());				  			 
		    	  if(cellRef.formatAsString().charAt(0) == 'A' || cellRef.formatAsString().charAt(0) == 'B' 
		    			  							|| cellRef.formatAsString().charAt(0) == 'C'){ 
		    		  	if(cell.getCellType() == Cell.CELL_TYPE_STRING){
		    		  		String cellString = cell.getRichStringCellValue().getString();
		    		  		boolean isFund = Character.isDigit(cellString.charAt(0));		    		  				    		  		
		    		  		if(isFund){		    		  					    		  			
		    		  			String cellString_A = cellString.trim();
		    		  			stipFundObj.setRecipientFund(cellString_A + "A");
		    		  			//Check if the Fund is 60100A or 69991A. If is it is should read from Column
		    		  			if( cellString.trim().equals("60100")){
		    		  				cellFund60100 = true;
		    		  			} else if ( cellString.trim().equals("69991")){
		    		  				cellFund69991 = true;
		    		  			}/*else if ( cellString.trim().equals("60250")){
		    		  				cellFund60250 = true;
		    		  			}*/
		    		  		} else {		    		  					    		  			
		    		  		}
		    		  	}		    		  	
		    	  } else if(cellRef.formatAsString().charAt(0) == 'E' ) {
		    		  if(cell.getCellType() == Cell.CELL_TYPE_STRING){		    			  
		    			  stipFundObj.setSourceFund(cell.getRichStringCellValue().getString() +"A");
		    		  }
		    	  } else if(cellRef.formatAsString().charAt(0) == 'H')	{  		
		    		  //&& !cellFund60250
		    		  if( ! cellFund60100 && ! cellFund69991 )
		    		  {
			    		  if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA){
			    		  	 	double stipAmt = cell.getNumericCellValue();		    			  		    		  			    		  		
			    		  		stipFundObj.setStipTotal(BigDecimal.valueOf(stipAmt).setScale(2, RoundingMode.CEILING));		    		  		
			    		  }
		    		  }
		    	 } else if (cellRef.formatAsString().charAt(0) == 'I'){		    		 
		    		 //Only for 60100 and 69991 Funds set Amounts from this Column
		    		 //|| cellFund60250
		    		 if( cellFund60100  || cellFund69991 ){
		    			 if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA){
		    				 //System.out.println(" 60100 A FUND "); 
		    				 double stipAmt = cell.getNumericCellValue();		  
		    				 stipFundObj.setStipTotal(BigDecimal.valueOf(stipAmt).setScale(2, RoundingMode.CEILING));
		    			 }		    			 
		    		 }
		    		
		    	 }
		    	  
			}		
			if(stipFundObj.getRecipientFund() != null)	{
				stipFundList.add(stipFundObj);
			}
		 }				
							
	}
}

