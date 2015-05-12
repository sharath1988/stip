package edu.ucsd.som.stip.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import org.hibernate.validator.Length;

@Entity
@Table(name="user_role"
    ,catalog="stip"
)
public class UserRole  implements java.io.Serializable {

	private static final long serialVersionUID = -5356498060280496627L;
	private Integer roleId;
     private Integer version;
     private Boolean conditional;
     private String role;
     private String notes;
     private Integer optlock;
     private Date dateCreated;
     private Integer createdBy;
     private Date dateModified;
     private Integer modifiedBy;
     private List<UserAcctRole> userAcctRoles;

    public UserRole() {
    }

    public UserRole(Boolean conditional, String role, String notes, Integer optlock, Date dateCreated, Integer createdBy, Date dateModified, Integer modifiedBy) {
       this.conditional = conditional;
       this.role = role;
       this.notes = notes;
       this.optlock = optlock;
       this.dateCreated = dateCreated;
       this.createdBy = createdBy;
       this.dateModified = dateModified;
       this.modifiedBy = modifiedBy;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="role_id", unique=true, nullable=false)
    public Integer getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Version
    @Column(name="version")
    public Integer getVersion() {
        return this.version;
    }
    
    public void setVersion(Integer version) {
        this.version = version;
    }

    
    @Column(name="conditional")
    public Boolean getConditional() {
        return this.conditional;
    }
    
    public void setConditional(Boolean conditional) {
        this.conditional = conditional;
    }

    
    @Column(name="role", length=100)
    @Length(max=100)
    public String getRole() {
        return this.role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }

    
    @Column(name="notes")
    public String getNotes() {
        return this.notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
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

    @OneToMany(fetch=FetchType.LAZY, mappedBy="userRole")
    public List<UserAcctRole> getUserAcctRoles() {
        return this.userAcctRoles;
    }
    
    public void setUserAcctRoles(List<UserAcctRole> userAcctRoles) {
        this.userAcctRoles = userAcctRoles;
    }


}


