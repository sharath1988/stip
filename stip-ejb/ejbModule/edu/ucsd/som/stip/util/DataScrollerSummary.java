package edu.ucsd.som.stip.util;

import javax.ejb.Remove;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;
import edu.ucsd.som.stip.util.DataScrollerBean;

@Name("dataScrollerSummary")
@Scope(ScopeType.SESSION)
public class DataScrollerSummary extends DataScrollerBean {

	private static final long serialVersionUID = 1L;
	private static final boolean debugging = false;

	@Logger 
	private Log log;	

	@Create
	public void create(){
		
		this.setScrollerAlign("right");
		this.setScrollerMaxPages(15);
		this.setScrollerPage(1);
		
		if (debugging){
			log.info("Data Scroller AFA bean has been created...");
		}
	}
	
	public void reset(){
		
		this.setScrollerPage(1);
		
	}
	
	@Destroy
	@Remove
	public void destroy(){
		if (debugging){
			log.info("Data Scroller AFA bean has been destroyed...");
		}
	}

	
}
