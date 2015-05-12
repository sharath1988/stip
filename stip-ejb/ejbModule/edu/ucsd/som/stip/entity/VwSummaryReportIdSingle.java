package edu.ucsd.som.stip.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.hibernate.validator.Length;

@Embeddable
public class VwSummaryReportIdSingle  implements java.io.Serializable {

	private static final long serialVersionUID = -4041594853125164275L;
	private String dept;
     private String division;
     private String fyQrt;
    // private String indx;
     private String fund;
     private String depToIndex;
     private String depToFund;
     private Double amount;

    public VwSummaryReportIdSingle() {
    }

    public VwSummaryReportIdSingle(String dept, String division, String fyQrt, String fund, String depToIndex, String depToFund, Double amount) {
       this.dept = dept;
       this.division = division;
       this.fyQrt = fyQrt;
      // this.indx = indx;
       this.fund = fund;
       this.depToIndex = depToIndex;
       this.depToFund = depToFund;
       this.amount = amount;
    }
   


    @Column(name="Dept", length=50)
    @Length(max=50)
    public String getDept() {
        return this.dept;
    }
    
    public void setDept(String dept) {
        this.dept = dept;
    }


    @Column(name="Division", length=50)
    @Length(max=50)
    public String getDivision() {
        return this.division;
    }
    
    public void setDivision(String division) {
        this.division = division;
    }


    @Column(name="FyQrt", length=7)
    @Length(max=7)
    public String getFyQrt() {
        return this.fyQrt;
    }
    
    public void setFyQrt(String fyQrt) {
        this.fyQrt = fyQrt;
    }


   /* @Column(name="indx", length=8)
    @Length(max=8)
    public String getIndx() {
        return this.indx;
    }
    
    public void setIndx(String indx) {
        this.indx = indx;
    }*/


    @Column(name="Fund", length=6)
    @Length(max=6)
    public String getFund() {
        return this.fund;
    }
    
    public void setFund(String fund) {
        this.fund = fund;
    }


    @Column(name="Dep_to_idx", length=7)
    @Length(max=7)
    public String getDepToIndex() {
        return this.depToIndex;
    }
    
    public void setDepToIndex(String depToIndex) {
        this.depToIndex = depToIndex;
    }


    @Column(name="Dep_to_Fund", length=6)
    @Length(max=6)
    public String getDepToFund() {
        return this.depToFund;
    }
    
    public void setDepToFund(String depToFund) {
        this.depToFund = depToFund;
    }


    @Column(name="Amount", precision=22, scale=0)
    public Double getAmount() {
        return this.amount;
    }
    
    public void setAmount(Double amount) {
        this.amount = amount;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof VwSummaryReportIdSingle) ) return false;
		 VwSummaryReportIdSingle castOther = ( VwSummaryReportIdSingle ) other; 
         
		 return ( (this.getDept()==castOther.getDept()) || ( this.getDept()!=null && castOther.getDept()!=null && this.getDept().equals(castOther.getDept()) ) )
 && ( (this.getDivision()==castOther.getDivision()) || ( this.getDivision()!=null && castOther.getDivision()!=null && this.getDivision().equals(castOther.getDivision()) ) )
 && ( (this.getFyQrt()==castOther.getFyQrt()) || ( this.getFyQrt()!=null && castOther.getFyQrt()!=null && this.getFyQrt().equals(castOther.getFyQrt()) ) )
 //&& ( (this.getIndx()==castOther.getIndx()) || ( this.getIndx()!=null && castOther.getIndx()!=null && this.getIndx().equals(castOther.getIndx()) ) )
 && ( (this.getFund()==castOther.getFund()) || ( this.getFund()!=null && castOther.getFund()!=null && this.getFund().equals(castOther.getFund()) ) )
 && ( (this.getDepToIndex()==castOther.getDepToIndex()) || ( this.getDepToIndex()!=null && castOther.getDepToIndex()!=null && this.getDepToIndex().equals(castOther.getDepToIndex()) ) )
 && ( (this.getDepToFund()==castOther.getDepToFund()) || ( this.getDepToFund()!=null && castOther.getDepToFund()!=null && this.getDepToFund().equals(castOther.getDepToFund()) ) )
 && ( (this.getAmount()==castOther.getAmount()) || ( this.getAmount()!=null && castOther.getAmount()!=null && this.getAmount().equals(castOther.getAmount()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getDept() == null ? 0 : this.getDept().hashCode() );
         result = 37 * result + ( getDivision() == null ? 0 : this.getDivision().hashCode() );
         result = 37 * result + ( getFyQrt() == null ? 0 : this.getFyQrt().hashCode() );
 //        result = 37 * result + ( getIndx() == null ? 0 : this.getIndx().hashCode() );
         result = 37 * result + ( getFund() == null ? 0 : this.getFund().hashCode() );
         result = 37 * result + ( getDepToIndex() == null ? 0 : this.getDepToIndex().hashCode() );
         result = 37 * result + ( getDepToFund() == null ? 0 : this.getDepToFund().hashCode() );
         result = 37 * result + ( getAmount() == null ? 0 : this.getAmount().hashCode() );
         return result;
   }   


}


