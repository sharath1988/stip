package edu.ucsd.som.stip.beans;

import java.io.Serializable;

import edu.ucsd.som.stip.entity.MaststipNew;
import edu.ucsd.som.stip.entity.MaststipSingle;

public class InactiveIndices implements Serializable{

	
	private static final long serialVersionUID = -697123690390575653L;
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
    private boolean updated;
    private boolean single;

   public InactiveIndices() {
   }

   public InactiveIndices(MaststipNew m)
   {
      this.sequenceNumber = m.getSequenceNumber();
      this.journalType =m.getJournalType();
      this.transactionDescription = m.getTransactionDescription();
      this.documentReferenceNo = m.getDocumentReferenceNo();
      this.transactionAmount = m.getTransactionAmount();
      this.debitCredit = m.getDebitCredit();
      this.coaCode = m.getCoaCode();
      this.indexCode = m.getIndexCode();
      this.fundCode = m.getFundCode();
      this.organizationCode = m.getOrganizationCode();
      this.accountCode = m.getAccountCode();
      this.programCode = m.getProgramCode();
      this.activityCode = m.getActivityCode();
      this.locationCode = m.getLocationCode();
      this.encumbranceNumber = m.getEncumbranceNumber();
      this.encumbranceDocType = m.getEncumbranceDocType();
      this.encumbranceItemNumber = m.getEncumbranceItemNumber();
      this.encumbranceItemSeqNo = m.getEncumbranceItemSeqNo();
   }
   
   public InactiveIndices(MaststipSingle m,boolean single)
   {
      this.sequenceNumber = m.getSequenceNumber();
      this.journalType =m.getJournalType();
      this.transactionDescription = m.getTransactionDescription();
      this.documentReferenceNo = m.getDocumentReferenceNo();
      this.transactionAmount = m.getTransactionAmount();
      this.debitCredit = m.getDebitCredit();
      this.coaCode = m.getCoaCode();
      this.indexCode = m.getIndexCode();
      this.fundCode = m.getFundCode();
      this.organizationCode = m.getOrganizationCode();
      this.accountCode = m.getAccountCode();
      this.programCode = m.getProgramCode();
      this.activityCode = m.getActivityCode();
      this.locationCode = m.getLocationCode();
      this.encumbranceNumber = m.getEncumbranceNumber();
      this.encumbranceDocType = m.getEncumbranceDocType();
      this.encumbranceItemNumber = m.getEncumbranceItemNumber();
      this.encumbranceItemSeqNo = m.getEncumbranceItemSeqNo();
      this.single=single;
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

public boolean isUpdated() {
	return updated;
}

public void setUpdated(boolean updated) {
	this.updated = updated;
}

public boolean isSingle() {
	return single;
}

public void setSingle(boolean single) {
	this.single = single;
}
}