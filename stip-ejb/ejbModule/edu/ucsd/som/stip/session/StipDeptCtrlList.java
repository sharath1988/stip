package edu.ucsd.som.stip.session;

import java.util.Arrays;

import javax.persistence.EntityManager;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import edu.ucsd.som.stip.entity.StipDept;
import edu.ucsd.som.stip.entity.StipDeptCtrl;

@Name("stipDeptCtrlList")
public class StipDeptCtrlList extends EntityQuery<StipDept>
{

	private static final long serialVersionUID = 8117174009403388369L;

	@In
	EntityManager entityManager;
	
	private static final String EJBQL = "select stipDept from StipDeptCtrl stipDept where stipDept.dept is not null and stipDept.dept <> '' ";

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

    private StipDeptCtrl stipDept = new StipDeptCtrl();

    public StipDeptCtrlList()
    {
        setEjbql(EJBQL);
        setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
    }

    public StipDeptCtrl getStipDept()
    {
        return stipDept;
    }
    
    
    
    public void enableDiable()
    {
    	StipDeptCtrl dept=entityManager.find(StipDeptCtrl.class, stipDept.getStipdeptId());
    	dept.setStatus(!dept.getStatus());
    	entityManager.persist(dept);
    }
}
