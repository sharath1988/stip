package edu.ucsd.som.stip.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

@Embeddable
public class UcsdIndexId  implements java.io.Serializable {

	private static final long serialVersionUID = -340736783733313545L;
	
	 private String indx;
     private String indxTitle;
     private Date endEffectiveDate;
     private Date earlyInactivationDate;
     private String status;
     private String orgHierarchyOne;
     private String program;
     private String organization;
     private String orgTitle;
     private String fund;
     private String fundTitle;
     private int divisionId;
     private int departmentId;
     private int somMajorGroup;

    public UcsdIndexId() {
    }

	
    public UcsdIndexId(String indx, int divisionId, int departmentId, int somMajorGroup) {
        this.indx = indx;
        this.divisionId = divisionId;
        this.departmentId = departmentId;
        this.somMajorGroup = somMajorGroup;
    }
    public UcsdIndexId(String indx, String indxTitle, Date endEffectiveDate, Date earlyInactivationDate, String status, String orgHierarchyOne, String program, String organization, String orgTitle, String fund, String fundTitle, int divisionId, int departmentId, int somMajorGroup) {
       this.indx = indx;
       this.indxTitle = indxTitle;
       this.endEffectiveDate = endEffectiveDate;
       this.earlyInactivationDate = earlyInactivationDate;
       this.status = status;
       this.orgHierarchyOne = orgHierarchyOne;
       this.program = program;
       this.organization = organization;
       this.orgTitle = orgTitle;
       this.fund = fund;
       this.fundTitle = fundTitle;
       this.divisionId = divisionId;
       this.departmentId = departmentId;
       this.somMajorGroup = somMajorGroup;
    }
   


    @Column(name="indx", nullable=false, length=10)
    @NotNull
    @Length(max=10)
    public String getIndx() {
        return this.indx;
    }
    
    public void setIndx(String indx) {
        this.indx = indx;
    }


    @Column(name="indxTitle", length=35)
    @Length(max=35)
    public String getIndxTitle() {
        return this.indxTitle;
    }
    
    public void setIndxTitle(String indxTitle) {
        this.indxTitle = indxTitle;
    }


    @Column(name="endEffectiveDate", length=0)
    public Date getEndEffectiveDate() {
        return this.endEffectiveDate;
    }
    
    public void setEndEffectiveDate(Date endEffectiveDate) {
        this.endEffectiveDate = endEffectiveDate;
    }


    @Column(name="earlyInactivationDate", length=0)
    public Date getEarlyInactivationDate() {
        return this.earlyInactivationDate;
    }
    
    public void setEarlyInactivationDate(Date earlyInactivationDate) {
        this.earlyInactivationDate = earlyInactivationDate;
    }


    @Column(name="status", length=8)
    @Length(max=8)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }


    @Column(name="orgHierarchyOne", length=7)
    @Length(max=7)
    public String getOrgHierarchyOne() {
        return this.orgHierarchyOne;
    }
    
    public void setOrgHierarchyOne(String orgHierarchyOne) {
        this.orgHierarchyOne = orgHierarchyOne;
    }


    @Column(name="program", length=6)
    @Length(max=6)
    public String getProgram() {
        return this.program;
    }
    
    public void setProgram(String program) {
        this.program = program;
    }


    @Column(name="organization", length=6)
    @Length(max=6)
    public String getOrganization() {
        return this.organization;
    }
    
    public void setOrganization(String organization) {
        this.organization = organization;
    }


    @Column(name="orgTitle")
    public String getOrgTitle() {
        return this.orgTitle;
    }
    
    public void setOrgTitle(String orgTitle) {
        this.orgTitle = orgTitle;
    }


    @Column(name="fund", length=6)
    @Length(max=6)
    public String getFund() {
        return this.fund;
    }
    
    public void setFund(String fund) {
        this.fund = fund;
    }


    @Column(name="fundTitle", length=35)
    @Length(max=35)
    public String getFundTitle() {
        return this.fundTitle;
    }
    
    public void setFundTitle(String fundTitle) {
        this.fundTitle = fundTitle;
    }


    @Column(name="divisionId", nullable=false)
    public int getDivisionId() {
        return this.divisionId;
    }
    
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }


    @Column(name="departmentId", nullable=false)
    public int getDepartmentId() {
        return this.departmentId;
    }
    
    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }


    @Column(name="somMajorGroup", nullable=false)
    public int getSomMajorGroup() {
        return this.somMajorGroup;
    }
    
    public void setSomMajorGroup(int somMajorGroup) {
        this.somMajorGroup = somMajorGroup;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof UcsdIndexId) ) return false;
		 UcsdIndexId castOther = ( UcsdIndexId ) other; 
         
		 return ( (this.getIndx()==castOther.getIndx()) || ( this.getIndx()!=null && castOther.getIndx()!=null && this.getIndx().equals(castOther.getIndx()) ) )
 && ( (this.getIndxTitle()==castOther.getIndxTitle()) || ( this.getIndxTitle()!=null && castOther.getIndxTitle()!=null && this.getIndxTitle().equals(castOther.getIndxTitle()) ) )
 && ( (this.getEndEffectiveDate()==castOther.getEndEffectiveDate()) || ( this.getEndEffectiveDate()!=null && castOther.getEndEffectiveDate()!=null && this.getEndEffectiveDate().equals(castOther.getEndEffectiveDate()) ) )
 && ( (this.getEarlyInactivationDate()==castOther.getEarlyInactivationDate()) || ( this.getEarlyInactivationDate()!=null && castOther.getEarlyInactivationDate()!=null && this.getEarlyInactivationDate().equals(castOther.getEarlyInactivationDate()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
 && ( (this.getOrgHierarchyOne()==castOther.getOrgHierarchyOne()) || ( this.getOrgHierarchyOne()!=null && castOther.getOrgHierarchyOne()!=null && this.getOrgHierarchyOne().equals(castOther.getOrgHierarchyOne()) ) )
 && ( (this.getProgram()==castOther.getProgram()) || ( this.getProgram()!=null && castOther.getProgram()!=null && this.getProgram().equals(castOther.getProgram()) ) )
 && ( (this.getOrganization()==castOther.getOrganization()) || ( this.getOrganization()!=null && castOther.getOrganization()!=null && this.getOrganization().equals(castOther.getOrganization()) ) )
 && ( (this.getOrgTitle()==castOther.getOrgTitle()) || ( this.getOrgTitle()!=null && castOther.getOrgTitle()!=null && this.getOrgTitle().equals(castOther.getOrgTitle()) ) )
 && ( (this.getFund()==castOther.getFund()) || ( this.getFund()!=null && castOther.getFund()!=null && this.getFund().equals(castOther.getFund()) ) )
 && ( (this.getFundTitle()==castOther.getFundTitle()) || ( this.getFundTitle()!=null && castOther.getFundTitle()!=null && this.getFundTitle().equals(castOther.getFundTitle()) ) )
 && (this.getDivisionId()==castOther.getDivisionId())
 && (this.getDepartmentId()==castOther.getDepartmentId())
 && (this.getSomMajorGroup()==castOther.getSomMajorGroup());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getIndx() == null ? 0 : this.getIndx().hashCode() );
         result = 37 * result + ( getIndxTitle() == null ? 0 : this.getIndxTitle().hashCode() );
         result = 37 * result + ( getEndEffectiveDate() == null ? 0 : this.getEndEffectiveDate().hashCode() );
         result = 37 * result + ( getEarlyInactivationDate() == null ? 0 : this.getEarlyInactivationDate().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         result = 37 * result + ( getOrgHierarchyOne() == null ? 0 : this.getOrgHierarchyOne().hashCode() );
         result = 37 * result + ( getProgram() == null ? 0 : this.getProgram().hashCode() );
         result = 37 * result + ( getOrganization() == null ? 0 : this.getOrganization().hashCode() );
         result = 37 * result + ( getOrgTitle() == null ? 0 : this.getOrgTitle().hashCode() );
         result = 37 * result + ( getFund() == null ? 0 : this.getFund().hashCode() );
         result = 37 * result + ( getFundTitle() == null ? 0 : this.getFundTitle().hashCode() );
         result = 37 * result + this.getDivisionId();
         result = 37 * result + this.getDepartmentId();
         result = 37 * result + this.getSomMajorGroup();
         return result;
   }   


}


