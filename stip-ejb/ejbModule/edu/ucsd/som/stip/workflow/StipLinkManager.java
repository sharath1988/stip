package edu.ucsd.som.stip.workflow;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Startup;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.log.Log;

@Startup
@Transactional
@Scope(ScopeType.SESSION)
@Name("stipLinkManager")
public class StipLinkManager implements Serializable {  

	@Logger 
    private Log log;
	
	private static final long serialVersionUID = 1L;
	private static final boolean debugging = false;
	
	private static final String TYPE_LOCAL = "LOCAL";
	private static final String TYPE_DEV = "DEV";
	private static final String TYPE_QA = "QA";
	private static final String TYPE_PROD = "PROD";
	
	// private
	private byte[] myIpAddr;
	private String myHostName;
	private String myLink;
	private String myType;
	private Boolean isLocal;
	private Boolean isDev;
	private Boolean isQa;
	private Boolean isProd;

	// getter setter
	public byte[] getMyIpAddr() {
		return myIpAddr;
	}
	public void setMyIpAddr(byte[] ipAddr) {
		this.myIpAddr = ipAddr;
	}
	public String getMyHostName() {
		return myHostName;
	}
	public void setMyHostName(String hostName) {
		this.myHostName = hostName;
	}
	public void setMyLink(String myLink) {
		this.myLink = myLink;
	}
	public String getMyLink() {
		return myLink;
	}
	public void setMyType(String myType) {
		this.myType = myType;
	}
	public String getMyType() {
		return myType;
	}
	public void setIsLocal(Boolean isLocal) {
		this.isLocal = isLocal;
	}
	public Boolean getIsLocal() {
		if (this.isLocal == null){
			return this.getDevProdType() == TYPE_LOCAL;
		}
		return isLocal;
	}
	public void setIsDev(Boolean isDev) {
		this.isDev = isDev;
	}
	public Boolean getIsDev() {
		if (this.isDev == null){
			return this.getDevProdType() == TYPE_DEV;
		}		
		return isDev;
	}
	public void setIsQa(Boolean isQa) {
		this.isQa = isQa;
	}
	public Boolean getIsQa() {
		if (this.isQa == null){
			return this.getDevProdType() == TYPE_QA;
		}		
		return isQa;
	}
	public void setIsProd(Boolean isProd) {
		this.isProd = isProd;
	}
	public Boolean getIsProd() {
		if (this.isProd == null){
			return this.getDevProdType() == TYPE_PROD;
		}		
		return isProd;
	}

	/**
	 * initialize the link manager and establish if DEV, PROD, LOCAL environment is present.
	 */
	@Create
	public void init(){
		this.getDevProdType();
	}
	
	/**
	 * return type of environment application is running in, LOCAL, DEV, QA, PROD
	 * @return
	 */
	public String getDevProdType(){

		try{
			// identity host
			InetAddress addr = InetAddress.getLocalHost();

			// write properties
			this.setMyHostName(addr.getHostName());
			this.setMyIpAddr(addr.getAddress());	
			this.setMyType("");
			//
			this.setIsLocal(false);
			this.setIsDev(false);
			this.setIsQa(false);
			this.setIsProd(false);
			
			//development host, windows 7 pc, old xp pc
			if (this.myHostName.toLowerCase().contains("somccywvr1") ||
					this.myHostName.toLowerCase().contains("somccyxvr1")){
				this.setIsLocal(true);
				this.setMyType(TYPE_LOCAL);
			}
			//linux dev2 host
			else if (this.myHostName.toLowerCase().contains("som-dev")){
				this.setIsDev(true);
				this.setMyType(TYPE_DEV);
			}
			//linux qa2 host
			else if (this.myHostName.toLowerCase().contains("som-qa")){
				this.setIsQa(true);
				this.setMyType(TYPE_QA);
			}
			//linux prod2 host
			else if (this.myHostName.toLowerCase().contains("som-prod") ||
					this.myHostName.toLowerCase().contains("som.ucsd.edu")){
				this.setIsProd(true);
				this.setMyType(TYPE_PROD);
			}
			else {
				log.warn("Type cannot be determined, this is a problem, setting to LOCAL: {0}",this.getMyHostName());
				this.setIsLocal(true);
				this.setMyType(TYPE_LOCAL);
			}

		} 
		catch (UnknownHostException e){
			log.warn("Get Dev Prod Type unknown host exception caught and logged {0} {1}",e.getCause(), e.getMessage());
		}
		catch (Exception ex){
			log.warn("Get Dev Prod Type general exception caught and logged {0} {1}",ex.getCause(), ex.getMessage());
		}
		return this.getMyType();
	}
	
	/**
	 * return proper http setting for type of environment
	 * @return
	 */
	public String getDevProdLink(){
		
		try{
			// write properties
			this.setMyLink("");

			//development host
			if (this.getDevProdType() == TYPE_LOCAL){
				this.setMyLink("http://localhost:8080");
			}
			//linux dev2 host
			else if (this.getDevProdType() == TYPE_DEV){
				this.setMyLink("http://som-dev4.ucsd.edu");
			}
			//linux qa2 host
			else if (this.getDevProdType() == TYPE_QA){
				this.setMyLink("http://som-qa4.ucsd.edu");
			}
			//linux prod2 host
			else if (this.getDevProdType() == TYPE_PROD){
				this.setMyLink("http://som.ucsd.edu");
			}
			else {
				log.warn("Link cannot determine host, this is a problem: {0}",this.getMyHostName());
				this.setMyLink("http://localhost:8080");
			}
		} 
		catch (Exception ex){
			log.warn("Get Dev Prod Link general exception caught and logged {0} {1}",ex.getCause(), ex.getMessage());
		}
		return this.getMyLink();
	}
	
	
}   

