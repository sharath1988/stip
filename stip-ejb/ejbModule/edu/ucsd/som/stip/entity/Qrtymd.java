package edu.ucsd.som.stip.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.Length;

@Entity
@Table(name="qrtymd"
    ,catalog="stip"
)
public class Qrtymd  implements java.io.Serializable {

	private static final long serialVersionUID = -2933330800622179012L;
	
	 private Integer qrtymdId;
     private String indx;
     private BigDecimal runBalance;
     private Integer days;
     private BigDecimal average;
     private String pfFund;
     private String poOrganization;
     private String majorGroup;
     private String department;
     private BigDecimal code;
     private String qrt;
     private String funddeposit;
     private BigDecimal totFndAvg;
     private BigDecimal stipAmt;
     private BigDecimal portionstip;
     private BigDecimal single;
     private BigDecimal intRate;
     private BigDecimal stipbaseduponIntrate;
     private BigDecimal totaldistintrate;
     private BigDecimal addtlStiptobeDist;
     private BigDecimal addtlBalcalc;
     private BigDecimal totAddtlBalcalc;
     private BigDecimal addtlStip;
     private BigDecimal totalStip;
     private BigDecimal totalStipfnd;
     private BigDecimal remainingStipadj;
     private BigDecimal stipadj;
     private BigDecimal finalStip;
     private BigDecimal sumFinalStipFund;

    public Qrtymd() {
    }

    public Qrtymd(String indx, BigDecimal runBalance, Integer days, BigDecimal average, String pfFund, String poOrganization, String majorGroup, String department, BigDecimal code, String qrt, String funddeposit, BigDecimal totFndAvg, BigDecimal stipAmt, BigDecimal portionstip, BigDecimal single, BigDecimal intRate, BigDecimal stipbaseduponIntrate, BigDecimal totaldistintrate, BigDecimal addtlStiptobeDist, BigDecimal addtlBalcalc, BigDecimal totAddtlBalcalc, BigDecimal addtlStip, BigDecimal totalStip, BigDecimal totalStipfnd, BigDecimal remainingStipadj, BigDecimal stipadj, BigDecimal finalStip, BigDecimal sumFinalStipFund) {
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
       this.funddeposit = funddeposit;
       this.totFndAvg = totFndAvg;
       this.stipAmt = stipAmt;
       this.portionstip = portionstip;
       this.single = single;
       this.intRate = intRate;
       this.stipbaseduponIntrate = stipbaseduponIntrate;
       this.totaldistintrate = totaldistintrate;
       this.addtlStiptobeDist = addtlStiptobeDist;
       this.addtlBalcalc = addtlBalcalc;
       this.totAddtlBalcalc = totAddtlBalcalc;
       this.addtlStip = addtlStip;
       this.totalStip = totalStip;
       this.totalStipfnd = totalStipfnd;
       this.remainingStipadj = remainingStipadj;
       this.stipadj = stipadj;
       this.finalStip = finalStip;
       this.sumFinalStipFund = sumFinalStipFund;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="qrtymd_id", unique=true, nullable=false)
    public Integer getQrtymdId() {
        return this.qrtymdId;
    }
    
    public void setQrtymdId(Integer qrtymdId) {
        this.qrtymdId = qrtymdId;
    }

    
    @Column(name="indx", length=8)
    @Length(max=8)
    public String getIndx() {
        return this.indx;
    }
    
    public void setIndx(String indx) {
        this.indx = indx;
    }

    
    @Column(name="run_balance", precision=22, scale=0,columnDefinition="double")
    public BigDecimal getRunBalance() {
        return this.runBalance;
    }
    
    public void setRunBalance(BigDecimal runBalance) {
        this.runBalance = runBalance;
    }

    
    @Column(name="days")
    public Integer getDays() {
        return this.days;
    }
    
    public void setDays(Integer days) {
        this.days = days;
    }

    
    @Column(name="average", precision=22, scale=0,columnDefinition="double")
    public BigDecimal getAverage() {
        return this.average;
    }
    
    public void setAverage(BigDecimal average) {
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

    
    @Column(name="code", precision=22, scale=0,columnDefinition="double")
    public BigDecimal getCode() {
        return this.code;
    }
    
    public void setCode(BigDecimal code) {
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

    
    @Column(name="funddeposit", length=6)
    @Length(max=6)
    public String getFunddeposit() {
        return this.funddeposit;
    }
    
    public void setFunddeposit(String funddeposit) {
        this.funddeposit = funddeposit;
    }

    
    @Column(name="tot_fnd_avg", precision=22, scale=0,columnDefinition="double")
    public BigDecimal getTotFndAvg() {
        return this.totFndAvg;
    }
    
    public void setTotFndAvg(BigDecimal totFndAvg) {
        this.totFndAvg = totFndAvg;
    }

    
    @Column(name="stip_amt", precision=22, scale=0,columnDefinition="double")
    public BigDecimal getStipAmt() {
        return this.stipAmt;
    }
    
    public void setStipAmt(BigDecimal stipAmt) {
        this.stipAmt = stipAmt;
    }

    
    @Column(name="portionstip", precision=22, scale=0,columnDefinition="double")
    public BigDecimal getPortionstip() {
        return this.portionstip;
    }
    
    public void setPortionstip(BigDecimal portionstip) {
        this.portionstip = portionstip;
    }

    
    @Column(name="single", precision=22, scale=0,columnDefinition="double")
    public BigDecimal getSingle() {
        return this.single;
    }
    
    public void setSingle(BigDecimal single) {
        this.single = single;
    }

    
    @Column(name="int_rate", precision=22, scale=0,columnDefinition="double")
    public BigDecimal getIntRate() {
        return this.intRate;
    }
    
    public void setIntRate(BigDecimal intRate) {
        this.intRate = intRate;
    }

    
    @Column(name="stipbasedupon_intrate", precision=22, scale=0,columnDefinition="double")
    public BigDecimal getStipbaseduponIntrate() {
        return this.stipbaseduponIntrate;
    }
    
    public void setStipbaseduponIntrate(BigDecimal stipbaseduponIntrate) {
        this.stipbaseduponIntrate = stipbaseduponIntrate;
    }

    
    @Column(name="totaldistintrate", precision=22, scale=0,columnDefinition="double")
    public BigDecimal getTotaldistintrate() {
        return this.totaldistintrate;
    }
    
    public void setTotaldistintrate(BigDecimal totaldistintrate) {
        this.totaldistintrate = totaldistintrate;
    }

    
    @Column(name="addtl_stiptobe_dist", precision=22, scale=0,columnDefinition="double")
    public BigDecimal getAddtlStiptobeDist() {
        return this.addtlStiptobeDist;
    }
    
    public void setAddtlStiptobeDist(BigDecimal addtlStiptobeDist) {
        this.addtlStiptobeDist = addtlStiptobeDist;
    }

    
    @Column(name="addtl_balcalc", precision=22, scale=0,columnDefinition="double")
    public BigDecimal getAddtlBalcalc() {
        return this.addtlBalcalc;
    }
    
    public void setAddtlBalcalc(BigDecimal addtlBalcalc) {
        this.addtlBalcalc = addtlBalcalc;
    }

    
    @Column(name="tot_addtl_balcalc", precision=22, scale=0,columnDefinition="double")
    public BigDecimal getTotAddtlBalcalc() {
        return this.totAddtlBalcalc;
    }
    
    public void setTotAddtlBalcalc(BigDecimal totAddtlBalcalc) {
        this.totAddtlBalcalc = totAddtlBalcalc;
    }

    
    @Column(name="addtl_stip", precision=22, scale=0,columnDefinition="double")
    public BigDecimal getAddtlStip() {
        return this.addtlStip;
    }
    
    public void setAddtlStip(BigDecimal addtlStip) {
        this.addtlStip = addtlStip;
    }

    
    @Column(name="total_stip", precision=22, scale=0,columnDefinition="double")
    public BigDecimal getTotalStip() {
        return this.totalStip;
    }
    
    public void setTotalStip(BigDecimal totalStip) {
        this.totalStip = totalStip;
    }

    
    @Column(name="total_stipfnd", precision=22, scale=0,columnDefinition="double")
    public BigDecimal getTotalStipfnd() {
        return this.totalStipfnd;
    }
    
    public void setTotalStipfnd(BigDecimal totalStipfnd) {
        this.totalStipfnd = totalStipfnd;
    }

    
    @Column(name="remaining_stipadj", precision=22, scale=0,columnDefinition="double")
    public BigDecimal getRemainingStipadj() {
        return this.remainingStipadj;
    }
    
    public void setRemainingStipadj(BigDecimal remainingStipadj) {
        this.remainingStipadj = remainingStipadj;
    }

    
    @Column(name="stipadj", precision=22, scale=0,columnDefinition="double")
    public BigDecimal getStipadj() {
        return this.stipadj;
    }
    
    public void setStipadj(BigDecimal stipadj) {
        this.stipadj = stipadj;
    }

    
    @Column(name="final_stip", precision=22, scale=0,columnDefinition="double")
    public BigDecimal getFinalStip() {
        return this.finalStip;
    }
    
    public void setFinalStip(BigDecimal finalStip) {
        this.finalStip = finalStip;
    }

    
    @Column(name="sum_final_stip_fund", precision=22, scale=0,columnDefinition="double")
    public BigDecimal getSumFinalStipFund() {
        return this.sumFinalStipFund;
    }
    
    public void setSumFinalStipFund(BigDecimal sumFinalStipFund) {
        this.sumFinalStipFund = sumFinalStipFund;
    }




}


