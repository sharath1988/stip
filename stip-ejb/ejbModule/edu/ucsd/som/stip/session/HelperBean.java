package edu.ucsd.som.stip.session;

import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;

import org.jboss.seam.annotations.Name;

@Name("helperBean")
public class HelperBean {
	
	private static final String MANAGED_BEAN_NAME = "helperBean";
	private HtmlInputText orgText;
	private HtmlInputText fundText;

	public static HelperBean getCurrentInstance(){
		return (HelperBean) FacesContext.getCurrentInstance().getExternalContext()
							.getRequestMap().get(MANAGED_BEAN_NAME);
	}
	public HtmlInputText getFundText() {
		return fundText;
	}

	public void setFundText(HtmlInputText fundText) {
		this.fundText = fundText;
	}

	public HtmlInputText getOrgText() {
		return orgText;
	}

	public void setOrgText(HtmlInputText orgText) {
		this.orgText = orgText;
	}

	
}
