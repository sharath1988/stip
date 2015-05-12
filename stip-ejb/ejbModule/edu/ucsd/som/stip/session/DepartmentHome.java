package edu.ucsd.som.stip.session;
/**
 * @author         Sharath A.
 */
import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

import edu.ucsd.som.stip.entity.Department;
import edu.ucsd.som.stip.entity.UserAcctDept;

@Name("departmentHome")
public class DepartmentHome extends EntityHome<Department>
{

	private static final long serialVersionUID = -7306756519065688952L;

	public void setDepartmentDepartmentId(Integer id)
    {
        setId(id);
    }

    public Integer getDepartmentDepartmentId()
    {
        return (Integer) getId();
    }

    @Override
    protected Department createInstance()
    {
        Department department = new Department();
        return department;
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

    public Department getDefinedInstance()
    {
        return isIdDefined() ? getInstance() : null;
    }

    public List<UserAcctDept> getUserAcctDepts() {
        return getInstance() == null ?
            null : new ArrayList<UserAcctDept>( getInstance().getUserAcctDepts() );
    }


    
}

