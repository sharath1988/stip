package edu.ucsd.som.stip.entity;
/**
 * @author         Sharath A.
 */
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.Length;

@Entity
@Table(name="user_acct",catalog="stip")

public class UserAcct  implements java.io.Serializable {

	private static final long serialVersionUID = 3177931909143974467L;
	private Integer accountId;
     private String ucsdId;
     private String name;
     private Boolean enabled;
     private String username;
     private String passwordHash;
     private Integer coreUserId;
     private String firstName;
     private String middleName;
     private String lastName;
     private String email;
     private String funcArea;
     private String employeeId;
     private Integer optlock;
     private Date dateCreated;
     private Integer createdBy;
     private Date dateModified;
     private Integer modifiedBy;
     private List<UserAcctRole> userAcctRoles;
     private List<UserAcctDept> userAcctDepts;
    // private List<PiManager> piManagersForManagerId;
     //private List<PiManager> piManagersForAccountId;

    public UserAcct() {
    }

    public UserAcct(String ucsdId, String name, Boolean enabled, String username, String passwordHash, Integer coreUserId, String firstName, String middleName, String lastName, String email, String funcArea, String employeeId, Integer optlock, Date dateCreated, Integer createdBy, Date dateModified, Integer modifiedBy, List<UserAcctRole> userAcctRoles, List<UserAcctDept> userAcctDepts) {
       this.ucsdId = ucsdId;
       this.name = name;
       this.enabled = enabled;
       this.username = username;
       this.passwordHash = passwordHash;
       this.coreUserId = coreUserId;
       this.firstName = firstName;
       this.middleName = middleName;
       this.lastName = lastName;
       this.email = email;
       this.funcArea = funcArea;
       this.employeeId = employeeId;
       this.optlock = optlock;
       this.dateCreated = dateCreated;
       this.createdBy = createdBy;
       this.dateModified = dateModified;
       this.modifiedBy = modifiedBy;
       this.userAcctRoles = userAcctRoles;
       this.userAcctDepts = userAcctDepts;
    }
    
    public UserAcct(String ucsdId, String name, Boolean enabled,  Integer coreUserId, String firstName, String middleName, String lastName, String email, String funcArea, String employeeId, Date dateCreated) {
        this.ucsdId = ucsdId;
        this.name = name;
        this.enabled = enabled;
        this.coreUserId = coreUserId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.funcArea = funcArea;
        this.employeeId = employeeId;
        this.dateCreated = dateCreated;
     }
    
    public UserAcct(Integer accountId)
    {
         this.accountId=accountId;
     }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="account_id", unique=true, nullable=false)
    public Integer getAccountId() {
        return this.accountId;
    }
    
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    
    @Column(name="ucsd_id", length=10)
    @Length(max=10)
    public String getUcsdId() {
        return this.ucsdId;
    }
    
    public void setUcsdId(String ucsdId) {
        this.ucsdId = ucsdId;
    }

    
    @Column(name="name", length=45)
    @Length(max=45)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="enabled")
    public Boolean getEnabled() {
        return this.enabled;
    }
    
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    
    @Column(name="username")
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    
    @Column(name="password_hash")
    public String getPasswordHash() {
        return this.passwordHash;
    }
    
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    
    @Column(name="core_user_id")
    public Integer getCoreUserId() {
    	return this.coreUserId;        
    }
    
    public void setCoreUserId(Integer coreUserId) {
        this.coreUserId = coreUserId;
    }

    
    @Column(name="first_name", length=50)
    @Length(max=50)
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    
    @Column(name="middle_name", length=50)
    @Length(max=50)
    public String getMiddleName() {
        return this.middleName;
    }
    
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    
    @Column(name="last_name", length=75)
    @Length(max=75)
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
    @Column(name="email", length=100)
    @Length(max=100)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="func_area", length=255)
    @Length(max=255)
    public String getFuncArea() {
        return this.funcArea;
    }
    
    public void setFuncArea(String funcArea) {
        this.funcArea = funcArea;
    }

    
    @Column(name="employee_id")
    public String getEmployeeId() {
        return this.employeeId;
    }
    
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    
    @Column(name="optlock")
    public Integer getOptlock() {
        return this.optlock;
    }
    
    public void setOptlock(Integer optlock) {
        this.optlock = optlock;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_created", length=19)
    public Date getDateCreated() {
        return this.dateCreated;
    }
    
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    
    @Column(name="created_by")
    public Integer getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_modified", length=19)
    public Date getDateModified() {
        return this.dateModified;
    }
    
    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    
    @Column(name="modified_by")
    public Integer getModifiedBy() {
        return this.modifiedBy;
    }
    
    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="userAcct")
@Cascade({CascadeType.DELETE})
    public List<UserAcctRole> getUserAcctRoles() {
        return this.userAcctRoles;
    }
    
    public void setUserAcctRoles(List<UserAcctRole> userAcctRoles) {
        this.userAcctRoles = userAcctRoles;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="userAcct")
@Cascade({CascadeType.DELETE})
    public List<UserAcctDept> getUserAcctDepts() {
        return this.userAcctDepts;
    }
    
    public void setUserAcctDepts(List<UserAcctDept> userAcctDepts) {
        this.userAcctDepts = userAcctDepts;
    }

}


