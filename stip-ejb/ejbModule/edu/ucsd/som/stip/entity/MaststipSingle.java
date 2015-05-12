package edu.ucsd.som.stip.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.Length;

@Entity
@Table(name="maststip_single"
    ,catalog="stip"
)
public class MaststipSingle  implements java.io.Serializable {

	private static final long serialVersionUID = -5328486880124823464L;
	
	 private Integer maststipNewId;
     private Short sequenceNumber;
     private String journalType;
     private String transactionDescription;
     private String documentReferenceNo;
     private Double transactionAmount;
     private String debitCredit;
     private String coaCode;
     private String indexCode;
     private String fundCode;
     private String organizationCode;
     private String accountCode;
     private String programCode;
     private String activityCode;
     private String locationCode;
     private String encumbranceNumber;
     private String encumbranceDocType;
     private String encumbranceItemNumber;
     private String encumbranceItemSeqNo;

    public MaststipSingle() {
    }

    public MaststipSingle(Short sequenceNumber, String journalType, String transactionDescription, String documentReferenceNo, Double transactionAmount, String debitCredit, String coaCode, String indexCode, String fundCode, String organizationCode, String accountCode, String programCode, String activityCode, String locationCode, String encumbranceNumber, String encumbranceDocType, String encumbranceItemNumber, String encumbranceItemSeqNo) {
       this.sequenceNumber = sequenceNumber;
       this.journalType = journalType;
       this.transactionDescription = transactionDescription;
       this.documentReferenceNo = documentReferenceNo;
       this.transactionAmount = transactionAmount;
       this.debitCredit = debitCredit;
       this.coaCode = coaCode;
       this.indexCode = indexCode;
       this.fundCode = fundCode;
       this.organizationCode = organizationCode;
       this.accountCode = accountCode;
       this.programCode = programCode;
       this.activityCode = activityCode;
       this.locationCode = locationCode;
       this.encumbranceNumber = encumbranceNumber;
       this.encumbranceDocType = encumbranceDocType;
       this.encumbranceItemNumber = encumbranceItemNumber;
       this.encumbranceItemSeqNo = encumbranceItemSeqNo;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="maststip_new_id", unique=true, nullable=false)
    public Integer getMaststipNewId() {
        return this.maststipNewId;
    }
    
    public void setMaststipNewId(Integer maststipNewId) {
        this.maststipNewId = maststipNewId;
    }

    
    @Column(name="sequence_number")
    public Short getSequenceNumber() {
        return this.sequenceNumber;
    }
    
    public void setSequenceNumber(Short sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    
    @Column(name="journal_type", length=4)
    @Length(max=4)
    public String getJournalType() {
        return this.journalType;
    }
    
    public void setJournalType(String journalType) {
        this.journalType = journalType;
    }

    
    @Column(name="transaction_description", length=50)
    @Length(max=50)
    public String getTransactionDescription() {
        return this.transactionDescription;
    }
    
    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    
    @Column(name="document_reference_no", length=11)
    @Length(max=11)
    public String getDocumentReferenceNo() {
        return this.documentReferenceNo;
    }
    
    public void setDocumentReferenceNo(String documentReferenceNo) {
        this.documentReferenceNo = documentReferenceNo;
    }

    
    @Column(name="transaction_amount", precision=22, scale=0)
    public Double getTransactionAmount() {
        return this.transactionAmount;
    }
    
    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    
    @Column(name="debit_credit", length=1)
    @Length(max=1)
    public String getDebitCredit() {
        return this.debitCredit;
    }
    
    public void setDebitCredit(String debitCredit) {
        this.debitCredit = debitCredit;
    }

    
    @Column(name="coa_code", length=1)
    @Length(max=1)
    public String getCoaCode() {
        return this.coaCode;
    }
    
    public void setCoaCode(String coaCode) {
        this.coaCode = coaCode;
    }

    
    @Column(name="index_code", length=7)
    @Length(max=7)
    public String getIndexCode() {
        return this.indexCode;
    }
    
    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode;
    }

    
    @Column(name="fund_code", length=6)
    @Length(max=6)
    public String getFundCode() {
        return this.fundCode;
    }
    
    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    
    @Column(name="organization_code", length=6)
    @Length(max=6)
    public String getOrganizationCode() {
        return this.organizationCode;
    }
    
    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    
    @Column(name="account_code", length=6)
    @Length(max=6)
    public String getAccountCode() {
        return this.accountCode;
    }
    
    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    
    @Column(name="program_code", length=1)
    @Length(max=1)
    public String getProgramCode() {
        return this.programCode;
    }
    
    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    
    @Column(name="activity_code", length=1)
    @Length(max=1)
    public String getActivityCode() {
        return this.activityCode;
    }
    
    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    
    @Column(name="location_code", length=1)
    @Length(max=1)
    public String getLocationCode() {
        return this.locationCode;
    }
    
    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    
    @Column(name="encumbrance_number", length=1)
    @Length(max=1)
    public String getEncumbranceNumber() {
        return this.encumbranceNumber;
    }
    
    public void setEncumbranceNumber(String encumbranceNumber) {
        this.encumbranceNumber = encumbranceNumber;
    }

    
    @Column(name="encumbrance_doc_type", length=1)
    @Length(max=1)
    public String getEncumbranceDocType() {
        return this.encumbranceDocType;
    }
    
    public void setEncumbranceDocType(String encumbranceDocType) {
        this.encumbranceDocType = encumbranceDocType;
    }

    
    @Column(name="encumbrance_item_number", length=1)
    @Length(max=1)
    public String getEncumbranceItemNumber() {
        return this.encumbranceItemNumber;
    }
    
    public void setEncumbranceItemNumber(String encumbranceItemNumber) {
        this.encumbranceItemNumber = encumbranceItemNumber;
    }

    
    @Column(name="encumbrance_item_seq_no", length=1)
    @Length(max=1)
    public String getEncumbranceItemSeqNo() {
        return this.encumbranceItemSeqNo;
    }
    
    public void setEncumbranceItemSeqNo(String encumbranceItemSeqNo) {
        this.encumbranceItemSeqNo = encumbranceItemSeqNo;
    }




}


