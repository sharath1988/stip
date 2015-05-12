package edu.ucsd.som.stip.session;

import java.util.Arrays;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.jboss.seam.log.Log;

import edu.ucsd.som.stip.entity.Journal;

@Name("journalList")
@Scope(ScopeType.CONVERSATION)
public class JournalList extends EntityQuery<Journal> {

	private static final long serialVersionUID = 1317531929093192886L;

	private static final String EJBQL = "select journal from Journal journal order by journal.qrt desc,journal.dateModified desc";
	
	@In
	private EntityManager entityManager;
	@Logger
	private Log log;

	private static final String[] RESTRICTIONS = {
			//"lower(journal.docnum) like lower(concat(#{journalList.journal.docnum},'%'))",
			//"lower(journal.quarter) like lower(concat(#{journalList.journal.quarter},'%'))",
			//"journal.status =#{journalList.journal.status}",
		};

	private Journal journal = new Journal();

	public JournalList() {
		setEjbql(EJBQL);
		setOrderDirection("desc");
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		super.refresh();
	}

	public Journal getJournal() {
		return journal;
	}
	
	
	/**
	 * Process reverse journal using procedure reverse_journal
	 * @param quarter
	 */
	@End(beforeRedirect=true)
	public String reverseJournal(String quarter,String single)
	{
		try{
			
			if(single.equals("Single"))
			{
				 log.info("reverseJournal-quarter-"+quarter+"-single-"+single);
				entityManager.createNativeQuery("call reverse_journal_single(:inQrt)").setParameter("inQrt", quarter).executeUpdate();
				//perform audits
				
			}
			else if(single.equals("Non-Single"))
			{
				entityManager.createNativeQuery("call reverse_journal_nonsingle(:inQrt)").setParameter("inQrt", quarter).executeUpdate();
				//perform audits
				
			}
			else
				FacesContext.getCurrentInstance().addMessage("reverseJournal", 
						new FacesMessage(FacesMessage.SEVERITY_ERROR,"single / non single cannot be determined contact BADG ", "single / non single cannot be determined contact BADG"));
		}catch(Exception ex)
		{
			FacesContext.getCurrentInstance().addMessage("reverseJournal", 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"error while reverseJournal ", "error while reverseJournal "+ex.getMessage()));
			ex.printStackTrace();
		}
		
		return  "/admin/views/audits.xhtml?initDone=true&quarterYear="+quarter;
		
	}
	
	public void updateDocNum()
	{
		try{
			Journal updated=entityManager.find(Journal.class,journal.getJournalId());
			entityManager.refresh(updated);
			updated.setDocnum(journal.getDocnum());
			entityManager.merge(updated);
			entityManager.flush();
			//Reset
			this.journal=new Journal();
		}catch(Exception ex)
		{
			FacesContext.getCurrentInstance().addMessage("updateDocNum", 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"error while updateDocNum ", "error in updateDocNum "+ex.getMessage()));
			ex.printStackTrace();
		}
	}
	
	public void resetJournal()
	{
		this.journal=new Journal();
	}
}
