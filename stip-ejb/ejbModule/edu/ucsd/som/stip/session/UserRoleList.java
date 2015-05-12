package edu.ucsd.som.stip.session;
/**
 * @author         Sharath A.
 */
import java.util.Arrays;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import edu.ucsd.som.stip.entity.UserRole;

@Name("userRoleList")
public class UserRoleList extends EntityQuery<UserRole>
{

	private static final long serialVersionUID = 1L;

	private static final String EJBQL = "select userRole from UserRole userRole";

    private static final String[] RESTRICTIONS = {
        "lower(userRole.role) like lower(concat(#{userRoleList.userRole.role},'%'))",
        "lower(userRole.notes) like lower(concat(#{userRoleList.userRole.notes},'%'))",
    };

    private UserRole userRole = new UserRole();

    public UserRoleList()
    {
        setEjbql(EJBQL);
        setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
       // setMaxResults(25);
    }

    public UserRole getUserRole()
    {
        return userRole;
    }
}
