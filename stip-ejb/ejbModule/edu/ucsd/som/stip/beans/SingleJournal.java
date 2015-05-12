package edu.ucsd.som.stip.beans;



public class SingleJournal  implements java.io.Serializable {

	private static final long serialVersionUID = 6937878446915746851L;

	 private Integer singleJournalId;
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
     private Boolean status;

    public SingleJournal() {
    }

    public SingleJournal(Short sequenceNumber, String journalType, String transactionDescription, String documentReferenceNo, Double transactionAmount, String debitCredit, String coaCode, String indexCode, String fundCode, String organizationCode, String accountCode, String programCode, String activityCode, String locationCode, String encumbranceNumber, String encumbranceDocType, String encumbranceItemNumber, String encumbranceItemSeqNo, Boolean status) {
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
       this.status = status;
    }
   

    
    public SingleJournal(Object[] o) {
    	this.singleJournalId=(Integer)o[0];
    	this.sequenceNumber = (Short)o[1];
        this.journalType = (String) o[2];
        this.transactionDescription = (String) o[3];
        this.documentReferenceNo = (String) o[4];
        this.transactionAmount = (Double) o[5];
        this.debitCredit = (String) o[6];
        this.coaCode = (String) o[7];
        this.indexCode = (String) o[8];
        this.fundCode = (String) o[9];
        this.organizationCode = (String) o[10];
        this.accountCode = (String) o[11];
    }

	public Integer getSingleJournalId() {
        return this.singleJournalId;
    }
    
    public void setSingleJournalId(Integer singleJournalId) {
        this.singleJournalId = singleJournalId;
    }

    
    public Short getSequenceNumber() {
        return this.sequenceNumber;
    }
    
    public void setSequenceNumber(Short sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    
    public String getJournalType() {
        return this.journalType;
    }
    
    public void setJournalType(String journalType) {
        this.journalType = journalType;
    }

    
    public String getTransactionDescription() {
        return this.transactionDescription;
    }
    
    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    
    public String getDocumentReferenceNo() {
        return this.documentReferenceNo;
    }
    
    public void setDocumentReferenceNo(String documentReferenceNo) {
        this.documentReferenceNo = documentReferenceNo;
    }

    
    public Double getTransactionAmount() {
        return this.transactionAmount;
    }
    
    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    
    public String getDebitCredit() {
        return this.debitCredit;
    }
    
    public void setDebitCredit(String debitCredit) {
        this.debitCredit = debitCredit;
    }

    
    public String getCoaCode() {
        return this.coaCode;
    }
    
    public void setCoaCode(String coaCode) {
        this.coaCode = coaCode;
    }

    
    public String getIndexCode() {
        return this.indexCode;
    }
    
    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode;
    }

    
    public String getFundCode() {
        return this.fundCode;
    }
    
    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    
    public String getOrganizationCode() {
        return this.organizationCode;
    }
    
    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    
    public String getAccountCode() {
        return this.accountCode;
    }
    
    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    
    public String getProgramCode() {
        return this.programCode;
    }
    
    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    
    public String getActivityCode() {
        return this.activityCode;
    }
    
    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    
    public String getLocationCode() {
        return this.locationCode;
    }
    
    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    
    public String getEncumbranceNumber() {
        return this.encumbranceNumber;
    }
    
    public void setEncumbranceNumber(String encumbranceNumber) {
        this.encumbranceNumber = encumbranceNumber;
    }

    
    public String getEncumbranceDocType() {
        return this.encumbranceDocType;
    }
    
    public void setEncumbranceDocType(String encumbranceDocType) {
        this.encumbranceDocType = encumbranceDocType;
    }

    
    public String getEncumbranceItemNumber() {
        return this.encumbranceItemNumber;
    }
    
    public void setEncumbranceItemNumber(String encumbranceItemNumber) {
        this.encumbranceItemNumber = encumbranceItemNumber;
    }

    
    public String getEncumbranceItemSeqNo() {
        return this.encumbranceItemSeqNo;
    }
    
    public void setEncumbranceItemSeqNo(String encumbranceItemSeqNo) {
        this.encumbranceItemSeqNo = encumbranceItemSeqNo;
    }

    
    public Boolean getStatus() {
        return this.status;
    }
    
    public void setStatus(Boolean status) {
        this.status = status;
    }




}


