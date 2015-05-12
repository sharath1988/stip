package edu.ucsd.som.stip.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

import edu.ucsd.som.stip.beans.MissingIndices;
import edu.ucsd.som.stip.beans.StipDiffIFISGA;
import edu.ucsd.som.stip.entity.Journal;
import edu.ucsd.som.stip.entity.Stipdetail;
import edu.ucsd.som.stip.entity.Stipdetailsingle;
import edu.ucsd.som.stip.services.AuditRepository;

@Name("audit")
@Scope(ScopeType.CONVERSATION)
public class Audit implements java.io.Serializable {

	private static final long serialVersionUID = -1055113653731850124L;

	@Logger private Log log;
	@In EntityManager entityManager;
	@In BadgIdentity identity;
	
	@In(create=true)
	MisMatchsignPanel misMatchsignPanel;
	@In(create=true)
	InactiveIndicesPanel inactiveIndicesPanel;
	@In(create=true)
	MissingIndicesPanel missingIndicesPanel;
	@In(create=true)
	IfisGAMismatchesPanel ifisGAMismatchesPanel;
	
	
	private String intRate;
	private String quarterYear;
	private Integer quarter;
	private Integer year;
	private boolean initDone;
	private Integer seqNoUpdate;
	private boolean manageIdxExpand;
	private String fundtoUpdate;
	private List<String> fundsAssignedWithIndx=new ArrayList<String>();
	private List<String> activatedIndices=new ArrayList<String>();

	@SuppressWarnings("unchecked")
	public void init()
	{
	try{
		
		if(!initDone)
		{
			if(this.getIntRate()==null || this.getQuarterYear()==null)
			{
				FacesContext.getCurrentInstance().addMessage("stipValidate", 
						new FacesMessage(FacesMessage.SEVERITY_ERROR,"Quarter Year / Intrate not valid. Please go back to home page and try again.", null));
			}
			else
			{
			log.info("intRate - >"+this.getIntRate());
			log.info("quarterYear - >"+this.getQuarterYear());
			this.quarter=Integer.parseInt(this.getQuarterYear().substring(6,7));
			this.year=Integer.parseInt(this.getQuarterYear().substring(0, 4));
			
			//creates stipamt_validate table for creating journals
			int stipValidate=entityManager.createNativeQuery(AuditRepository.stipValidateQuery).
										  setParameter("qtrYr",this.getQuarterYear()).executeUpdate();
			
			if(stipValidate==0)
			{
				FacesContext.getCurrentInstance().addMessage("stipValidate", 
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error in procedure stipValidate!Please try again.", null));
				
			}
			else
			{
				String docNum = null;
				String qrtInput=null;
				if(quarter==1)
				{
					docNum="09D97";
					qrtInput="1 09/"+year;
				}
				else if(quarter==2)
				{
					docNum="12D97";
					qrtInput="2 12/"+year;
				}
				else if(quarter==3)
				{
					docNum="03D97";
					qrtInput="3 03/"+year;
				}
				else if(quarter==4)
				{
					docNum="06D97";
					qrtInput="4 06/"+year;
				}

				int qtrYrSt=Integer.parseInt(year+"05");
				int QtrYrEnd=Integer.parseInt(year+1+"05");
				BigDecimal diffSum=new BigDecimal(0);
				List<Object[]> diffInStipAmt=(List<Object[]>)entityManager.
											  createNativeQuery(AuditRepository.diffInStipAmtQuery).
													setParameter("docNum",docNum).
													setParameter("qtrYrSt",qtrYrSt).
													setParameter("QtrYrEnd",QtrYrEnd).
													setParameter("qtrYr",this.getQuarterYear()).
													getResultList();
				
				for(Object[] obj:diffInStipAmt)
				{
					BigDecimal i=(BigDecimal) obj[4];
					if(i.compareTo(BigDecimal.ZERO) > 0)
					{
						diffSum=diffSum.add(i);
						StipDiffIFISGA diffIFISGA=new StipDiffIFISGA(obj);
						this.ifisGAMismatchesPanel.getStipDiffIFISGAs().add(diffIFISGA);
					}
					
				}
				if (diffSum.compareTo(BigDecimal.ZERO) > 0)
				{
					//ifisGAMismatchesPanel.setOpen(true);
				}
				
				else
				{
					this.ifisGAMismatchesPanel.setStipDiffIFISGAs(new ArrayList<StipDiffIFISGA>());
				}
				
				// generating source for journals from adb and ga excel
				int qrtymdValidate=entityManager.createNativeQuery(AuditRepository.qrtymdValidateQuery)
												.setParameter("qtrYr", this.getQuarterYear()).setParameter("intRate", this.getIntRate())
												.executeUpdate();
				if(qrtymdValidate==0)
				{
					FacesContext.getCurrentInstance().addMessage("stipValidate", 
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error in procedure process_stip_distribution!Please try again.", null));
					
				}
				else
				{
					//checkSign
					
					boolean checkMismatch=false;
					checkMismatch=!(boolean)entityManager.createNativeQuery(AuditRepository.checkMismatchQuery).
														 setParameter("qrtYr",this.getQuarterYear()).
														 getSingleResult().toString().equals("true");
					if(checkMismatch)
					{
						this.misMatchsignPanel.setSignMismatches(entityManager.createQuery(AuditRepository.signMismatchesQuery).
																 setParameter("qrt",this.getQuarterYear()).getResultList());
					}
					else
					{
						// create journals
						entityManager.createNativeQuery(AuditRepository.cerateJournalNonSingleQuery)
						             .setParameter("qrtYr", this.getQuarterYear()).setParameter("qrtInput", qrtInput).executeUpdate();
					    entityManager.createNativeQuery(AuditRepository.cerateJournalSingleQuery)
			             			 .setParameter("qrtYr", this.getQuarterYear()).setParameter("qrtInput", qrtInput).executeUpdate();
					    
					    //creating single journal
					    	Journal singleJournal=new Journal();
					    	singleJournal.setQrt(this.getQuarterYear());
					    	singleJournal.setCreatedBy(identity.getAccountId());
					    	singleJournal.setDateCreated(new Date());
					    	singleJournal.setDateModified(new Date());
					    	singleJournal.setType("Single");
					    	singleJournal.setReversed(false);
						
					    //creating non single journal
					    	Journal nonSingleJournal=new Journal();
					    	nonSingleJournal.setQrt(this.getQuarterYear());
					    	nonSingleJournal.setCreatedBy(identity.getAccountId());
					    	nonSingleJournal.setDateCreated(new Date());
					    	nonSingleJournal.setDateModified(new Date());
					    	nonSingleJournal.setType("Non-Single");
					    	nonSingleJournal.setReversed(false);
					    
					    populateMissinactIndexes();
					    
						//journal status to true if no missing / invalid
						if(this.inactiveIndicesPanel.getInactiveIndices().isEmpty() && this.missingIndicesPanel.getMissIndices().isEmpty() && 
								this.ifisGAMismatchesPanel.getStipDiffIFISGAs().isEmpty() && this.misMatchsignPanel.getSignMismatches().isEmpty())
						{
					    //add condition back to limit only valid reports
							entityManager.createQuery("update MaststipNew set status=1").executeUpdate();
							entityManager.createQuery("update MaststipSingle set status=1").executeUpdate();
							entityManager.createNativeQuery("update stip"+quarterYear+" set status=1").executeUpdate();
							entityManager.createNativeQuery("update stipsingle"+quarterYear+" set status=1").executeUpdate();
							entityManager.createNativeQuery("update stipdetail set status=1 where qrt='"+quarterYear+"'").executeUpdate();
							entityManager.createNativeQuery("update stipdetailsingle set status=1 where qrt='"+quarterYear+"'").executeUpdate();
							
							entityManager.persist(singleJournal);
							entityManager.persist(nonSingleJournal);
							entityManager.createNativeQuery("update journal set status=1 where qrt='"+quarterYear+"'").executeUpdate();
						}
						else
							entityManager.createNativeQuery("update journal set status=0 where qrt='"+quarterYear+"'").executeUpdate();
								
					}
					
				}
				
				
			}
		
			this.setInitDone(true);
			}
		}
		
		else
		{
			this.callAuditsFromReverseJournals();
		}
		
	   }catch(Exception e)
	   {
			FacesContext.getCurrentInstance().addMessage("stipValidate", 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"error occured please donot enter the link maually.. Please conatact BADG for more info... --- > "+e.getMessage(), null));
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	private void callAuditsFromReverseJournals() {
		try{
			
			

			if(this.getQuarterYear()==null)
			{
				FacesContext.getCurrentInstance().addMessage("callAuditsFromReverseJournals", 
						new FacesMessage(FacesMessage.SEVERITY_ERROR,"Quarter Year not valid. Please go back to home page and try again.", null));
			}
			else
			{
			log.info("quarterYear - >"+this.getQuarterYear());
			this.quarter=Integer.parseInt(this.getQuarterYear().substring(6,7));
			this.year=Integer.parseInt(this.getQuarterYear().substring(0, 4));
			
				String docNum = null;
				if(quarter==1)
				{
					docNum="09D97";
				}
				else if(quarter==2)
				{
					docNum="12D97";
				}
				else if(quarter==3)
				{
					docNum="03D97";
				}
				else if(quarter==4)
				{
					docNum="06D97";
				}

				int qtrYrSt=Integer.parseInt(year+"05");
				int QtrYrEnd=Integer.parseInt(year+1+"05");
				BigDecimal diffSum=new BigDecimal(0);
				List<Object[]> diffInStipAmt=(List<Object[]>)entityManager.
											  createNativeQuery(AuditRepository.diffInStipAmtQuery).
													setParameter("docNum",docNum).
													setParameter("qtrYrSt",qtrYrSt).
													setParameter("QtrYrEnd",QtrYrEnd).
													setParameter("qtrYr",this.getQuarterYear()).
													getResultList();
				
				for(Object[] obj:diffInStipAmt)
				{
					BigDecimal i=(BigDecimal) obj[4];
					if(i.compareTo(BigDecimal.ZERO) > 0)
					{
						diffSum=diffSum.add(i);
						StipDiffIFISGA diffIFISGA=new StipDiffIFISGA(obj);
						this.ifisGAMismatchesPanel.getStipDiffIFISGAs().add(diffIFISGA);
					}
					
				}
				if (diffSum.compareTo(BigDecimal.ZERO) > 0)
				{
					//ifisGAMismatchesPanel.setOpen(true);
				}
				
				else
				{
					this.ifisGAMismatchesPanel.setStipDiffIFISGAs(new ArrayList<StipDiffIFISGA>());
				}
				
					//checkSign
					
					boolean checkMismatch=false;
					checkMismatch=!(boolean)entityManager.createNativeQuery(AuditRepository.checkMismatchQuery).
														 setParameter("qrtYr",this.getQuarterYear()).
														 getSingleResult().toString().equals("true");
					if(checkMismatch)
					{
						this.misMatchsignPanel.setSignMismatches(entityManager.createQuery(AuditRepository.signMismatchesQuery).
																 setParameter("qrt",this.getQuarterYear()).getResultList());
					}
					    
					    populateMissinactIndexes();
					    
					    
					    
					  /*TODO check if audit issues after reverse what should be done?
					   * //journal status to true if no missing / invalid
						if(!this.inactiveIndicesPanel.getInactiveIndices().isEmpty() || !this.missingIndicesPanel.getMissIndices().isEmpty() || 
								!this.ifisGAMismatchesPanel.getStipDiffIFISGAs().isEmpty() || !this.misMatchsignPanel.getSignMismatches().isEmpty())
						{
					    //add condition back to limit only valid reports
							entityManager.createNativeQuery("update stip"+quarterYear+" set status=0").executeUpdate();
							entityManager.createNativeQuery("update stipsingle"+quarterYear+" set status=0").executeUpdate();
							entityManager.createNativeQuery("update stipdetail set status=0 where qrt='"+quarterYear+"'").executeUpdate();
							entityManager.createNativeQuery("update stipdetailsingle set status=0 where qrt='"+quarterYear+"'").executeUpdate();
							entityManager.createNativeQuery("update journal set status=0 where qrt='"+quarterYear+"'").executeUpdate();
						}*/
					    
					    
					    
								
			this.setInitDone(true);
			}
			
		}catch(Exception ex)
		{
			FacesContext.getCurrentInstance().addMessage("callAuditsFromReverseJournals", 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"error occured while calling callAuditsFromReverseJournals....Please conatact BADG with following info... --- > "+ex.getMessage(), null));
			ex.printStackTrace();
		}
	}


	@SuppressWarnings("unchecked")
	public void populateMissinactIndexes()
	{
		List<Stipdetail> missingIndices=new ArrayList<Stipdetail>();
	    List<Stipdetailsingle> missingIndicessingle=new ArrayList<Stipdetailsingle>();
	    List<Stipdetail> inactiveIndices=new ArrayList<Stipdetail>();
	    List<Stipdetailsingle> inactiveIndicessingle=new ArrayList<Stipdetailsingle>();
	    
	    //Missing/Inactive Indices
	    if(!manageIdxExpand)
	    {
	    	missingIndices=entityManager.createQuery(AuditRepository.missingIndicesQuery).
			 						    setParameter("qrt",this.getQuarterYear()).getResultList();
	    	missingIndicessingle=entityManager.createQuery(AuditRepository.missingIndicessingleQuery).
			 								  setParameter("qrt",this.getQuarterYear()).getResultList();
	    	inactiveIndices=entityManager.createQuery(AuditRepository.inactiveIndicesQuery). 
			 							  setParameter("qrt",this.getQuarterYear()).getResultList();
	    	inactiveIndicessingle=entityManager.createQuery(AuditRepository.inactiveIndicessingleQuery). 
												setParameter("qrt",this.getQuarterYear()).getResultList();
	    }
	    
	    else
	    {
	    	missingIndices=entityManager.createQuery(AuditRepository.missingIndicesExpandQuery).
						   setParameter("qrt",this.getQuarterYear()).getResultList();
			missingIndicessingle=entityManager.createQuery(AuditRepository.missingIndicessingleExpandQuery).
			 					 setParameter("qrt",this.getQuarterYear()).getResultList();
			inactiveIndices=entityManager.createQuery(AuditRepository.inactiveIndicesExpandQuery). 
						    setParameter("qrt",this.getQuarterYear()).getResultList();
			inactiveIndicessingle=entityManager.createQuery(AuditRepository.inactiveIndicessingleExpandQuery). 
								  setParameter("qrt",this.getQuarterYear()).getResultList();
	    }
	    
		   List<MissingIndices> missIndices= new ArrayList<MissingIndices>();
		   List<MissingIndices> inactIndices= new ArrayList<MissingIndices>();
		
		 for(Stipdetail missingIndex: missingIndices)
		 {
			 if(!this.fundsAssignedWithIndx.contains(missingIndex.getPfFund()))
				 missIndices.add(new MissingIndices(missingIndex,"missing"));
		 }
		 for(Stipdetailsingle missingIndexSingle: missingIndicessingle)
		 {
			 if(!this.fundsAssignedWithIndx.contains(missingIndexSingle.getPfFund()))
				 missIndices.add(new MissingIndices(missingIndexSingle,"missing"));
		 }
		 
		  missingIndicesPanel.setMissIndices(missIndices);
		 for(Stipdetail inactiveIndex: inactiveIndices)
		 {
			 if(!this.activatedIndices.contains(inactiveIndex.getInde()))
				 inactIndices.add(new MissingIndices(inactiveIndex,"inactive"));
		 }
		 for(Stipdetailsingle inactiveIndexsingle: inactiveIndicessingle)
		 {
			 if(!this.activatedIndices.contains(inactiveIndexsingle.getInde()))
				 inactIndices.add(new MissingIndices(inactiveIndexsingle,"inactive"));
		 }
		  inactiveIndicesPanel.setInactiveIndices(inactIndices);
	}
	
	/*Not Used May be future
	 * public void updateMisIndx()
	{
		try{
		int updated=0;
		for(MissingIndices m:this.missingIndicesPanel.getMissIndices())
		{
			if(m.isUpdate() && m.getFund().equals(this.fundtoUpdate) && m.getIdxAdd()!=null && !m.getIdxAdd().isEmpty())
			{
				updated++;
				StipDept sd= new StipDept();
				
				sd.setCreatedBy(identity.getAccountId());
				sd.setDateCreated(new Date());
				sd.setIndx(m.getIdxAdd()); 
				UcsdIndex u=(UcsdIndex) entityManager.createQuery("from UcsdIndex i where i.id.indx = :idxName ").setParameter("idxName", m.getIdxAdd()).getSingleResult();
				sd.setFund(u.getId().getFund());
				sd.setOrg(u.getId().getOrganization());
				Object[] deptDiv=(Object[]) entityManager.createNativeQuery("select  distinct smgName,divName from som_rollup where divId = :divId").
									setParameter("divId", u.getId().getDivisionId()).setMaxResults(1).getSingleResult();
				
				sd.setDept((String) deptDiv[0]);
				
				//problem if not matched as checking division while updating stipdetail
				if(sd.getDept().equals("Medical Group"))
				sd.setDivision((String) deptDiv[1]);
				
				sd.setNotes("Added as there was missing index");
				sd.setStatus(true);
				
				
				if(m.getFund()!=null && !m.getFund().isEmpty() && m.getFund().startsWith("76") && !u.getId().getIndx().equals("SOM8864"))
				{
					sd.setAcct("723050");
				}
				else
				sd.setAcct("720703");
				
				
				sd.setStipFund(m.getFund());
				
				this.fundtoUpdate=null;
				if(sd.getDept().equals(m.getDept()))
				{
					entityManager.persist(sd);
					entityManager.flush();
					this.fundsAssignedWithIndx.add(m.getFund());
					m.setUpdate(false);
				}
				else{
					 
					FacesContext.getCurrentInstance().addMessage("updateMisIndx", 
							new FacesMessage(FacesMessage.SEVERITY_ERROR,"Index should be from same majorgroup", null));
				}
				
			}
				
		}
		
		if(updated==0)
		{
			FacesContext.getCurrentInstance().addMessage("updateMisIndx", 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"index not valid .select Index from drop down.", null));
		}
		}
		catch(EntityNotFoundException ex)
		{
			FacesContext.getCurrentInstance().addMessage("updateMisIndx", 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"index not valid .select Index from drop down.", null));
		}
		catch(NoResultException ex)
		{
			FacesContext.getCurrentInstance().addMessage("updateMisIndx", 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"index not valid .select Index from drop down.", null));
		}
		catch(Exception ex)
		{
			FacesContext.getCurrentInstance().addMessage("updateMisIndx", 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"index not valid .select Index from drop down.", null));
		}
		
	}
	
	/**
	 * not sure why the flush mode is auto instead setting in page.xml
	 * overridding flush mode
	 */
	/*
	 * Not Used May be future
	@SuppressWarnings("unchecked")
	@Begin(join=true,flushMode=FlushModeType.MANUAL)
	public void updateinactIdx()
	{
		
		try{
			int updated=0;
			boolean isError=false;
		
			for(MissingIndices m:this.inactiveIndicesPanel.getInactiveIndices())
				{
					if(m.isUpdate() && m.getFund().equals(this.fundtoUpdate) && m.getIdxAdd()!=null && !m.getIdxAdd().isEmpty())
					{
						updated++;
						List<StipDept> sd= (List<StipDept>)entityManager.createQuery("from StipDept d where d.indx=:indx ").setParameter("indx",m.getOldIdx()).getResultList();
							for(StipDept s :sd )
							{
								s.setIndx(m.getIdxAdd());
								// check acct and stipfund remains same confirm
								//s.setAcct("720703");
								s.setModifiedBy(identity.getAccountId());
								s.setDateModified(new Date());
								UcsdIndex u=(UcsdIndex) entityManager.createQuery("from UcsdIndex i where i.id.indx = :idxName ").setParameter("idxName", m.getIdxAdd()).getSingleResult();
								s.setFund(u.getId().getFund());
								s.setOrg(u.getId().getOrganization());
								Object[] deptDiv=(Object[]) entityManager.createNativeQuery("select  distinct smgName,divName from som_rollup where divId = :divId ").
										setParameter("divId", u.getId().getDivisionId()).setMaxResults(1).getSingleResult();
								s.setDept((String) deptDiv[0]);
								// problem if not matched as checking division while updating stipdetail
								if(s.getDept().equals("Medical Group"))
									s.setDivision((String) deptDiv[1]);
								if(s.getNotes()!=null && !s.getNotes().trim().isEmpty())
									s.setNotes(s.getNotes()+". Added as there was inactive index "+m.getOldIdx()+" before");
								else
									s.setNotes("Added as there was inactive index "+m.getOldIdx()+" before");
								s.setStatus(true);
								if(s.getDept().equals(m.getDept()) && !m.getOldIdx().equals(s.getIndx()))
								{
									entityManager.merge(s);
									entityManager.flush();
								}
								else
								{
									isError=true;
									FacesContext.getCurrentInstance().addMessage("updateMisIndx", 
											new FacesMessage(FacesMessage.SEVERITY_ERROR,"Index should be from same majorgroup and Index cannot be same as before. you selected "+m.getIdxAdd(), null));
									m.setIdxAdd(m.getOldIdx());
								}
								
								
							}
							
							if(!isError)
							{
								this.activatedIndices.add(m.getOldIdx());
								m.setUpdate(false);
							}
					}
						
				}
		
		if(updated==0)
		{
			FacesContext.getCurrentInstance().addMessage("updateinactIdx", 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"index not valid .select Index from drop down.", null));
		}
		this.fundtoUpdate=null;
		}
		catch(EntityNotFoundException ex)
		{
			FacesContext.getCurrentInstance().addMessage("updateinactIdx", 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"index not valid .select Index from drop down.", null));
		}
		catch(NoResultException ex)
		{
			FacesContext.getCurrentInstance().addMessage("updateinactIdx", 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"index not valid .select Index from drop down.", null));
		}
		catch(Exception ex)
		{
			FacesContext.getCurrentInstance().addMessage("updateinactIdx", 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"index not valid .select Index from drop down.", null));
		}
		
	
	}
	*/
	
	public String getIntRate() {
		return intRate;
	}
	public void setIntRate(String intRate) {
		this.intRate = intRate;
	}
	public String getQuarterYear() {
		return quarterYear;
	}
	public void setQuarterYear(String quarterYear) {
		this.quarterYear = quarterYear;
	}



	public MisMatchsignPanel getMisMatchsignPanel() {
		return misMatchsignPanel;
	}



	public void setMisMatchsignPanel(MisMatchsignPanel misMatchsignPanel) {
		this.misMatchsignPanel = misMatchsignPanel;
	}



	public InactiveIndicesPanel getInactiveIndicesPanel() {
		return inactiveIndicesPanel;
	}



	public void setInactiveIndicesPanel(InactiveIndicesPanel inactiveIndicesPanel) {
		this.inactiveIndicesPanel = inactiveIndicesPanel;
	}



	public MissingIndicesPanel getMissingIndicesPanel() {
		return missingIndicesPanel;
	}



	public void setMissingIndicesPanel(MissingIndicesPanel missingIndicesPanel) {
		this.missingIndicesPanel = missingIndicesPanel;
	}



	public IfisGAMismatchesPanel getIfisGAMismatchesPanel() {
		return ifisGAMismatchesPanel;
	}



	public void setIfisGAMismatchesPanel(IfisGAMismatchesPanel ifisGAMismatchesPanel) {
		this.ifisGAMismatchesPanel = ifisGAMismatchesPanel;
	}


	public boolean isInitDone() {
		return initDone;
	}


	public void setInitDone(boolean initDone) {
		this.initDone = initDone;
	}


	public Integer getSeqNoUpdate() {
		return seqNoUpdate;
	}


	public void setSeqNoUpdate(Integer seqNoUpdate) {
		this.seqNoUpdate = seqNoUpdate;
	}


	public boolean isManageIdxExpand() {
		return manageIdxExpand;
	}


	public void setManageIdxExpand(boolean manageIdxExpand) {
		this.manageIdxExpand = manageIdxExpand;
	}


	public String getFundtoUpdate() {
		return fundtoUpdate;
	}


	public void setFundtoUpdate(String fundtoUpdate) {
		this.fundtoUpdate = fundtoUpdate;
	}



	public List<String> getFundsAssignedWithIndx() {
		return fundsAssignedWithIndx;
	}


	public void setFundsAssignedWithIndx(List<String> fundsAssignedWithIndx) {
		this.fundsAssignedWithIndx = fundsAssignedWithIndx;
	}
	
	
	
	public List<String> getActivatedIndices() {
		return activatedIndices;
	}


	public void setActivatedIndices(List<String> activatedIndices) {
		this.activatedIndices = activatedIndices;
	}
}