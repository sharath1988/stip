package edu.ucsd.som.stip.session;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.MDC;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;

import edu.ucsd.som.stip.app.Environment;
import edu.ucsd.som.stip.entity.UserAcct;
import edu.ucsd.som.stip.entity.UserAcctRole;
import edu.ucsd.som.stip.services.EmployeeRepository;

/**
* Authenticate user logins for the STIP. 
 * 
 * @author         Sharath A.
* @version         1.0
* @since           08/2014
*
*/
@AutoCreate()
@Scope(ScopeType.SESSION)
@Name("authenticator")
public class Authenticator implements Serializable, IAuthenticator {
                
	private static final long serialVersionUID = -8230474926246588075L;

	@Logger 
    private Log log;
    
    @In (create=true, required=false)
    FacesContext facesContext;
    
    @In 
    private BadgIdentity identity;
    
    @In(create=true, required=false)
    Environment environment;
    
    @In(create=true, required=false)
    FacesMessages facesMessages;
    
    @In(create=true, required=false) @Out
    Credentials credentials;                

    @In(create=true, required=false)
    EntityManager entityManager;
    
    @In(create=true, required=false) 
    @Out(scope=ScopeType.SESSION, required=false)
    UserAcct userAccount;
    
    //fields 
    private boolean isLocalHost;
    private boolean isAuthenticated;
    private String  ucsdEID;
    private String  ucsdRACFID;
    
    // getter
    public String getEID() {return this.ucsdEID;}
    public String getRACFID() {return this.ucsdRACFID;}
    public boolean getIsLocalHost() {return this.isLocalHost;}
    public boolean getIsAuthenticated() {return this.isAuthenticated;}          
    
    // setter
    public void setIsAuthentiated(boolean _isAuthenticated) {this.isAuthenticated = _isAuthenticated;}
    // left pad zeros to nine characters, see core_user 
    public void setEID(String _ucsdEID) { this.ucsdEID = StringUtils.leftPad(_ucsdEID.trim(),9,'0');}
    // typically a 6 character string like MSDJCG.
    public void setRACFID(String _ucsdRACFID){this.ucsdRACFID = StringUtils.upperCase(_ucsdRACFID.trim());}
    public void setIsLocalHost(boolean _isLocalHost) {this.isLocalHost = _isLocalHost;}
    
    static {
                    System.out.print("Hello ");
    }
    public Authenticator(){
    }

	/**
	 * CheckLogin is called from components.xml and is front door to authentication process.
	 */
    public boolean checkLogin() 
    {
        boolean isLoggedIn = identity.isLoggedIn();
        // user may already be logged in, check.
        if (isLoggedIn) {
            this.setIsAuthentiated(true);
        } 
        else 
        {
        	try 
        	{
               // try single sign on - auto login.
                this.setIsAuthentiated(this.Authenticate());
                 if (this.getIsAuthenticated())
                 {
                    // authenticated
                    this.isAuthenticated = true;
                 }
                else 
                {
                	if(userAccount!=null && userAccount.getLastName()!=null && userAccount.getFirstName()!=null )
                		{
                			log.warn("User {0} failed to be authenticated via Authenticator.",
                    		 userAccount.getLastName() + ", " + userAccount.getFirstName());
                		}
                	else
	                	{
                		log.warn("User failed to be authenticated via Authenticator.");
	                	}
                    this.setIsAuthentiated(false);
                }
        	}
            catch (Exception e) 
            {
                e.printStackTrace();
                log.warn("Warning, user unable to be authenticated to Space Survey application {0}",e.getMessage());
                this.setIsAuthentiated(false);
            }                                      
        }
        return this.getIsAuthenticated();
    } 

    /**
    * Primary method to determine credentials by database lookup.
    */
    public boolean Authenticate() {
	    // are we successful?
	    boolean llReturn = false;
	    // first get session header variables from campus SSO
	    llReturn = this.getSessionVariables();
	    // now, all session header variables are part of class
	    // next, query the jpaIdentityManager table UserAccount
	    if (llReturn) 
		  {
	        // reset so we can check if user is authenticated
	        llReturn = false;
	        try {
	                // priority is if we have a UCSD Employee ID, try this first. development mode localhost will have EID
	                if (!StringUtils.isEmpty(this.getEID()) && Integer.parseInt(this.getEID().trim()) > 0 )
	                {                                                       
	                   userAccount = (UserAcct) entityManager.
	                		   					createQuery
	                		   					(
	                		   						EmployeeRepository.userAuth
	                		   					 )
	                                             .setParameter("ucsdId",String.valueOf(Integer.valueOf(this.getEID()))).getSingleResult();
	                }
	                // otherwise, must assume that this is affiliate with RACFID
	                else if (!StringUtils.isEmpty(this.getRACFID())) 
	                {
	                    // query for racfid stored in name field
	                	// check how to populate Affiliate data
	                	userAccount = (UserAcct) entityManager.
	                							 createQuery
	                							 (
	                									 EmployeeRepository. userAuthRACFID
	                							  )
                                                .setParameter("nameRACFID", this.getRACFID())
                                                .getSingleResult();
	                } 
	                else 
	                {
	                  userAccount = null;
	                }
	                
	                // we must have a user account,otherwise we are not authenticated - under any condition.
	                if (userAccount == null || userAccount.getAccountId().equals(null) || 
	                    StringUtils.isEmpty(userAccount.getName()))
	                {
                        log.warn("Cannot authenticate user EID: {0} RACFID: {1}", this.getEID(), this.getRACFID());
                        FacesMessages.instance().add("Cannot authenticate user EID: {0} RACFID: {1}", this.getEID(), this.getRACFID());
	                    llReturn = false;
	                    return llReturn;
	                                
	                } 
	                else 
	                {
                        // credentials username
                        credentials.setUsername(userAccount.getUsername());
                        // org.apache.log4j.MDC - allow for logging the users full name
                        // also - see just adding %X{username} to the pattern.
                        MDC.put("fullUserName",userAccount.getLastName() + ", " + userAccount.getFirstName());                                                                   
	                } 
	                
	                // must have some roles
	                if (userAccount.getUserAcctRoles() != null && userAccount.getUserAcctRoles().size()>0) 
	                 {
	                    // check for roles
	                    Boolean hasMinimumRole = false;
	                    if(userAccount.getUserAcctRoles().get(0).getUserRole().getRole().toString().equals("stip_admin"))
	                    	identity.setPrimaryRole("stip_admin");
	                    else if(userAccount.getUserAcctRoles().get(0).getUserRole().getRole().toString().equals("dbo"))
	                    	identity.setPrimaryRole("dbo");
	                    else if(userAccount.getUserAcctRoles().get(0).getUserRole().getRole().toString().equals("stip_user"))
	                    	identity.setPrimaryRole("stip_user");
	                    
	                    log.info("user "+userAccount.getUsername()+" Roles:");
	                    
	                    for (UserAcctRole mr : userAccount.getUserAcctRoles()) 
	                    	{
	                    	   log.info(mr.getUserAcct().getFirstName()+"---"+mr.getUserRole().getRole());
                                identity.addRole(mr.getUserRole().getRole().toString());
                                identity.setFirstName(mr.getUserAcct().getFirstName());
                				identity.setLastName(mr.getUserAcct().getLastName());
                				identity.setFullName(mr.getUserAcct().getName());
                				identity.setAccountId(mr.getUserAcct().getAccountId());
                				identity.setUcsdId(mr.getUserAcct().getUcsdId());
                                hasMinimumRole = true;
	                    	} 
		                    if ( hasMinimumRole == true ) 
		                    {
		                      llReturn = true;
		                    }
		                    else
		                    { 
                                llReturn = false;
		                    }
			            }
	        } catch (NoResultException ex) {
	                        log.error(ex.getMessage() + ", NoResultException thrown. Invalid username or password");
	                        FacesMessages.instance().add("Error, NoResultException thrown. Invalid username or password");
	                        llReturn = false;
	                        return llReturn;
	        } catch (Exception e) {
	                        log.error(e.getMessage() + ", Generic error trapped during sql query.", e);
	                        llReturn = false;
	                        return llReturn;
	        }
	     }
                    return llReturn;
    }

    /**
    * Provides ability to query users browser session header for header variables from SSO
    */
  public boolean getSessionVariables() 
  {
    boolean llReturn = false;
    // seam methods to pull session information
    ExternalContext externalContext = facesContext.getExternalContext();
    HttpSession session = (HttpSession) externalContext.getSession(true);
    // check for a proper session object
    if (session == null) 
    {
        log.error("No session found in externalContext from SpaceAuthenticator.authenticate.");
        llReturn = false;
    }
    else 
    {
        // make calls to extract user credentials from http session
    	try 
    		{
                String lcKey = "";
                String lcValue = "";
                // map of keys and values from session header
                Map<String, String> headers = externalContext.getRequestHeaderMap();
                java.util.Iterator<Entry<String, String>> it = headers.entrySet().iterator();
                // loop through all header keys
                while (it.hasNext()) 
                {
	                // map data from http session header
	                @SuppressWarnings("rawtypes")
					Map.Entry pairs = (Map.Entry) it.next();
	                // examine each key and check if it qualifies for access
	                if (pairs.getKey() != null && pairs.getValue() != null) 
	                {
                        lcKey = pairs.getKey().toString().toUpperCase().trim();
                        lcValue = pairs.getValue().toString().toUpperCase().trim();
                        // must not be empty to store
                        if (!StringUtils.isEmpty(lcKey) && !StringUtils.isEmpty(lcValue))
                        {
                            // only currently interested in a couple of keys
                            if (lcKey.equals("HOST")) 
                            {
                               this.setIsLocalHost(lcValue.contains("LOCALHOST"));
                            }
                            if (lcKey.equals("EID")) 
                            {
                               this.setEID(lcValue.toString().trim());
                            }
                            if (lcKey.equals("RACFID")) 
                            {
                               this.setRACFID(lcValue.toString().trim());
                            }                                                                                              
                        } // must not be empty string
	                } // must not be null
                } // end while loop
                
                // process as local host, under development
                if (this.getIsLocalHost())
                {
                    // obtain defaults from environment.properties for local testing
                    this.setEID(environment.getProperty("default.localhost.user-eid"));
                    this.setRACFID(environment.getProperty("default.localhost.racfid"));
                }
                
                // EID is not empty, EID > 0, RACFID is empty
                if (!StringUtils.isEmpty(this.getEID()) && 
                	Integer.parseInt(this.getEID().trim()) > 0 &&  
                	StringUtils.isEmpty(this.getRACFID()))
                {
	                llReturn = true;
                }
                
                // EID is not empty, EID > 0, RACFID is not empty
                else if (!StringUtils.isEmpty(this.getEID()) && 
                		Integer.parseInt(this.getEID().trim()) > 0 && 
                		!StringUtils.isEmpty(this.getRACFID()))  
                {
	                llReturn = true;
                }
                
                // EID is empty, EID <= 0, RACFID is not empty, we will process RACFID
                else if (StringUtils.isEmpty(this.getEID()) || 
                		Integer.parseInt(this.getEID().trim()) <= 0 && 
                		!StringUtils.isEmpty(this.getRACFID())) 
                {
                    llReturn = true;
                }
                
                // EID is empty, RACFID is empty, not local host, nothing to check, failure
                else if ( StringUtils.isEmpty(this.getEID()) || 
                		Integer.parseInt(this.getEID().trim()) <= 0 &&
                		StringUtils.isEmpty(this.getRACFID()) &&  
                		!this.getIsLocalHost())
                {
                    log.warn("Nothing to authenticate with, no EID, no RACFID and NOT localhost.");
                    llReturn = false;
                }
    		} 
        	catch (Exception e) 
        	{
                log.error(e.getMessage() + ". User not authenticated.", e);
                llReturn = false;
        	}
        } 
      return llReturn;
	} 
  
	/*@Remove
	@Destroy
	public void destroy() 
	{
		
	}*/
} 
