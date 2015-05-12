package edu.ucsd.som.stip.app;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Startup;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

import edu.ucsd.som.stip.util.DateManager;
import edu.ucsd.som.stip.workflow.StipLinkManager;

@Startup
@Scope(ScopeType.APPLICATION)
@Name("environment")
public class Environment implements Serializable
{
 
	private static final long serialVersionUID = 5437901010164593439L;
	
	@Logger
    private Log log;
    
    @In(create=true, required=false)
    DateManager dateManager;
                
    private Properties properties = null;
    private String     hostLink = "";
    private Boolean    isLocal = false;
    private Boolean    isDevEmailCommentsEnabled = false;
    private Boolean    isProductionEmailEnabled = false;
    private String     dateTimeStamp = "";
    
    @Create
    public void setup() throws EnvironmentException 
    {
       ClassLoader loader = Thread.currentThread().getContextClassLoader();
       properties = new Properties();
       try {
        	StipLinkManager spaceLinkManager = new StipLinkManager(); 
            if (spaceLinkManager != null)
            {
	            isLocal = spaceLinkManager.getIsLocal();
	            hostLink = spaceLinkManager.getDevProdLink();
            }
             // load
             properties.load(loader.getResourceAsStream("environment.properties"));
             // setup
             isDevEmailCommentsEnabled = ( "true".equals(getProperty("email.dev-email-comments-enabled")) ? true : false);
             isProductionEmailEnabled  = ( "true".equals(getProperty("email.production-email-enabled")) ? true : false);
             // set timestamp
             dateTimeStamp = dateManager.getDateStamp();
                                
            // log
       
        }
        catch(IOException e) {
                log.warn("Unable to load environment.properties file: {0}",e.getMessage());
            throw new EnvironmentException("Unable to load environment.properties", e);
        }

    }

    public String getProperty(String name) {
        return properties.getProperty(name);
    }

                public String getLogoutRedirect() {
                                String url = "";
                                if (isLocal) {
                                                url = getProperty("application.logout.local");
                                } else {
                                                url = "" + hostLink + "/Shibboleth.sso/Logout?return=https://a4.ucsd.edu/tritON/logout?target=http://som.ucsd.edu/";
                                }
                                return url;
                }

                // support the global application scoped logic
                public String getHostLink() {
                                return hostLink;
                }
                public void setHostLink(String hostLink) {
                                this.hostLink = hostLink;
                }
                public Boolean getIsLocal() {
                                return isLocal;
                }
                public void setIsLocal(Boolean isLocal) {
                                this.isLocal = isLocal;
                }
                public Boolean getIsDevEmailCommentsEnabled() {
                                return isDevEmailCommentsEnabled;
                }
                public void setIsDevEmailCommentsEnabled(Boolean isDevEmailCommentsEnabled) {
                                this.isDevEmailCommentsEnabled = isDevEmailCommentsEnabled;
                }
                public Boolean getIsProductionEmailEnabled() {
                                return isProductionEmailEnabled;
                }
                public void setIsProductionEmailEnabled(Boolean isProductionEmailEnabled) {
                                this.isProductionEmailEnabled = isProductionEmailEnabled;
                }
                public String getDateTimeStamp() {
                                return dateTimeStamp;
                }              
                public void setDateTimeStamp(String dateTimeStamp) {
                                this.dateTimeStamp = dateTimeStamp;
                }
                
}
