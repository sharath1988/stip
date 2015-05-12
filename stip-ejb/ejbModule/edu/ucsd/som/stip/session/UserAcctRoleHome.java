package edu.ucsd.som.stip.session;
/**
 * @author         Sharath A.
 */
import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityHome;

import edu.ucsd.som.stip.entity.UserAcct;
import edu.ucsd.som.stip.entity.UserAcctRole;
import edu.ucsd.som.stip.entity.UserAcctRoleId;
import edu.ucsd.som.stip.entity.UserRole;

@Name("userAcctRoleHome")
@Scope( ScopeType.CONVERSATION)
public class UserAcctRoleHome extends EntityHome<UserAcctRole>
{

	private static final long serialVersionUID = 1L;
	@In(create=true)
    UserRoleHome userRoleHome;
    @In(create=true)
    UserAcctHome userAcctHome;
    @In(create=true)
    UserAcctDeptHome userAcctDeptHome;
    @In EntityManager entityManager;
    @In BadgIdentity identity;
    
    private UserRole selectedRole;

	public void setUserAcctRoleId(UserAcctRoleId id)
    {
        setId(id);
    }

    public UserAcctRoleId getUserAcctRoleId()
    {
        return (UserAcctRoleId) getId();
    }

    public UserAcctRoleHome()
    {
        setUserAcctRoleId( new UserAcctRoleId() );
    }

    @Override
    public boolean isIdDefined()
    {
        if ( getUserAcctRoleId().getRoleId()==0 ) return false;
        if ( getUserAcctRoleId().getAccountId()==0 ) return false;
        return true;
    }
    @Override
    public String remove()
    {
        return super.remove();
    }

    @Override
    protected UserAcctRole createInstance()
    {
        UserAcctRole userAcctRole = new UserAcctRole();
        userAcctRole.setId( new UserAcctRoleId() );
        return userAcctRole;
    }

    public void load()
    {
        if (isIdDefined())
        {
            wire();
        }
    }

    public void wire()
    {
    	 getInstance();
        UserRole userRole=userRoleHome.getDefinedInstance();
        if ( userRole!=null )
        {
           getInstance().setUserRole(userRole);
        }
        UserAcct userAcct=userAcctHome.getDefinedInstance();
        if ( userAcct!=null )
        {
           getInstance().setUserAcct(userAcct);
        }
    }

    public boolean isWired()
    {
        if ( getInstance().getUserRole()==null ) return false;
        if ( getInstance().getUserAcct()==null ) return false;
        return true;
    }

    public UserAcctRole getDefinedInstance()
    {
        return isIdDefined() ? getInstance() : null;
    }
    
    public String removeUserRole()
    {
 	   	  this.setUserAcctRoleId(new UserAcctRoleId(this.userRoleHome.getUserRoleRoleId(),this.userAcctHome.getUserAcctAccountId()));
	 	  String s= super.remove();
	 	  setInstance(this.createInstance());
	 	  return s;    
    }
    public String  saveAcctRole()
    {
	    this.getUserAcctRoleId().setAccountId((Integer) userAcctHome.getId());
	    getInstance().setId( this.getUserAcctRoleId());
	    getInstance().setUserAcct(userAcctHome.getInstance());
	    UserRole ur=  entityManager.find(UserRole.class, this.getUserAcctRoleId().getRoleId());
	    getInstance().setUserRole(ur);
	    String s= super.persist();
	    setInstance(this.createInstance());
	    return s;
    }
    
    public void updateRole() {
    	entityManager.refresh(userAcctHome.getInstance());
    	UserAcctRole uar=userAcctHome.getInstance().getUserAcctRoles().get(0);
    	if(uar.getUserRole().getRoleId()!=selectedRole.getRoleId())
    	{
	    	UserAcctRole newUar=new UserAcctRole();
	    	newUar.setId(new UserAcctRoleId(selectedRole.getRoleId(),userAcctHome.getInstance().getAccountId()));
	    	newUar.setUserAcct(userAcctHome.getInstance());
	    	newUar.setUserRole(selectedRole);
	    	newUar.setIsPrimary(true);
	    	entityManager.persist(newUar);
	    	entityManager.remove(uar);
	    	if(identity.getAccountId()==newUar.getUserAcct().getAccountId())
	    	{
		    	identity.addRole(newUar.getUserRole().getRole().toString());
		    	identity.removeRole(uar.getUserRole().getRole().toString());
	    	}
	    	
    	}
	}
    
    public UserRole getSelectedRole() {
		return selectedRole;
	}

	public void setSelectedRole(UserRole selectedRole) {
		this.selectedRole = selectedRole;
	}
}

