package edu.ucsd.som.stip.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.Length;

@Entity
@Table(name="stipdetailsingle"
    ,catalog="stip"
)
public class Stipdetailsingle  implements java.io.Serializable {

	private static final long serialVersionUID = 8574229628483312301L;
	private Integer stipdetailId;
     private String indx;
     private Double runBalance;
     private Integer days;
     private Double average;
     private String pfFund;
     private String poOrganization;
     private String majorGroup;
     private String department;
     private Double code;
     private String qrt;
     private String fundDeposit;
     private Double totfndAvg;
     private Double stipAmt;
     private Double portionStip;
     private Double single;
     private String dept;
     private String division;
     private String inde;
     private String fund;
     private String org;
     private String acct;
     private String somcrtlindex;
     private Boolean status;

    public Stipdetailsingle() {
    }

    public Stipdetailsingle(String indx, Double runBalance, Integer days, Double average, String pfFund, String poOrganization, String majorGroup, String department, Double code, String qrt, String fundDeposit, Double totfndAvg, Double stipAmt, Double portionStip, Double single, String dept, String division, String inde, String fund, String org, String acct, String somcrtlindex) {
       this.indx = indx;
       this.runBalance = runBalance;
       this.days = days;
       this.average = average;
       this.pfFund = pfFund;
       this.poOrganization = poOrganization;
       this.majorGroup = majorGroup;
       this.department = department;
       this.code = code;
       this.qrt = qrt;
       this.fundDeposit = fundDeposit;
       this.totfndAvg = totfndAvg;
       this.stipAmt = stipAmt;
       this.portionStip = portionStip;
       this.single = single;
       this.dept = dept;
       this.division = division;
       this.inde = inde;
       this.fund = fund;
       this.org = org;
       this.acct = acct;
       this.somcrtlindex = somcrtlindex;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="stipdetail_id", unique=true, nullable=false)
    public Integer getStipdetailId() {
        return this.stipdetailId;
    }
    
    public void setStipdetailId(Integer stipdetailId) {
        this.stipdetailId = stipdetailId;
    }

    
    @Column(name="indx", length=8)
    @Length(max=8)
    public String getIndx() {
        return this.indx;
    }
    
    public void setIndx(String indx) {
        this.indx = indx;
    }

    
    @Column(name="run_balance", precision=22, scale=0)
    public Double getRunBalance() {
        return this.runBalance;
    }
    
    public void setRunBalance(Double runBalance) {
        this.runBalance = runBalance;
    }

    
    @Column(name="days")
    public Integer getDays() {
        return this.days;
    }
    
    public void setDays(Integer days) {
        this.days = days;
    }

    
    @Column(name="average", precision=22, scale=0)
    public Double getAverage() {
        return this.average;
    }
    
    public void setAverage(Double average) {
        this.average = average;
    }

    
    @Column(name="pf_fund", length=6)
    @Length(max=6)
    public String getPfFund() {
        return this.pfFund;
    }
    
    public void setPfFund(String pfFund) {
        this.pfFund = pfFund;
    }

    
    @Column(name="po_organization", length=6)
    @Length(max=6)
    public String getPoOrganization() {
        return this.poOrganization;
    }
    
    public void setPoOrganization(String poOrganization) {
        this.poOrganization = poOrganization;
    }

    
    @Column(name="major_group", length=50)
    @Length(max=50)
    public String getMajorGroup() {
        return this.majorGroup;
    }
    
    public void setMajorGroup(String majorGroup) {
        this.majorGroup = majorGroup;
    }

    
    @Column(name="department", length=50)
    @Length(max=50)
    public String getDepartment() {
        return this.department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }

    
    @Column(name="code", precision=22, scale=0)
    public Double getCode() {
        return this.code;
    }
    
    public void setCode(Double code) {
        this.code = code;
    }

    
    @Column(name="qrt", length=7)
    @Length(max=7)
    public String getQrt() {
        return this.qrt;
    }
    
    public void setQrt(String qrt) {
        this.qrt = qrt;
    }

    
    @Column(name="fund_deposit", length=6)
    @Length(max=6)
    public String getFundDeposit() {
        return this.fundDeposit;
    }
    
    public void setFundDeposit(String fundDeposit) {
        this.fundDeposit = fundDeposit;
    }

    
    @Column(name="totfnd_avg", precision=22, scale=0)
    public Double getTotfndAvg() {
        return this.totfndAvg;
    }
    
    public void setTotfndAvg(Double totfndAvg) {
        this.totfndAvg = totfndAvg;
    }

    
    @Column(name="stip_amt", precision=22, scale=0)
    public Double getStipAmt() {
        return this.stipAmt;
    }
    
    public void setStipAmt(Double stipAmt) {
        this.stipAmt = stipAmt;
    }

    
    @Column(name="portion_stip", precision=22, scale=0)
    public Double getPortionStip() {
        return this.portionStip;
    }
    
    public void setPortionStip(Double portionStip) {
        this.portionStip = portionStip;
    }

    
    @Column(name="single", precision=22, scale=0)
    public Double getSingle() {
        return this.single;
    }
    
    public void setSingle(Double single) {
        this.single = single;
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

    
    @Column(name="inde_", length=7)
    @Length(max=7)
    public String getInde() {
        return this.inde;
    }
    
    public void setInde(String inde) {
        this.inde = inde;
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

    
    @Column(name="somcrtlindex", length=7)
    @Length(max=7)
    public String getSomcrtlindex() {
        return this.somcrtlindex;
    }
    
    public void setSomcrtlindex(String somcrtlindex) {
        this.somcrtlindex = somcrtlindex;
    }


    @Column(name="status")
    public Boolean getStatus() {
        return this.status;
    }
    
    public void setStatus(Boolean status) {
        this.status = status;
    }

}


