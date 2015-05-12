package edu.ucsd.som.stip.session;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import edu.ucsd.som.stip.entity.VwSummaryReportCombinedId;
import edu.ucsd.som.stip.entity.VwSummaryReportId;
import edu.ucsd.som.stip.entity.VwSummaryReportIdSingle;

@Name("summaryReport")
@Scope(ScopeType.CONVERSATION)
public class SummaryReport implements java.io.Serializable {

	private static final long serialVersionUID = 7974147265099837857L;
	@Logger private Log log;
	@In EntityManager entityManager;
	@In BadgIdentity identity;
	@In FacesMessages facesMessages;
	private final String SEPARATOR = "\t";
	private final String END_OF_LINE = "\n";
	private String dept;
	private List<String> quarterYears;
	
	
	@SuppressWarnings("unchecked")
	public List<VwSummaryReportId> getSummaryReport()
	{
		
		
		List<VwSummaryReportId> returnList=new ArrayList<VwSummaryReportId>();
		try
		{
			if(this.getQuarterYears()!=null && !this.getQuarterYears().isEmpty())
			{
				if( dept==null || dept.isEmpty() )
				{
					dept="%";
				}
				if(identity.hasRole("stip_admin"))
				{
					//Commented to remove totals
					//List<VwSummaryReportId> actualList
					returnList=entityManager.createQuery("select s.id from VwSummaryReport  s where s.id.fyQrt in (:fyQrt) and s.id.amount<>0 "
							+ " and s.id.dept like :dept "
							+ " order by s.id.dept,s.id.division,s.id.fyQrt desc,s.id.indx desc").
							setParameter("fyQrt", this.getQuarterYears()).setParameter("dept",dept) .getResultList();
				/*	for(VwSummaryReportId id:actualList)
					{
						if(id.getDepToIndex()==null || id.getDepToIndex().isEmpty())
						{
							id.setDept(id.getDivision()+"_"+id.getFyQrt()+" Total:");
							id.setDivision("");
							id.setFyQrt("");

						}
						returnList.add(id);
					}*/
				}
				else if(identity.hasRole("dbo"))
				{
					//List<VwSummaryReportId> actualList
					returnList=entityManager.createQuery("select s.id from VwSummaryReport  s where s.id.fyQrt in (:fyQrt) and s.id.amount<>0 and s.id.dept in "
															+ " (select uad.department.deptLongName from UserAcctDept uad where uad.userAcct.accountId=:accountId) "
															+ " and s.id.dept like :dept "
															+ " order by s.id.dept,s.id.division,s.id.fyQrt desc,s.id.indx desc").
											setParameter("fyQrt", this.getQuarterYears())
											.setParameter("accountId", identity.getAccountId())
											.setParameter("dept",dept)
											.getResultList();
					
					/*for(VwSummaryReportId id:actualList)
					{
						if(id.getDepToIndex()==null || id.getDepToIndex().isEmpty())
						{
							id.setDept(id.getDivision()+"_"+id.getFyQrt()+" Total:");
							id.setDivision("");
							id.setFyQrt("");

						}
						returnList.add(id);
					}*/
				}
				else
				{
					FacesContext.getCurrentInstance().addMessage("getSummaryReport", 
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "You donot have permission to access the reports", null));
				}
			}
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("getSummaryReport", 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error in getSummaryReport contact BADG", null));
			log.info("error in getSummaryReport ");
		}
		return returnList;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<VwSummaryReportIdSingle> getSummaryReportSingle()
	{
		
		
		List<VwSummaryReportIdSingle> returnList=new ArrayList<VwSummaryReportIdSingle>();
		try
		{
			if(this.getQuarterYears()!=null && !this.getQuarterYears().isEmpty())
			{
				if( dept==null || dept.isEmpty() )
				{
					dept="%";
				}
				
				if(identity.hasRole("stip_admin"))
				{
					//List<VwSummaryReportIdSingle> actualList
					returnList=entityManager.createQuery("select s.id from VwSummaryReportSingle  s where s.id.fyQrt in (:fyQrt) and s.id.amount<>0 "
							+ " and s.id.dept like :dept "
							+ " order by s.id.dept,s.id.division,s.id.fyQrt desc,s.id.depToIndex desc").
							setParameter("fyQrt", this.getQuarterYears()).setParameter("dept",dept).getResultList();
					/*for(VwSummaryReportIdSingle id:actualList)
					{
						if(id.getDepToIndex()==null || id.getDepToIndex().isEmpty())
						{
							id.setDept(id.getDivision()+"_"+id.getFyQrt()+" Total:");
							id.setDivision("");
							id.setFyQrt("");

						}
						returnList.add(id);
					}*/
				}
				else if(identity.hasRole("dbo"))
				{
					//List<VwSummaryReportIdSingle> actualList
					returnList=entityManager.createQuery("select s.id from VwSummaryReportSingle  s where s.id.fyQrt in (:fyQrt) and s.id.amount<>0 and s.id.dept in "
															+ " (select uad.department.deptLongName from UserAcctDept uad where uad.userAcct.accountId=:accountId) "
															+ " and s.id.dept like :dept "
															+ " order by s.id.dept,s.id.division,s.id.fyQrt desc,s.id.depToIndex desc").
											setParameter("fyQrt", this.getQuarterYears())
											.setParameter("dept",dept)
											.setParameter("accountId", identity.getAccountId())
											.getResultList();
					/*for(VwSummaryReportIdSingle id:actualList)
					{
						if(id.getDepToIndex()==null || id.getDepToIndex().isEmpty())
						{
							id.setDept(id.getDivision()+"_"+id.getFyQrt()+" Total:");
							id.setDivision("");
							id.setFyQrt("");

						}
						returnList.add(id);
					}*/
				}
				else
				{
					FacesContext.getCurrentInstance().addMessage("getSummaryReportSingle", 
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "You donot have permission to access the reports", null));
				}
			}
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("getSummaryReportSingle", 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error in getSummaryReportSingle contact BADG", null));
			log.info("error in getSummaryReportSingle ");
		}
		return returnList;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<VwSummaryReportCombinedId> getSummaryReportCombined()
	{
		
		
		List<VwSummaryReportCombinedId> returnList=new ArrayList<VwSummaryReportCombinedId>();
		try
		{
			if(this.getQuarterYears()!=null && !this.getQuarterYears().isEmpty())
			{
				if( dept==null || dept.isEmpty() )
				{
					dept="%";
				}
				if(identity.hasRole("stip_admin"))
				{
					returnList=entityManager.createQuery("select s.id from VwSummaryReportCombined  s where s.id.fyQrt in (:fyQrt) and s.id.amount<>0 "
							+ " and s.id.dept like :dept "
							+ " order by s.id.dept,s.id.division,s.id.fyQrt desc,s.id.indx desc").
							setParameter("fyQrt", this.getQuarterYears()).setParameter("dept",dept) .getResultList();
				}
				else if(identity.hasRole("dbo"))
				{
					returnList=entityManager.createQuery("select s.id from VwSummaryReportCombined  s where s.id.fyQrt in (:fyQrt) and s.id.amount<>0 and s.id.dept in "
															+ " (select uad.department.deptLongName from UserAcctDept uad where uad.userAcct.accountId=:accountId) "
															+ " and s.id.dept like :dept "
															+ " order by s.id.dept,s.id.division,s.id.fyQrt desc,s.id.indx desc").
											setParameter("fyQrt", this.getQuarterYears())
											.setParameter("accountId", identity.getAccountId())
											.setParameter("dept",dept)
											.getResultList();
				}
				else
				{
					FacesContext.getCurrentInstance().addMessage("getSummaryReport", 
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "You donot have permission to access the reports", null));
				}
			}
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("getSummaryReport", 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error in getSummaryReport contact BADG", null));
			log.info("error in getSummaryReport ");
		}
		return returnList;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getQrtList()
	{
		List<String> returnList=new ArrayList<String>();
		try
		{
			returnList=entityManager.createQuery("select distinct s.qrt from Stipdetailsingle s where s.status=1 order by s.qrt desc ").getResultList();
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
	
	
	@SuppressWarnings("unchecked")
	public Set<String> getDeptList()
	{
		Set<String> returnList=new TreeSet<String>();
		try
		{
			//restricting to only details which have the journals cretaed
			List<String> tmpList=new ArrayList<String>();
			if(!identity.hasRole("dbo"))
			{
			 tmpList=entityManager.createQuery("select distinct s.dept from Stipdetailsingle s where s.status=1 and s.dept <> null and s.qrt in (:qrt) "
												+" union select distinct s.dept from Stipdetail s where s.status=1 and s.dept <> null and s.qrt in (:qrt) ").
												setParameter("qrt", this.getQuarterYears()).getResultList();
			}
			else
			{
				 tmpList=entityManager.createQuery("select uad.department.deptLongName from UserAcctDept uad where uad.userAcct.accountId=:accountId").
						 		setParameter("accountId", identity.getAccountId()).getResultList();
			}
			for(String s : tmpList)
			{
				returnList.add(s);
			}
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
	
	
	public void textNonSingleReport() throws IOException
  {
	  File temp = File.createTempFile("summary_non_single"+this.getQuarterYears(), ".txt"); 
	  BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
	  
	  List<List<String>> matrix = new ArrayList<List<String>>();
	  for(VwSummaryReportId idSingle: getSummaryReport())
	  {
		List<String> row=new ArrayList<String>();
				if(idSingle.getDepToIndex()!=null && !idSingle.getDepToIndex().isEmpty())
				{
				row.add(idSingle.getDept()+"\t\t\t\t\t");
				row.add(idSingle.getDivision());
				row.add(idSingle.getFyQrt());
				row.add(idSingle.getIndx()+"\t");
				row.add(idSingle.getFund()+"\t");
				row.add(idSingle.getDepToIndex()+"\t");
				row.add(idSingle.getDepToFund()+"\t\t");
				row.add(idSingle.getAmount().toString());
				matrix.add(row);
			  }
			else
			{
				row.add(idSingle.getDept()+"\t\t\t\t\t\t\t\t\t\t");
				row.add(StringUtils.leftPad("",idSingle.getDivision().length(),' '));
				row.add(StringUtils.leftPad("",idSingle.getFyQrt().length(),' '));
				row.add(StringUtils.leftPad("",idSingle.getIndx().length(),' '));
				row.add(StringUtils.leftPad("",idSingle.getFund().length(),' '));
				row.add(StringUtils.leftPad("",idSingle.getDepToIndex().length(),' '));
				row.add(StringUtils.leftPad("",idSingle.getDepToFund().length(),' '));
				row.add(idSingle.getAmount().toString());
				matrix.add(row);
			}
	  }
	  StringBuilder sb = new StringBuilder();
	  
	  sb.append("Department\t\t\t\t\t\t\tDivision\t\tFY_QRT\tIndex\t\tFund\tDept to Index\tDept to Fund\tAmount");
	  sb.append(END_OF_LINE);
	  
	    for (int i = 0; i < matrix.size(); i++) {
	        for (int o = 0; o < matrix.get(i).size(); o++) {
	            sb.append(matrix.get(i).get(o));
	            if (o <( matrix.get(i).size()-1))
	                sb.append(SEPARATOR);
	            else
	                sb.append(END_OF_LINE);
	        }
	    }
	  
	  
	  bw.write(sb.toString());
	  bw.close();
		//
		HttpServletResponse response  = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("fileName", "FILE_NAME");
		response.setContentType(URLConnection.guessContentTypeFromName(temp.getName()));
        response.addHeader("Content-disposition", "attachment; filename=\"" + "summary_non_single"+this.getQuarterYears() +"\"");
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
	
  public void textSingleReport() throws IOException
  {
	  File temp = File.createTempFile("summary_single"+this.getQuarterYears(), ".txt"); 
	  BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
	  
	  List<List<String>> matrix = new ArrayList<List<String>>();
	  for(VwSummaryReportIdSingle idSingle: getSummaryReportSingle())
	  {
		List<String> row=new ArrayList<String>();
				if(idSingle.getDepToIndex()!=null && !idSingle.getDepToIndex().isEmpty())
				{
				row.add(idSingle.getDept()+"\t\t\t\t\t");
				row.add(idSingle.getDivision());
				row.add(idSingle.getFyQrt());
				row.add(idSingle.getFund()+"\t");
				row.add(idSingle.getDepToIndex()+"\t");
				row.add(idSingle.getDepToFund()+"\t\t");
				row.add(idSingle.getAmount().toString());
				matrix.add(row);
			  }
			else
			{
				row.add(idSingle.getDept()+"\t\t\t\t\t\t\t\t\t\t");
				row.add(StringUtils.leftPad("",idSingle.getDivision().length(),' '));
				row.add(StringUtils.leftPad("",idSingle.getFyQrt().length(),' '));
				row.add(StringUtils.leftPad("",idSingle.getFund().length(),' '));
				row.add(StringUtils.leftPad("",idSingle.getDepToIndex().length(),' '));
				row.add(StringUtils.leftPad("",idSingle.getDepToFund().length(),' '));
				row.add(idSingle.getAmount().toString());
				matrix.add(row);
			}
	  }
	  StringBuilder sb = new StringBuilder();
	  
	  sb.append("Department\t\t\t\t\t\t\tDivision\t\tFY_QRT\tFund\tDept to Index\tDept to Fund\tAmount");
	  sb.append(END_OF_LINE);
	  
	    for (int i = 0; i < matrix.size(); i++) {
	        for (int o = 0; o < matrix.get(i).size(); o++) {
	            sb.append(matrix.get(i).get(o));
	            if (o <( matrix.get(i).size()-1))
	                sb.append(SEPARATOR);
	            else
	                sb.append(END_OF_LINE);
	        }
	    }
	  
	  
	  bw.write(sb.toString());
	  bw.close();
		//
		HttpServletResponse response  = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("fileName", "FILE_NAME");
		response.setContentType(URLConnection.guessContentTypeFromName(temp.getName()));
        response.addHeader("Content-disposition", "attachment; filename=\"" + "summary_single"+this.getQuarterYears() +"\"");
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


public void reset()
{
	this.quarterYears=null;
	this.dept=null;
}
public String getDept() {
	return dept;
}



public void setDept(String dept) {
	this.dept = dept;
}



public List<String> getQuarterYears() {
	return quarterYears;
}



public void setQuarterYears(List<String> quarterYears) {
	this.quarterYears = quarterYears;
}

}