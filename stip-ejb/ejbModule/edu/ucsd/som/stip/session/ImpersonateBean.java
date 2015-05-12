package edu.ucsd.som.stip.session;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpSession;

import org.hibernate.validator.Length;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Identity;

import edu.ucsd.som.stip.beans.AppInfo;
import edu.ucsd.som.stip.entity.AuthUser;
import edu.ucsd.som.stip.services.UserService;

@Stateful
@Name("impersonate")
public class ImpersonateBean implements Impersonate
{
    @Logger private Log log;
    @In private Identity identity;
    @In private Authenticator authenticator;
    @In private AppInfo appInfo;
    @In private UserService userService;
    private String value;

    public void impersonate(){
        log.info("impersonate.impersonate() action start");
        HttpSession session = appInfo.getSession();
        String userId = value.substring(value.indexOf("(") + 1, value.indexOf(")")); /*EmployeeId or RacfId*/
        if ((identity != null && identity.hasRole("administrator")) 
        		|| ((String)session.getAttribute("impersonating")) != null
        		|| (identity != null && identity.hasRole("impersonating"))) { /*If the person is an administrator/impersonating then only allow to impersonate*/
        	session.setAttribute("impersonating",userId); /* Impersonating */
        	log.info("IMPERSONATING - trying: " + userId);
            if (identity != null ){
            	log.info("Unauthenticating the user");
            	identity.unAuthenticate(); //Logging the current user out
            }
           	try {
           			log.info("Logging in");
           			identity.authenticate(); //Logging in the user in the session
           			if (!identity.isLoggedIn()) {// Trying to see - why identity is not sometimes logging in
           				authenticator.Authenticate(); 
           			}
           			log.info("Logged in");
			} catch (LoginException e) {
				log.info("Login failed, you are not authenticated");
			}
        }
    }

    // add additional action methods
    @Length(max = 40)
    public String getValue(){
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }

    @Remove
    @Destroy
    public void destroy() {}

	public List<String> getUsers() {
		List<String> userStrings = new ArrayList<String>();
		List<AuthUser> users = userService.loadUserList();
		for (AuthUser u : users) {
			userStrings.add(u.getFullName() + " (" + u.getUserId() + ")");
		}
		return userStrings;
	}
    
   

}
