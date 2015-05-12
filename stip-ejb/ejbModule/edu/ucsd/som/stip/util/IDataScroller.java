package edu.ucsd.som.stip.util;

import javax.ejb.Local;

@Local
public interface IDataScroller {

	public abstract Integer getScrollerPage();
	public abstract void setScrollerPage(Integer scrollerPage);

	public Integer getScrollerMaxPages();
	public abstract void setScrollerMaxPages(Integer scrollerMaxPages);
	
	public abstract String getScrollerAlign();
	public abstract void setScrollerAlign(String scrollerAlign);
	
}
