package edu.ucsd.som.stip.session;
/**
 * @author         Sharath A.
 */
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityHome;

import edu.ucsd.som.stip.entity.Department;
import edu.ucsd.som.stip.entity.UserAcct;
import edu.ucsd.som.stip.entity.UserAcctDept;
import edu.ucsd.som.stip.entity.UserAcctDeptId;
import edu.ucsd.som.stip.services.EmployeeRepository;

@Name("userAcctDeptHome")
@Scope( ScopeType.CONVERSATION)
public class UserAcctDeptHome extends EntityHome<UserAcctDept>
{

	private static final long serialVersionUID = 1L;
	@In
    UserAcctHome userAcctHome;
    @In(create=true)
    DepartmentHome departmentHome;
    @In EntityManager entityManager;
    @In BadgIdentity identity;
    
   
  

    public void setUserAcctDeptId(UserAcctDeptId id)
    {
        setId(id);
    }

    public UserAcctDeptId getUserAcctDeptId()
    {
        return (UserAcctDeptId) getId();
    }

    public UserAcctDeptHome()
    {
        setUserAcctDeptId( new UserAcctDeptId() );
    }

    @Override
    public boolean isIdDefined()
    {
        if ( getUserAcctDeptId().getAccountId()==0 ) return false;
        if ( getUserAcctDeptId().getDepartmentId()==0 ) return false;
        return true;
    }

    @Override
    protected UserAcctDept createInstance()
    {
        UserAcctDept userAcctDept = new UserAcctDept();
        userAcctDept.setId( new UserAcctDeptId() );
        return userAcctDept;
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
        UserAcct userAcct=userAcctHome.getDefinedInstance();
        if ( userAcct!=null )
        {
           getInstance().setUserAcct(userAcct);
        }
        Department department=departmentHome.getDefinedInstance();
        if ( department!=null )
        {
           getInstance().setDepartment(department);
        }
    }

    public boolean isWired()
    {
        if ( getInstance().getUserAcct()==null ) return false;
        if ( getInstance().getDepartment()==null ) return false;
        return true;
    }

    public UserAcctDept getDefinedInstance()
    {
        return isIdDefined() ? getInstance() : null;
    }

	
	 public String removeUserDept()
     {
  	   this.setUserAcctDeptId(new UserAcctDeptId(this.userAcctHome.getUserAcctAccountId(),this.departmentHome.getDepartmentDepartmentId()) );
  	   return super.remove();
     
     }
	 
	 private List<String> userAcctDeptsSelected;

	public List<String> getUserAcctDeptsSelected() {
		return userAcctDeptsSelected;
	}

	public void setUserAcctDeptsSelected(List<String> userAcctDeptsSelected) {
		this.userAcctDeptsSelected = userAcctDeptsSelected;
	}

	@SuppressWarnings("unchecked")
	public List<Department> getUserAcctDepts() {
		Query q= entityManager.createQuery(EmployeeRepository.getUserDeptsNotAssigned);
	    q.setParameter("accountId", this.userAcctHome.getUserAcctAccountId()) ;
		List<Department> userAcctDepts=(List<Department>)q.getResultList();
		return userAcctDepts;
	}
	
	public String saveUserDepts()
	{
		String persist = null;
		 List<String> userAcctDepts1=	this.userAcctDeptsSelected;
		 for(String dept: userAcctDepts1 )
		 {
			 this.getUserAcctDeptId().setAccountId((Integer) userAcctHome.getId());
			 this.getUserAcctDeptId().setDepartmentId(Integer.valueOf(dept));
			 getInstance().setId( this.getUserAcctDeptId());
			 getInstance().setUserAcct(userAcctHome.getInstance());
			 getInstance().setDepartment(entityManager.find(Department.class,Integer.valueOf(dept)));
			 getInstance().setIsPrimary(true);
			 persist= super.persist();
			 this.userAcctHome.getInstance().getUserAcctDepts().add(this.getInstance());
			 setInstance(this.createInstance());
		 }
		 return persist;
	}
	
	@SuppressWarnings("unchecked")
	public List<Department> userAcctDeptsNotAssigned()
	{
		return (List<Department>)entityManager.createQuery(" from Department d ").
				getResultList();
	}
	
	
	public void removeDept(UserAcctDept acctDept)
	{
		this.setInstance(acctDept);
		super.remove();
		this.userAcctHome.getInstance().getUserAcctDepts().remove(acctDept);
		
	}
	
	public void assignHomeDepttoDept()
	{
		
		for(Department d: userAcctDeptsNotAssigned())
		{
			
			if(d.getDeptLongName()!=null && this.userAcctHome.getInstance().getFuncArea()!=null && 
					d.getDeptLongName().trim().equalsIgnoreCase(this.userAcctHome.getInstance().getFuncArea().trim()))
			{
				this.userAcctHome.setDepartmentId(d.getDepartmentId());
			}
		}
		
	}
	
}

