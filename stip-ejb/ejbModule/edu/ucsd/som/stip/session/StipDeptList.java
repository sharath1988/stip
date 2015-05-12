package edu.ucsd.som.stip.session;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import edu.ucsd.som.stip.entity.StipDept;

@Name("stipDeptList")
public class StipDeptList extends EntityQuery<StipDept>
{

	private static final long serialVersionUID = -3381006434218070812L;
	
	@In
	EntityManager entityManager;
	
	private static final String EJBQL = "select stipDept from StipDept stipDept where stipDept.dept is not null and stipDept.dept <> '' ";

    private static final String[] RESTRICTIONS = {
        "lower(stipDept.dept) like lower(concat(#{stipDeptList.stipDept.dept},'%'))",
        "lower(stipDept.division) like lower(concat(#{stipDeptList.stipDept.division},'%'))",
        "lower(stipDept.stipFund) like lower(concat(#{stipDeptList.stipDept.stipFund},'%'))",
        "lower(stipDept.inde) like lower(concat(#{stipDeptList.stipDept.indx},'%'))",
        "lower(stipDept.fund) like lower(concat(#{stipDeptList.stipDept.fund},'%'))",
        "lower(stipDept.org) like lower(concat(#{stipDeptList.stipDept.org},'%'))",
        "lower(stipDept.acct) like lower(concat(#{stipDeptList.stipDept.acct},'%'))",
        "lower(stipDept.notes) like lower(concat(#{stipDeptList.stipDept.notes},'%'))",
        "lower(stipDept.createdBy) like lower(concat(#{stipDeptList.stipDept.createdBy},'%'))",
    };

    private StipDept stipDept = new StipDept();

    public StipDeptList()
    {
        setEjbql(EJBQL);
        setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
    }

    public StipDept getStipDept()
    {
        return stipDept;
    }
    
    public Set<String> getDeptList()
    {
    	Set<String> returnList=new TreeSet<String>();
    	String tempDept=this.stipDept.getDept();
    	this.stipDept.setDept("");
    		for(StipDept s:this.getResultList())
    		{
    			returnList.add(s.getDept());
    		}
    		this.stipDept.setDept(tempDept);
        return returnList;
    }
    
    public void enableDiable()
    {
    	StipDept dept=entityManager.find(StipDept.class, stipDept.getStipdeptId());
    	dept.setStatus(!dept.getStatus());
    	entityManager.persist(dept);
    }
}
