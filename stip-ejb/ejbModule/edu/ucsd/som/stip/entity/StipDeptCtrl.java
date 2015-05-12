package edu.ucsd.som.stip.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.Length;

@Entity
@Table(name="stip_dept_ctrl"
    ,catalog="stip") 

public class StipDeptCtrl  implements java.io.Serializable {

 	private static final long serialVersionUID = 5597223742862957578L;
 	
	 private Integer stipdeptId;
     private String dept;
     private String division;
     private String stipFund;
     private String indx;
     private String fund;
     private String org;
     private String acct;
     private String notes;
     private Boolean status;
     private Date dateCreated;
     private Integer createdBy;
     private Date dateModified;
     private Integer modifiedBy;

    public StipDeptCtrl() {
    }

    public StipDeptCtrl(String dept, String division, String stipFund, String indx, String fund, String org, String acct, String notes, Boolean status, Date dateCreated, Integer createdBy, Date dateModified, Integer modifiedBy) {
       this.dept = dept;
       this.division = division;
       this.stipFund = stipFund;
       this.indx = indx;
       this.fund = fund;
       this.org = org;
       this.acct = acct;
       this.notes = notes;
       this.status = status;
       this.dateCreated = dateCreated;
       this.createdBy = createdBy;
       this.dateModified = dateModified;
       this.modifiedBy = modifiedBy;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="stipdept_id", unique=true, nullable=false)
    public Integer getStipdeptId() {
        return this.stipdeptId;
    }
    
    public void setStipdeptId(Integer stipdeptId) {
        this.stipdeptId = stipdeptId;
    }

    
    @Column(name="dept", length=50)
    @Length(max=50)
    public String getDept() {
        return this.dept;
    }
    
    public void setDept(String dept) {
        this.dept = dept;
    }

    
    @Column(name="division", length=50)
    @Length(max=50)
    public String getDivision() {
        return this.division;
    }
    
    public void setDivision(String division) {
        this.division = division;
    }

    
    @Column(name="stip_fund", length=6)
    @Length(max=6)
    public String getStipFund() {
        return this.stipFund;
    }
    
    public void setStipFund(String stipFund) {
        this.stipFund = stipFund;
    }

    
    @Column(name="indx", length=7)
    @Length(max=7,min=7)
    public String getIndx() {
        return this.indx;
    }
    
    public void setIndx(String indx) {
        this.indx = indx;
    }

    
    @Column(name="fund", length=6)
    @Length(max=6)
    public String getFund() {
        return this.fund;
    }
    
    public void setFund(String fund) {
        this.fund = fund;
    }

    
    @Column(name="org", length=6)
    @Length(max=6)
    public String getOrg() {
        return this.org;
    }
    
    public void setOrg(String org) {
        this.org = org;
    }

    
    @Column(name="acct", length=6)
    @Length(max=6)
    public String getAcct() {
        return this.acct;
    }
    
    public void setAcct(String acct) {
        this.acct = acct;
    }

    
    @Column(name="notes")
    public String getNotes() {
        return this.notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }

    
    @Column(name="status")
    public Boolean getStatus() {
        return this.status;
    }
    
    public void setStatus(Boolean status) {
        this.status = status;
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


