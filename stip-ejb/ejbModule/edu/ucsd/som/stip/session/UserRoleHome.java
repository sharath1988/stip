package edu.ucsd.som.stip.session;
/**
 * @author         Sharath A.
 */
import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityHome;

import edu.ucsd.som.stip.entity.UserAcctRole;
import edu.ucsd.som.stip.entity.UserRole;

@Name("userRoleHome")
@Scope( ScopeType.CONVERSATION)
public class UserRoleHome extends EntityHome<UserRole>
{

	private static final long serialVersionUID = 1L;

	public void setUserRoleRoleId(Integer id)
    {
        setId(id);
    }

    public Integer getUserRoleRoleId()
    {
        return (Integer) getId();
    }

    @Override
    protected UserRole createInstance()
    {
        UserRole userRole = new UserRole();
        return userRole;
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
    }

    public boolean isWired()
    {
        return true;
    }

    public UserRole getDefinedInstance()
    {
        return isIdDefined() ? getInstance() : null;
    }

    public List<UserAcctRole> getUserAcctRoles() {
        return getInstance() == null ?
            null : new ArrayList<UserAcctRole>( getInstance().getUserAcctRoles() );
    }
    
   

}

