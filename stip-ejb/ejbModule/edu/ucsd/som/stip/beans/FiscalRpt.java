package edu.ucsd.som.stip.beans;

import java.math.BigDecimal;

public class FiscalRpt implements java.io.Serializable {

	private static final long serialVersionUID = -5040737911675073466L;
	private String majorGroup;
	private String department;
	private String indexGenerated;
	private String fundGenerated;
	private String indexDeposited;
	private String fundDeposited;
	private String quartersList;
	private String quartersStipList;
	private BigDecimal totalFiscalStip=BigDecimal.ZERO;
	private BigDecimal q1total=BigDecimal.ZERO;
	private BigDecimal q2total=BigDecimal.ZERO;
	private BigDecimal q3total=BigDecimal.ZERO;
	private BigDecimal q4total=BigDecimal.ZERO;

	public FiscalRpt() {
	}

	public FiscalRpt(String majorGroup, String department,
			String indexGenerated, String fundGenerated, String indexDeposited,
			String fundDeposited, String quartersList, String quartersStipList,
			BigDecimal totalFiscalStip) {
		this.majorGroup = majorGroup;
		this.department = department;
		this.indexGenerated = indexGenerated;
		this.fundGenerated = fundGenerated;
		this.indexDeposited = indexDeposited;
		this.fundDeposited = fundDeposited;
		this.quartersList = quartersList;
		this.quartersStipList = quartersStipList;
		this.totalFiscalStip = totalFiscalStip;
	}

	public String getMajorGroup() {
		return this.majorGroup;
	}

	public void setMajorGroup(String majorGroup) {
		this.majorGroup = majorGroup;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getIndexGenerated() {
		return this.indexGenerated;
	}

	public void setIndexGenerated(String indexGenerated) {
		this.indexGenerated = indexGenerated;
	}

	public String getFundGenerated() {
		return this.fundGenerated;
	}

	public void setFundGenerated(String fundGenerated) {
		this.fundGenerated = fundGenerated;
	}

	public String getIndexDeposited() {
		return this.indexDeposited;
	}

	public void setIndexDeposited(String indexDeposited) {
		this.indexDeposited = indexDeposited;
	}

	public String getFundDeposited() {
		return this.fundDeposited;
	}

	public void setFundDeposited(String fundDeposited) {
		this.fundDeposited = fundDeposited;
	}

	public String getQuartersList() {
		return this.quartersList;
	}

	public void setQuartersList(String quartersList) {
		this.quartersList = quartersList;
	}

	public String getQuartersStipList() {
		return this.quartersStipList;
	}

	public void setQuartersStipList(String quartersStipList) {
		this.quartersStipList = quartersStipList;
	}

	public BigDecimal getTotalFiscalStip() {
		return this.totalFiscalStip;
	}

	public void setTotalFiscalStip(BigDecimal totalFiscalStip) {
		this.totalFiscalStip = totalFiscalStip;
	}
	
	
	public BigDecimal getQ2total() {
		return q2total;
	}

	
	public void setQ2total(BigDecimal q2total) {
		this.q2total = q2total;
	}

	public BigDecimal getQ1total() {
		return q1total;
	}

	public void setQ1total(BigDecimal q1total) {
		this.q1total = q1total;
	}

	public BigDecimal getQ4total() {
		return q4total;
	}

	public void setQ4total(BigDecimal q4total) {
		this.q4total = q4total;
	}

	public BigDecimal getQ3total() {
		return q3total;
	}

	public void setQ3total(BigDecimal q3total) {
		this.q3total = q3total;
	}


}
