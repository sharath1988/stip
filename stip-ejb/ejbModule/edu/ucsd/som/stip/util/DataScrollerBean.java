package edu.ucsd.som.stip.util;

import java.io.Serializable;

import javax.ejb.Remove;
// import java.util.ArrayList;
// import java.util.List;
// import javax.faces.model.SelectItem;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

@Name("dataScrollerBean")
@Scope(ScopeType.SESSION)
public class DataScrollerBean implements Serializable, IDataScroller {

	// serialize
	private static final long serialVersionUID = 1L;
	private static final boolean debugging = false;

	@Logger 
	private Log log;	
	
	// class fields

	// instance fields
	private Integer scrollerPage;						// what is current page
	private Integer scrollerMaxPages;					// how many choices to display
	private String scrollerAlign;						// put choices right or left
	
	// default constructor
	public DataScrollerBean(){};

	@Create
	public void create(){
		
		this.scrollerPage = 1;
		this.scrollerMaxPages = 5;
		this.scrollerAlign = "right";
		
		if (debugging){
			log.info("Data Scroller bean has been created...");
		}
	}
	
	// public
	public Integer getScrollerPage() {
		return scrollerPage;
	}
	public void setScrollerPage(Integer scrollerPage) {
		this.scrollerPage = scrollerPage;
	}
	public void setScrollerAlign(String scrollerAlign) {
		this.scrollerAlign = scrollerAlign;
	}
	public String getScrollerAlign() {
		return scrollerAlign;
	}
	public void setScrollerMaxPages(Integer scrollerMaxPages) {
		this.scrollerMaxPages = scrollerMaxPages;
	}
	public Integer getScrollerMaxPages() {
		return scrollerMaxPages;
	}
	
	@Destroy
	@Remove
	public void destroy(){
		if (debugging){
			log.info("Data Scroller bean has been destroyed...");
		}
	}

	
}
