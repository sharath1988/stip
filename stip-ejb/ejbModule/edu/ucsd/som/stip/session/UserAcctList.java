package edu.ucsd.som.stip.session;
/**
 * @author         Sharath A.
 */
import java.util.Arrays;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import edu.ucsd.som.stip.entity.UserAcct;

@Name("userAcctList")
public class UserAcctList extends EntityQuery<UserAcct>
{

	private static final long serialVersionUID = 1L;
	
	private static final String EJBQL = "select userAcct from UserAcct userAcct";

    private static final String[] RESTRICTIONS = {
        "lower(userAcct.ucsdId) like lower(concat(#{userAcctList.userAcct.ucsdId},'%'))",
        "lower(userAcct.name) like lower(concat(#{userAcctList.userAcct.name},'%'))",
        "lower(userAcct.username) like lower(concat(#{userAcctList.userAcct.username},'%'))",
        "lower(userAcct.passwordHash) like lower(concat(#{userAcctList.userAcct.passwordHash},'%'))",
        "lower(userAcct.firstName) like lower(concat(#{userAcctList.userAcct.firstName},'%'))",
        "lower(userAcct.middleName) like lower(concat(#{userAcctList.userAcct.middleName},'%'))",
        "lower(userAcct.lastName) like lower(concat(#{userAcctList.userAcct.lastName},'%'))",
        "lower(userAcct.email) like lower(concat(#{userAcctList.userAcct.email},'%'))",
        "lower(userAcct.funcArea) like lower(concat(#{userAcctList.userAcct.funcArea},'%'))",
    };

    private UserAcct userAcct;
    
    private String ucsdId;

    public UserAcctList()
    {
    	userAcct = new UserAcct();
        setEjbql(EJBQL);
        setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
    }

    public UserAcct getUserAcct()
    {
        return userAcct;
    }
    
	public String getUcsdId() {
		return ucsdId;
	}

	public void setUcsdId(String ucsdId) {
		this.ucsdId = ucsdId;
	}
}
