package edu.ucsd.som.stip.session;
/**
 * @author         Sharath A.
 */
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Install;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Startup;
import org.jboss.seam.annotations.intercept.BypassInterceptors;
import org.jboss.seam.security.Identity;
import edu.ucsd.som.stip.util.StipStrings;

@Name("org.jboss.seam.security.identity")
@Scope(ScopeType.SESSION)
@Install(precedence = Install.APPLICATION)
@BypassInterceptors
@Startup
public class BadgIdentity extends Identity {

	private static final long serialVersionUID = 4833121074822625565L;
	private static boolean debugging = false;
		
	
	private String fullName;
	private String firstName;
	private String lastName;
	private int accountId;
	private String ucsdId;
	private String primaryRole;
	
	public BadgIdentity() {}
	
	@Override
	public String login() {
		if (debugging){
		}
		return super.login();
	}

	/**
	 * returns a formatted users name for display purposes
	 * @return A formatted user name
	 */
	public String getFormattedName(){
		return StipStrings.toProperCase(lastName) + ", " + StipStrings.toProperCase(firstName);
	}
	
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getUcsdId() {
		return ucsdId;
	}

	public void setUcsdId(String ucsdId) {
		this.ucsdId = ucsdId;
	}

	public String getPrimaryRole() {
		return primaryRole;
	}

	public void setPrimaryRole(String primaryRole) {
		this.primaryRole = primaryRole;
	}

}
