package edu.ucsd.som.stip.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

@Embeddable
public class DtlTransId  implements java.io.Serializable {

	 private static final long serialVersionUID = -43159199531577939L;
	 private Integer an;
     private int accountingPeriod;
     private String indexNum;
     private String fund;
     private String org;
     private String account;
     private char sub;
     private String program;
     private String docNum;
     private String docRefNum;
     private BigDecimal budget;
     private BigDecimal financial;
     private BigDecimal encumbrance;
     private String fieldIndicator;
     private String description;
     private String ruleClass;
     private char ledgerIndicator;
     private Date transDate;
     private Date createTs;
     private Date updateTs;

    public DtlTransId() {
    }

	
    public DtlTransId(int accountingPeriod, String indexNum, String fund, String org, String account, char sub, String program, String docNum, BigDecimal budget, BigDecimal financial, BigDecimal encumbrance, String fieldIndicator, String ruleClass, char ledgerIndicator, Date transDate) {
        this.accountingPeriod = accountingPeriod;
        this.indexNum = indexNum;
        this.fund = fund;
        this.org = org;
        this.account = account;
        this.sub = sub;
        this.program = program;
        this.docNum = docNum;
        this.budget = budget;
        this.financial = financial;
        this.encumbrance = encumbrance;
        this.fieldIndicator = fieldIndicator;
        this.ruleClass = ruleClass;
        this.ledgerIndicator = ledgerIndicator;
        this.transDate = transDate;
    }
    public DtlTransId(Integer an, int accountingPeriod, String indexNum, String fund, String org, String account, char sub, String program, String docNum, String docRefNum, BigDecimal budget, BigDecimal financial, BigDecimal encumbrance, String fieldIndicator, String description, String ruleClass, char ledgerIndicator, Date transDate, Date createTs, Date updateTs) {
       this.an = an;
       this.accountingPeriod = accountingPeriod;
       this.indexNum = indexNum;
       this.fund = fund;
       this.org = org;
       this.account = account;
       this.sub = sub;
       this.program = program;
       this.docNum = docNum;
       this.docRefNum = docRefNum;
       this.budget = budget;
       this.financial = financial;
       this.encumbrance = encumbrance;
       this.fieldIndicator = fieldIndicator;
       this.description = description;
       this.ruleClass = ruleClass;
       this.ledgerIndicator = ledgerIndicator;
       this.transDate = transDate;
       this.createTs = createTs;
       this.updateTs = updateTs;
    }
   


    @Column(name="an")
    public Integer getAn() {
        return this.an;
    }
    
    public void setAn(Integer an) {
        this.an = an;
    }


    @Column(name="accountingPeriod", nullable=false)
    public int getAccountingPeriod() {
        return this.accountingPeriod;
    }
    
    public void setAccountingPeriod(int accountingPeriod) {
        this.accountingPeriod = accountingPeriod;
    }


    @Column(name="indexNum", nullable=false, length=10)
    @NotNull
    @Length(max=10)
    public String getIndexNum() {
        return this.indexNum;
    }
    
    public void setIndexNum(String indexNum) {
        this.indexNum = indexNum;
    }


    @Column(name="fund", nullable=false, length=6)
    @NotNull
    @Length(max=6)
    public String getFund() {
        return this.fund;
    }
    
    public void setFund(String fund) {
        this.fund = fund;
    }


    @Column(name="org", nullable=false, length=6)
    @NotNull
    @Length(max=6)
    public String getOrg() {
        return this.org;
    }
    
    public void setOrg(String org) {
        this.org = org;
    }


    @Column(name="account", nullable=false, length=6)
    @NotNull
    @Length(max=6)
    public String getAccount() {
        return this.account;
    }
    
    public void setAccount(String account) {
        this.account = account;
    }


    @Column(name="sub", nullable=false, length=1)
    public char getSub() {
        return this.sub;
    }
    
    public void setSub(char sub) {
        this.sub = sub;
    }


    @Column(name="program", nullable=false, length=6)
    @NotNull
    @Length(max=6)
    public String getProgram() {
        return this.program;
    }
    
    public void setProgram(String program) {
        this.program = program;
    }


    @Column(name="docNum", nullable=false, length=8)
    @NotNull
    @Length(max=8)
    public String getDocNum() {
        return this.docNum;
    }
    
    public void setDocNum(String docNum) {
        this.docNum = docNum;
    }


    @Column(name="docRefNum", length=10)
    @Length(max=10)
    public String getDocRefNum() {
        return this.docRefNum;
    }
    
    public void setDocRefNum(String docRefNum) {
        this.docRefNum = docRefNum;
    }


    @Column(name="budget", nullable=false, scale=4)
    @NotNull
    public BigDecimal getBudget() {
        return this.budget;
    }
    
    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }


    @Column(name="financial", nullable=false, scale=4)
    @NotNull
    public BigDecimal getFinancial() {
        return this.financial;
    }
    
    public void setFinancial(BigDecimal financial) {
        this.financial = financial;
    }


    @Column(name="encumbrance", nullable=false, scale=4)
    @NotNull
    public BigDecimal getEncumbrance() {
        return this.encumbrance;
    }
    
    public void setEncumbrance(BigDecimal encumbrance) {
        this.encumbrance = encumbrance;
    }


    @Column(name="fieldIndicator", nullable=false, length=32)
    @NotNull
    @Length(max=32)
    public String getFieldIndicator() {
        return this.fieldIndicator;
    }
    
    public void setFieldIndicator(String fieldIndicator) {
        this.fieldIndicator = fieldIndicator;
    }


    @Column(name="description", length=35)
    @Length(max=35)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }


    @Column(name="ruleClass", nullable=false, length=4)
    @NotNull
    @Length(max=4)
    public String getRuleClass() {
        return this.ruleClass;
    }
    
    public void setRuleClass(String ruleClass) {
        this.ruleClass = ruleClass;
    }


    @Column(name="ledgerIndicator", nullable=false, length=1)
    public char getLedgerIndicator() {
        return this.ledgerIndicator;
    }
    
    public void setLedgerIndicator(char ledgerIndicator) {
        this.ledgerIndicator = ledgerIndicator;
    }


    @Column(name="transDate", nullable=false, length=0)
    @NotNull
    public Date getTransDate() {
        return this.transDate;
    }
    
    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }


    @Column(name="create_ts", length=0)
    public Date getCreateTs() {
        return this.createTs;
    }
    
    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
    }


    @Column(name="update_ts", length=0)
    public Date getUpdateTs() {
        return this.updateTs;
    }
    
    public void setUpdateTs(Date updateTs) {
        this.updateTs = updateTs;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DtlTransId) ) return false;
		 DtlTransId castOther = ( DtlTransId ) other; 
         
		 return ( (this.getAn()==castOther.getAn()) || ( this.getAn()!=null && castOther.getAn()!=null && this.getAn().equals(castOther.getAn()) ) )
 && (this.getAccountingPeriod()==castOther.getAccountingPeriod())
 && ( (this.getIndexNum()==castOther.getIndexNum()) || ( this.getIndexNum()!=null && castOther.getIndexNum()!=null && this.getIndexNum().equals(castOther.getIndexNum()) ) )
 && ( (this.getFund()==castOther.getFund()) || ( this.getFund()!=null && castOther.getFund()!=null && this.getFund().equals(castOther.getFund()) ) )
 && ( (this.getOrg()==castOther.getOrg()) || ( this.getOrg()!=null && castOther.getOrg()!=null && this.getOrg().equals(castOther.getOrg()) ) )
 && ( (this.getAccount()==castOther.getAccount()) || ( this.getAccount()!=null && castOther.getAccount()!=null && this.getAccount().equals(castOther.getAccount()) ) )
 && (this.getSub()==castOther.getSub())
 && ( (this.getProgram()==castOther.getProgram()) || ( this.getProgram()!=null && castOther.getProgram()!=null && this.getProgram().equals(castOther.getProgram()) ) )
 && ( (this.getDocNum()==castOther.getDocNum()) || ( this.getDocNum()!=null && castOther.getDocNum()!=null && this.getDocNum().equals(castOther.getDocNum()) ) )
 && ( (this.getDocRefNum()==castOther.getDocRefNum()) || ( this.getDocRefNum()!=null && castOther.getDocRefNum()!=null && this.getDocRefNum().equals(castOther.getDocRefNum()) ) )
 && ( (this.getBudget()==castOther.getBudget()) || ( this.getBudget()!=null && castOther.getBudget()!=null && this.getBudget().equals(castOther.getBudget()) ) )
 && ( (this.getFinancial()==castOther.getFinancial()) || ( this.getFinancial()!=null && castOther.getFinancial()!=null && this.getFinancial().equals(castOther.getFinancial()) ) )
 && ( (this.getEncumbrance()==castOther.getEncumbrance()) || ( this.getEncumbrance()!=null && castOther.getEncumbrance()!=null && this.getEncumbrance().equals(castOther.getEncumbrance()) ) )
 && ( (this.getFieldIndicator()==castOther.getFieldIndicator()) || ( this.getFieldIndicator()!=null && castOther.getFieldIndicator()!=null && this.getFieldIndicator().equals(castOther.getFieldIndicator()) ) )
 && ( (this.getDescription()==castOther.getDescription()) || ( this.getDescription()!=null && castOther.getDescription()!=null && this.getDescription().equals(castOther.getDescription()) ) )
 && ( (this.getRuleClass()==castOther.getRuleClass()) || ( this.getRuleClass()!=null && castOther.getRuleClass()!=null && this.getRuleClass().equals(castOther.getRuleClass()) ) )
 && (this.getLedgerIndicator()==castOther.getLedgerIndicator())
 && ( (this.getTransDate()==castOther.getTransDate()) || ( this.getTransDate()!=null && castOther.getTransDate()!=null && this.getTransDate().equals(castOther.getTransDate()) ) )
 && ( (this.getCreateTs()==castOther.getCreateTs()) || ( this.getCreateTs()!=null && castOther.getCreateTs()!=null && this.getCreateTs().equals(castOther.getCreateTs()) ) )
 && ( (this.getUpdateTs()==castOther.getUpdateTs()) || ( this.getUpdateTs()!=null && castOther.getUpdateTs()!=null && this.getUpdateTs().equals(castOther.getUpdateTs()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getAn() == null ? 0 : this.getAn().hashCode() );
         result = 37 * result + this.getAccountingPeriod();
         result = 37 * result + ( getIndexNum() == null ? 0 : this.getIndexNum().hashCode() );
         result = 37 * result + ( getFund() == null ? 0 : this.getFund().hashCode() );
         result = 37 * result + ( getOrg() == null ? 0 : this.getOrg().hashCode() );
         result = 37 * result + ( getAccount() == null ? 0 : this.getAccount().hashCode() );
         result = 37 * result + this.getSub();
         result = 37 * result + ( getProgram() == null ? 0 : this.getProgram().hashCode() );
         result = 37 * result + ( getDocNum() == null ? 0 : this.getDocNum().hashCode() );
         result = 37 * result + ( getDocRefNum() == null ? 0 : this.getDocRefNum().hashCode() );
         result = 37 * result + ( getBudget() == null ? 0 : this.getBudget().hashCode() );
         result = 37 * result + ( getFinancial() == null ? 0 : this.getFinancial().hashCode() );
         result = 37 * result + ( getEncumbrance() == null ? 0 : this.getEncumbrance().hashCode() );
         result = 37 * result + ( getFieldIndicator() == null ? 0 : this.getFieldIndicator().hashCode() );
         result = 37 * result + ( getDescription() == null ? 0 : this.getDescription().hashCode() );
         result = 37 * result + ( getRuleClass() == null ? 0 : this.getRuleClass().hashCode() );
         result = 37 * result + this.getLedgerIndicator();
         result = 37 * result + ( getTransDate() == null ? 0 : this.getTransDate().hashCode() );
         result = 37 * result + ( getCreateTs() == null ? 0 : this.getCreateTs().hashCode() );
         result = 37 * result + ( getUpdateTs() == null ? 0 : this.getUpdateTs().hashCode() );
         return result;
   }   


}


