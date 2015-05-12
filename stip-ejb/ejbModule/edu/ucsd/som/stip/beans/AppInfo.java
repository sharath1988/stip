package edu.ucsd.som.stip.beans;

import java.io.Serializable;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;
import org.jboss.seam.web.ServletContexts;

@Name("appInfo")
@Scope(ScopeType.APPLICATION)
@AutoCreate
public class AppInfo implements Serializable {
	
	private static final long serialVersionUID = 4026049500765209947L;
	
	private static final String LOCAL_SERVER = "localhost:8080";
	private static final String DEV_SERVER = "som-dev4.ucsd.edu";
	private static final String PROD_SERVER = "som.ucsd.edu";
	
	@Logger private Log log;
	
	private boolean development = false;
	private boolean localhost = false;
	private boolean qa = false;
	private String hostname = null;
	
	public AppInfo() {}
	

	@Create
	public void onCreate() {
		Map<String, String> headers = FacesContext.getCurrentInstance().getExternalContext().getRequestHeaderMap();
		String host = headers.get("HOST");
		if (host != null) {
			log.info("HOST found: " + host);
			setHostname(host);
			if (host.toLowerCase().contains("localhost")) {
				localhost = true;
			}
			if (localhost || host.toLowerCase().contains("som-dev")) {
				development = true;
			} else if (host.toLowerCase().contains("som-qa")) {
				qa = true;
			}
		} else {
			setHostname(PROD_SERVER);
		}
	}
	
	public String getLogoutRedirect() {
		if (localhost) {
			// No SSO; just redirect to SOM home page
			return "http://som.ucsd.edu/";
		} else {
			return "http://" +
					hostname +
					"/Shibboleth.sso/Logout?return=https://a4.ucsd.edu/tritON/logout?target=http://som.ucsd.edu/";
		}
	}
	
	public boolean isTestServer() {
		return (development || qa);
	}
	
	public String getWebServiceServer() {
		if (localhost) {
			return LOCAL_SERVER;
		} else if (development) {
			return DEV_SERVER;
		} else {
			return PROD_SERVER;
		}
	}
	
	public HttpSession getSession() {
		return (HttpSession) ServletContexts.getInstance().getRequest().getSession(true);
	}
	
	public String getRequestHeader(String name) {
		return ServletContexts.getInstance().getRequest().getHeader(name);
	}
	
	public String getContextParameter(String name) {
		return FacesContext.getCurrentInstance().getExternalContext().getInitParameter(name);
	}
	
	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public boolean isQa() {
		return qa;
	}

	public void setQa(boolean qa) {
		this.qa = qa;
	}
	
	public boolean isDevelopment() {
		return development;
	}
	
	public void setDevelopment(boolean development) {
		this.development = development;
	}
	
	public boolean isLocalhost() {
		return localhost;
	}

	public void setLocalhost(boolean localhost) {
		this.localhost = localhost;
	}

}
