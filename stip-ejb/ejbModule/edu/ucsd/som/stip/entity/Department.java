package edu.ucsd.som.stip.entity;
/**
 * @author         Sharath A.
 */
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.Length;

@Entity
@Table(name="department",catalog="stip")
public class Department  implements java.io.Serializable {

	private static final long serialVersionUID = 2343692050212141310L;
	 private Integer departmentId;
     private Integer majorGroupId;
     private Integer facDeptKey;
     private String deptLongName;
     private String deptShortName;
     private String deptAbbrev;
     private Character vcUnitCode;
     private Integer optlock;
     private Date dateCreated;
     private Integer createdBy;
     private Date dateModified;
     private Integer modifiedBy;
     private List<UserAcctDept> userAcctDepts ;


    public Department() {
    }

    public Department(Integer majorGroupId, Integer facDeptKey, String deptLongName, String deptShortName, String deptAbbrev, 
    				Character vcUnitCode, Integer optlock, Date dateCreated, Integer createdBy, Date dateModified,
    				Integer modifiedBy, List<UserAcctDept> userAcctDepts) {
       this.majorGroupId = majorGroupId;
       this.facDeptKey = facDeptKey;
       this.deptLongName = deptLongName;
       this.deptShortName = deptShortName;
       this.deptAbbrev = deptAbbrev;
       this.vcUnitCode = vcUnitCode;
       this.optlock = optlock;
       this.dateCreated = dateCreated;
       this.createdBy = createdBy;
       this.dateModified = dateModified;
       this.modifiedBy = modifiedBy;
       this.userAcctDepts = userAcctDepts;
    }
   
    @Column(name="major_group_id")
    public Integer getMajorGroupId() {
        return this.majorGroupId;
    }
    
    public void setMajorGroupId(Integer majorGroupId) {
        this.majorGroupId = majorGroupId;
    }

    
    @Column(name="fac_dept_key")
    public Integer getFacDeptKey() {
        return this.facDeptKey;
    }
    
    public void setFacDeptKey(Integer facDeptKey) {
        this.facDeptKey = facDeptKey;
    }

    @Id 
    @Column(name="department_id", unique=true, nullable=false)
    public Integer getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
    
    @Column(name="dept_long_name", length=100)
    @Length(max=100)
    public String getDeptLongName() {
        return this.deptLongName;
    }
    
    public void setDeptLongName(String deptLongName) {
        this.deptLongName = deptLongName;
    }

    
    @Column(name="dept_short_name", length=20)
    @Length(max=20)
    public String getDeptShortName() {
        return this.deptShortName;
    }
    
    public void setDeptShortName(String deptShortName) {
        this.deptShortName = deptShortName;
    }

    
    @Column(name="dept_abbrev", length=4)
    @Length(max=4)
    public String getDeptAbbrev() {
        return this.deptAbbrev;
    }
    
    public void setDeptAbbrev(String deptAbbrev) {
        this.deptAbbrev = deptAbbrev;
    }

    
    @Column(name="vc_unit_code", length=1)
    public Character getVcUnitCode() {
        return this.vcUnitCode;
    }
    
    public void setVcUnitCode(Character vcUnitCode) {
        this.vcUnitCode = vcUnitCode;
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


@OneToMany(fetch=FetchType.LAZY, mappedBy="department")
    public List<UserAcctDept> getUserAcctDepts() {
        return this.userAcctDepts;
    }
    
    public void setUserAcctDepts(List<UserAcctDept> userAcctDepts) {
        this.userAcctDepts = userAcctDepts;
    }


}


