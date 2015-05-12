package edu.ucsd.som.stip.session;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

import edu.ucsd.som.stip.beans.NonSingleJournal;
import edu.ucsd.som.stip.beans.SingleJournal;
import edu.ucsd.som.stip.util.DataScrollerSummary;

@Name("summary")
@Scope(ScopeType.CONVERSATION)
public class JournalSummary implements java.io.Serializable {


	private static final long serialVersionUID = -4519774367501417860L;
	@Logger private Log log;
	@In EntityManager entityManager;
	@In BadgIdentity identity;
	@In(create=true) DataScrollerSummary dataScrollerSummary;
	private String quarterYear;
	private List<NonSingleJournal> nonSingleJournals;
	private List<SingleJournal> singleJournals;
	private final String SEPARATOR = "\t";
	private final String END_OF_LINE = "\r\n";
	private static final String EMPTY_STRING = "";
	
	@SuppressWarnings("unchecked")
	public void init()
	{
		try{
			
			if(quarterYear!=null && !quarterYear.isEmpty())
			{
				 nonSingleJournals=new ArrayList<NonSingleJournal>();
				 singleJournals=new ArrayList<SingleJournal>();
				String nonSingleJournalname="stip"+quarterYear;
				String singleJournalname="stipsingle"+quarterYear;
				List<Object[]> nonSingleJournalsForQtr=(List<Object[]>)entityManager.createNativeQuery("select * from "+nonSingleJournalname+" where status=1").getResultList();
				List<Object[]> singleJournalsForQtr=(List<Object[]>)entityManager.createNativeQuery("select * from "+singleJournalname+" where status=1").getResultList();
				//+ "	 order by transaction_description,debit_credit,transaction_amount,index_code,fund_code"
				for(Object[] o:nonSingleJournalsForQtr)
				{
					NonSingleJournal nsj=new NonSingleJournal(o);
					nonSingleJournals.add(nsj);
				}
				
				for(Object[] o:singleJournalsForQtr)
				{
					SingleJournal sj=new SingleJournal(o);
					singleJournals.add(sj);
				}
				
			}
			if(nonSingleJournals!=null && singleJournals!=null && nonSingleJournals.size()==0 && singleJournals.size()==0)
			{
				FacesContext.getCurrentInstance().addMessage("init", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Quarter or no journals found. Please enter in the following format - 2014_Q1", null));
			}
		}
		catch(PersistenceException e)
		{
			FacesContext.getCurrentInstance().addMessage("init", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Quarter or no journals found. Please enter in the following format - 2014_Q1", null));
			log.info("error in init()");
		}
		catch(Exception e)
		{
			log.info("error in init()");
			e.printStackTrace();
		}
	}
	
	
	public void textNonSingleJournals() throws IOException
	  {
		  File temp = File.createTempFile("journal_non_single"+this.getQuarterYear(), ".txt"); 
		  BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
		  
		  List<List<String>> matrix = new ArrayList<List<String>>();
		  for(NonSingleJournal idSingle: nonSingleJournals)
		  {
			List<String> row=new ArrayList<String>();
					
					row.add(idSingle.getSequenceNumber().toString());
					row.add(idSingle.getJournalType());
					row.add(idSingle.getTransactionDescription());
					row.add(idSingle.getDocumentReferenceNo());
					row.add(BigDecimal.valueOf(idSingle.getTransactionAmount()).setScale(2,RoundingMode.HALF_UP).toString());
					row.add(idSingle.getDebitCredit());
					row.add(idSingle.getCoaCode());
					row.add(idSingle.getIndexCode());
					
					//row.add(idSingle.getFundCode());
					//row.add(idSingle.getOrganizationCode());
					row.add(EMPTY_STRING);
					row.add(EMPTY_STRING);
					
					row.add(idSingle.getAccountCode());
					
					//row.add(idSingle.getProgramCode());
					//row.add(idSingle.getActivityCode());
					//row.add(idSingle.getLocationCode());
					//row.add(idSingle.getEncumbranceNumber());
					//row.add(idSingle.getEncumbranceDocType());
					//row.add(idSingle.getEncumbranceItemNumber());
					//row.add(idSingle.getEncumbranceItemSeqNo());
					
					row.add(EMPTY_STRING);
					row.add(EMPTY_STRING);
					row.add(EMPTY_STRING);
					row.add(EMPTY_STRING);
					row.add(EMPTY_STRING);
					row.add(EMPTY_STRING);
					row.add(EMPTY_STRING);
					
					
					matrix.add(row);
				 
		  }
		  StringBuilder sb = new StringBuilder();
		  
		 // sb.append("seq No\tJournal Type\tTransaction Description\tDoc Ref No\tAmount\t\t\tDebit/credit\tCOA Code\tIndex\tFund\tAccount");
		 // sb.append(END_OF_LINE);
		  
		    for (int i = 0; i < matrix.size(); i++) {
		        for (int o = 0; o < matrix.get(i).size(); o++) {
		            sb.append(matrix.get(i).get(o));
		            if (o <( matrix.get(i).size()-1))
		                sb.append(SEPARATOR);
		            else
		                sb.append(END_OF_LINE);
		        }
		    }
		  
		    String reversed=  matrix.get(0).toString().contains("REVERSED") ? "_REVERSED" : "";
		    
		  bw.write(sb.toString());
		  bw.close();
			//
			HttpServletResponse response  = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("fileName", "FILE_NAME");
			response.setContentType(URLConnection.guessContentTypeFromName(temp.getName()));
	        response.addHeader("Content-disposition", "attachment; filename=\"" + "journal_non_single"+this.getQuarterYear()+reversed+"\"");
			ServletOutputStream out = response.getOutputStream();
			
			byte[] fileInBytes = new byte[(int)temp.length()]; 	
			
			FileInputStream fileInputStream = new FileInputStream(temp);
	    	fileInputStream.read(fileInBytes);
	    	fileInputStream.close();
	            	
			out.write(fileInBytes);
			out.flush();
			out.close();
			response.flushBuffer();
			FacesContext.getCurrentInstance().responseComplete();
			
	  }
		
	  public void textSingleJournals() throws IOException
	  {
		  File temp = File.createTempFile("journal_single"+this.getQuarterYear(), ".txt"); 
		  BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
		  
		  List<List<String>> matrix = new ArrayList<List<String>>();
		  for(SingleJournal idSingle: singleJournals)
		  {
			List<String> row=new ArrayList<String>();
					
					row.add(idSingle.getSequenceNumber().toString());
					row.add(idSingle.getJournalType());
					row.add(idSingle.getTransactionDescription());
					row.add(idSingle.getDocumentReferenceNo());
					row.add(BigDecimal.valueOf(idSingle.getTransactionAmount()).setScale(2,RoundingMode.HALF_UP).toString());
					row.add(idSingle.getDebitCredit());
					row.add(idSingle.getCoaCode());
					row.add(idSingle.getIndexCode());
					
					//row.add(idSingle.getFundCode());
					//row.add(idSingle.getOrganizationCode());
					row.add(EMPTY_STRING);
					row.add(EMPTY_STRING);
					
					row.add(idSingle.getAccountCode());
					
					//row.add(idSingle.getProgramCode());
					//row.add(idSingle.getActivityCode());
					//row.add(idSingle.getLocationCode());
					//row.add(idSingle.getEncumbranceNumber());
					//row.add(idSingle.getEncumbranceDocType());
					//row.add(idSingle.getEncumbranceItemNumber());
					//row.add(idSingle.getEncumbranceItemSeqNo());
					
					row.add(EMPTY_STRING);
					row.add(EMPTY_STRING);
					row.add(EMPTY_STRING);
					row.add(EMPTY_STRING);
					row.add(EMPTY_STRING);
					row.add(EMPTY_STRING);
					row.add(EMPTY_STRING);
					
					matrix.add(row);
				 
		  }
		  StringBuilder sb = new StringBuilder();
		  
		  //sb.append("seq No\tJournal Type\tTransaction Description\tDoc Ref No\tAmount\tDebit/credit\tCOA Code\tIndex\tFund\tAccount");
		  //sb.append(END_OF_LINE);
		  
		    for (int i = 0; i < matrix.size(); i++) {
		        for (int o = 0; o < matrix.get(i).size(); o++) {
		            sb.append(matrix.get(i).get(o));
		            if (o <( matrix.get(i).size()-1))
		                sb.append(SEPARATOR);
		            else
		            	  sb.append(END_OF_LINE);
		        }
		    }
		  
		  String reversed=  matrix.get(0).toString().contains("REVERSED") ? "_REVERSED" : "";
		  
		  bw.write(sb.toString());
		  bw.close();
			//
			HttpServletResponse response  = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("fileName", "FILE_NAME");
			response.setContentType(URLConnection.guessContentTypeFromName(temp.getName()));
	        response.addHeader("Content-disposition", "attachment; filename=\"" + "journal_single"+this.getQuarterYear()+reversed+"\"");
			ServletOutputStream out = response.getOutputStream();
			
			byte[] fileInBytes = new byte[(int)temp.length()]; 	
			
			FileInputStream fileInputStream = new FileInputStream(temp);
	    	fileInputStream.read(fileInBytes);
	    	fileInputStream.close();
	            	
			out.write(fileInBytes);
			out.flush();
			out.close();
			response.flushBuffer();
			FacesContext.getCurrentInstance().responseComplete();
			
	  }
	
	  @SuppressWarnings("unchecked")
		public List<String> getQrtList()
		{
			List<String> returnList=new ArrayList<String>();
			try
			{
				returnList=entityManager.createQuery("select distinct s.qrt from Journal s where s.status=1 order by s.qrt desc ").getResultList();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				FacesContext.getCurrentInstance().addMessage("getQrtList", 
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error in getSummaryReportSingle contact BADG", null));
				log.info("error in getQrtList ");
			}
			return returnList;
		}
	
	public String getQuarterYear() {
		return quarterYear;
	}
	public void setQuarterYear(String quarterYear) {
		this.quarterYear = quarterYear;
	}

	public List<SingleJournal> getSingleJournals() {
		return singleJournals;
	}

	public void setSingleJournals(List<SingleJournal> singleJournals) {
		this.singleJournals = singleJournals;
	}

	public List<NonSingleJournal> getNonSingleJournals() {
		return nonSingleJournals;
	}

	public void setNonSingleJournals(List<NonSingleJournal> nonSingleJournals) {
		this.nonSingleJournals = nonSingleJournals;
	}


}