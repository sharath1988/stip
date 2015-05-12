package edu.ucsd.som.stip.session;
/**
 * @author         Sharath A.
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityHome;

import edu.ucsd.som.stip.entity.Db2employee;
import edu.ucsd.som.stip.entity.Department;
import edu.ucsd.som.stip.entity.UserAcct;
import edu.ucsd.som.stip.entity.UserAcctDept;
import edu.ucsd.som.stip.entity.UserAcctDeptId;
import edu.ucsd.som.stip.entity.UserAcctRole;
import edu.ucsd.som.stip.entity.UserAcctRoleId;
import edu.ucsd.som.stip.entity.UserRole;
import edu.ucsd.som.stip.services.EmployeeRepository;

@Name("userAcctHome")
@Scope( ScopeType.CONVERSATION)
public class UserAcctHome extends EntityHome<UserAcct> implements Serializable
{
	
	private static final long serialVersionUID = -2917992010935235767L;

	@In
	private EntityManager entityManager;
	
	@In(create=true)
	private UserAcctRoleHome userAcctRoleHome;
	
	@In(create=true)
	private UserAcctDeptHome userAcctDeptHome;
	
	private Integer roleId;
	
	private Integer departmentId;
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}


	public void setUserAcctAccountId(Integer id)
    {
        setId(id);
    }

    public Integer getUserAcctAccountId()
    {
        return (Integer) getId();
    }

    @Override
    protected UserAcct createInstance()
    {
        UserAcct userAcct = new UserAcct();
        return userAcct;
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

    public UserAcct getDefinedInstance()
    {
        return isIdDefined() ? getInstance() : null;
    }

    public List<UserAcctRole> getUserAcctRoles() {
        return getInstance() == null ?null : 
        	(getInstance().getUserAcctRoles()==null ? new ArrayList<UserAcctRole>() : new ArrayList<UserAcctRole>( getInstance().getUserAcctRoles() ));
    }
    public List<UserAcctDept> getUserAcctDepts() {
        return getInstance() == null ? null : 
        	(getInstance().getUserAcctDepts()==null ? new ArrayList<UserAcctDept>() : new ArrayList<UserAcctDept>( getInstance().getUserAcctDepts() ));
    }
    
    public void adduser()
    {
    	if(entityManager.createQuery(EmployeeRepository.userByUcsdID).setParameter("ucsdId", getInstance().getUcsdId()).getResultList().size()>0);
    	  
    	else
    		super.persist();
    }
    
    @SuppressWarnings("unchecked")
    public List<UserRole> getRoles() {
      Query q= entityManager.createQuery(EmployeeRepository.rolesQuery);
      q.setParameter("accountId",this.getUserAcctAccountId() );
      return   (List<UserRole>)q.getResultList();
    
    	 
    }
    
    @SuppressWarnings("unchecked")
	@End(beforeRedirect=true)
	public String addUserAcctRoles()
    {
    	
    	UserAcct user=getInstance();
    	//user.setCreatedBy(identity.getCredentials().getPassword());
    	//adding user and role only if the user not exist previously 
    	if(entityManager.createQuery(EmployeeRepository.userByUcsdID)
    					.setParameter("ucsdId",  String.valueOf(user.getCoreUserId()))
    					.getResultList().size()==0)
    	{
    				List<Db2employee> dbEmployees=(List<Db2employee>)entityManager.
    															   createQuery(EmployeeRepository.userFromDb2Emp).
    															   setParameter("ucsdId", user.getCoreUserId()).
    															   getResultList();
		    	if(dbEmployees.size()==0)
		    	{
		        		 FacesContext.getCurrentInstance().addMessage(null,
		    	                    new FacesMessage(FacesMessage.SEVERITY_ERROR,"Employee not valid. donot change the Employee ID",
		    	                    								"Employee not valid. donot change the Employee ID"));
		        		 			return null;
		    	}
    	
		    	else if(dbEmployees.size()==1)
		    	{
		    		Db2employee dbEmployee=dbEmployees.get(0);
		    		List<String> emails=(List<String>)entityManager.createNativeQuery(EmployeeRepository.employeeEmailQuery).
													    			setParameter("empNumber", dbEmployee.getEmbEmployeeNumber()).
																	setParameter("emptyString", "").
																	getResultList();
					user.setUcsdId(String.valueOf(user.getCoreUserId())); 
					user.setName(dbEmployee.getEmbEmployeeName());
					user.setEnabled(true);
					user.setFirstName(dbEmployee.getEmpFirstName());
					user.setMiddleName(dbEmployee.getEmpMiddleName());
					user.setLastName(dbEmployee.getEmpLastName());
					if(emails.size()>0 && emails.get(0)!=null)
					user.setEmail(emails.get(0));
					user.setFuncArea(dbEmployee.getEmpEmpHomeDepartmentName());
					user.setEmployeeId(String.valueOf(user.getCoreUserId()));
					user.setDateCreated(new Date());
					user.setDateModified(new Date());
					user.setOptlock(0);
			    	setInstance(user);
			    	
			    	entityManager.persist(user);
			    	 //adding dept to user
			    		 getUserAcctDeptHome().getInstance().setUserAcct(user);
			    		 getUserAcctDeptHome().getInstance().setDepartment(entityManager.find(Department.class,this.getDepartmentId()));
			    		 getUserAcctDeptHome().getInstance().setId(new UserAcctDeptId(user.getAccountId(),this.getDepartmentId()));
			    		 getUserAcctDeptHome().getInstance().setIsPrimary(true);
			    		 getUserAcctDeptHome().persist();
			    	
			    	
			    	//adding role to user
			    	 getUserAcctRoleHome().getInstance().setUserAcct(user);
			    	 getUserAcctRoleHome().getInstance().setUserRole(entityManager.find(UserRole.class,this.getRoleId()));
			    	 getUserAcctRoleHome().getInstance().setId(new UserAcctRoleId(this.getRoleId(), user.getAccountId()));
			    	 getUserAcctRoleHome().getInstance().setIsPrimary(true);
			    	 getUserAcctRoleHome().persist();
			    	 
									    	 
		    	}
				    	 
    		}
				    	return "/admin/views/UserList.seam";
    	}

    
    
    
   	
   	@End(beforeRedirect=true)
	public String cancel()
	{
		return "/admin/views/UserList.seam";
	}
   	
	@SuppressWarnings("unchecked")
	public List<UserAcct> getNames(Object in) {
        String fullName = in.toString().toUpperCase() + "%";
		return (List<UserAcct>)entityManager.
				createQuery(EmployeeRepository.userByName).
								setParameter("name", fullName).setMaxResults(30).getResultList();
	}
    
	@SuppressWarnings("unchecked")
	public List<String> renderEditpage(int aactId)
	{
		return (List<String>)entityManager.
				createQuery(EmployeeRepository.editLinkRender).
				setParameter("accountId", aactId).getResultList();
	}
    
	
    
    
	public UserAcctRoleHome getUserAcctRoleHome() {
		return userAcctRoleHome;
	}

	public void setUserAcctRoleHome(UserAcctRoleHome userAcctRoleHome) {
		this.userAcctRoleHome = userAcctRoleHome;
	}

	public UserAcctDeptHome getUserAcctDeptHome() {
		return userAcctDeptHome;
	}

	public void setUserAcctDeptHome(UserAcctDeptHome userAcctDeptHome) {
		this.userAcctDeptHome = userAcctDeptHome;
	}

	public boolean getDboRole()
	{
		for(UserAcctRole r:this.getUserAcctRoles())
		{
			if(r.getId().getRoleId()==3)
			{
				return true;
			}
			
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserAcctDept> getUserAcctDepartments()
	{
		return (List<UserAcctDept>)entityManager.createQuery("select uad from UserAcctDept uad where uad.userAcct.accountId=:accountId").
				setParameter("accountId", this.getInstance().getAccountId()).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<UserAcctDept> getUserAcctRols()
	{
		return (List<UserAcctDept>)entityManager.createQuery("select uar from UserAcctRole uar where uar.userAcct.accountId=:accountId").
				setParameter("accountId", this.getInstance().getAccountId()).getResultList();
	}
	
	public void resetUser()
	{
		this.clearInstance();
		this.roleId=null;
		this.departmentId=null;
	}
	
	
}	

