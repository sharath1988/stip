package edu.ucsd.som.stip.entity;
/**
 * @author         Sharath A.
 */
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.validator.NotNull;

@Entity
@Table(name="user_acct_dept",catalog="stip")
public class UserAcctDept  implements java.io.Serializable {

	private static final long serialVersionUID = 2161338522841922097L;
	private UserAcctDeptId id;
     private UserAcct userAcct;
     private Department department;
     private Boolean isPrimary;
     private Integer optlock;
     private Date dateCreated;
     private Integer createdBy;
     private Date dateModified;
     private Integer modifiedBy;

    public UserAcctDept() {
    }

	
    public UserAcctDept(UserAcctDeptId id, UserAcct userAcct, Department department) {
        this.id = id;
        this.userAcct = userAcct;
        this.department = department;
    }
    public UserAcctDept(UserAcctDeptId id, UserAcct userAcct, Department department, Boolean isPrimary, Integer optlock, Date dateCreated, Integer createdBy, Date dateModified, Integer modifiedBy) {
       this.id = id;
       this.userAcct = userAcct;
       this.department = department;
       this.isPrimary = isPrimary;
       this.optlock = optlock;
       this.dateCreated = dateCreated;
       this.createdBy = createdBy;
       this.dateModified = dateModified;
       this.modifiedBy = modifiedBy;
    }
    public UserAcctDept(UserAcctDeptId id, UserAcct userAcct, Department department, Boolean isPrimary) {
        this.id = id;
        this.userAcct = userAcct;
        this.department = department;
        this.isPrimary = isPrimary;
     }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="accountId", column=@Column(name="account_id", nullable=false) ), 
        @AttributeOverride(name="departmentId", column=@Column(name="department_id", nullable=false) ) } )
    @NotNull
    public UserAcctDeptId getId() {
        return this.id;
    }
    
    public void setId(UserAcctDeptId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="account_id", nullable=false, insertable=false, updatable=false)
    @NotNull
    public UserAcct getUserAcct() {
        return this.userAcct;
    }
    
    public void setUserAcct(UserAcct userAcct) {
        this.userAcct = userAcct;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="department_id", nullable=false, insertable=false, updatable=false)
    @NotNull
    public Department getDepartment() {
        return this.department;
    }
    
    public void setDepartment(Department department) {
        this.department = department;
    }

    
    @Column(name="is_primary")
    public Boolean getIsPrimary() {
        return this.isPrimary;
    }
    
    public void setIsPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
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




}


