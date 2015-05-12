package edu.ucsd.som.stip.session;
/**
 * @author         Sharath A.
 */
import javax.ejb.Local;


@Local
public interface IAuthenticator {
	
                // set
                public abstract void setEID(String _ucsdEID);
                public abstract void setRACFID(String _ucsdRACFID);
                public abstract void setIsLocalHost(boolean _isLocalHost);
                public abstract void setIsAuthentiated(boolean _isAuthenticated);

                // get
                public abstract String getEID();
                public abstract String getRACFID();
                public abstract boolean getIsLocalHost();
                public abstract boolean getIsAuthenticated();
                
                public abstract boolean checkLogin();

                /** 
                 * Performs the work to add the successful credentials to the Seam components
                * @return true if authentication is successful
                */
                public abstract boolean Authenticate();

                /**
                * Performs the work of reading the session header variables
                * @return true if session variables are properly retrieved
                */
                public abstract boolean getSessionVariables();

                /*@Remove
                @Destroy
                public abstract void destroy();*/

}
