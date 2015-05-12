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
import javax.persistence.Transient;

import org.hibernate.validator.NotNull;

@Entity
@Table(name="user_acct_role",catalog="stip")
public class UserAcctRole  implements java.io.Serializable {

	private static final long serialVersionUID = 204259686272809008L;
	private UserAcctRoleId id;
     private UserRole userRole;
     private UserAcct userAcct;
     private Boolean isPrimary;
     private Integer optlock;
     private Date dateCreated;
     private Integer createdBy;
     private Date dateModified;
     private Integer modifiedBy;
     private String departmentName;

    public UserAcctRole() {
    }

	
    public UserAcctRole(UserAcctRoleId id, UserRole userRole, UserAcct userAcct) {
        this.id = id;
        this.userRole = userRole;
        this.userAcct = userAcct;
    }
    
    public UserAcctRole(UserAcctRoleId id) {
        this.id = id;
    }
    
    public UserAcctRole(UserAcctRoleId id, UserRole userRole, UserAcct userAcct, Boolean isPrimary, Integer optlock, Date dateCreated, Integer createdBy, Date dateModified, Integer modifiedBy) {
       this.id = id;
       this.userRole = userRole;
       this.userAcct = userAcct;
       this.isPrimary = isPrimary;
       this.optlock = optlock;
       this.dateCreated = dateCreated;
       this.createdBy = createdBy;
       this.dateModified = dateModified;
       this.modifiedBy = modifiedBy;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="roleId", column=@Column(name="role_id", nullable=false) ), 
        @AttributeOverride(name="accountId", column=@Column(name="account_id", nullable=false) ) } )
    @NotNull
    public UserAcctRoleId getId() {
        return this.id;
    }
    
    public void setId(UserAcctRoleId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="role_id", nullable=false, insertable=false, updatable=false)
    @NotNull
    public UserRole getUserRole() {
        return this.userRole;
    }
    
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
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


    @Transient
	public String getDepartmentName() {
		return departmentName;
	}


	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}




}


