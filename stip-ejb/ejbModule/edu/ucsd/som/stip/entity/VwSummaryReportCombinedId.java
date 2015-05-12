package edu.ucsd.som.stip.entity;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

@Embeddable
public class VwSummaryReportCombinedId implements java.io.Serializable {

	private static final long serialVersionUID = -3148940001809214346L;
	private String dept;
	private String division;
	private String fyQrt;
	private String indx;
	private String fund;
	private String depToIdx;
	private String depToFund;
	private Double amount;

	public VwSummaryReportCombinedId() {
	}

	public VwSummaryReportCombinedId(String depToIdx, String depToFund) {
		this.depToIdx = depToIdx;
		this.depToFund = depToFund;
	}

	public VwSummaryReportCombinedId(String dept, String division,
			String fyQrt, String indx, String fund, String depToIdx,
			String depToFund, Double amount) {
		this.dept = dept;
		this.division = division;
		this.fyQrt = fyQrt;
		this.indx = indx;
		this.fund = fund;
		this.depToIdx = depToIdx;
		this.depToFund = depToFund;
		this.amount = amount;
	}

	@Column(name = "Dept", length = 50)
	@Length(max = 50)
	public String getDept() {
		return this.dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	@Column(name = "Division", length = 50)
	@Length(max = 50)
	public String getDivision() {
		return this.division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	@Column(name = "FyQrt", length = 7)
	@Length(max = 7)
	public String getFyQrt() {
		return this.fyQrt;
	}

	public void setFyQrt(String fyQrt) {
		this.fyQrt = fyQrt;
	}

	@Column(name = "indx", length = 8)
	@Length(max = 8)
	public String getIndx() {
		return this.indx;
	}

	public void setIndx(String indx) {
		this.indx = indx;
	}

	@Column(name = "Fund", length = 6)
	@Length(max = 6)
	public String getFund() {
		return this.fund;
	}

	public void setFund(String fund) {
		this.fund = fund;
	}

	@Column(name = "Dep_to_idx", nullable = false, length = 7)
	@NotNull
	@Length(max = 7)
	public String getDepToIdx() {
		return this.depToIdx;
	}

	public void setDepToIdx(String depToIdx) {
		this.depToIdx = depToIdx;
	}

	@Column(name = "Dep_to_Fund", nullable = false, length = 6)
	@NotNull
	@Length(max = 6)
	public String getDepToFund() {
		return this.depToFund;
	}

	public void setDepToFund(String depToFund) {
		this.depToFund = depToFund;
	}

	@Column(name = "Amount", precision = 22, scale = 0)
	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VwSummaryReportCombinedId))
			return false;
		VwSummaryReportCombinedId castOther = (VwSummaryReportCombinedId) other;

		return ((this.getDept() == castOther.getDept()) || (this.getDept() != null
				&& castOther.getDept() != null && this.getDept().equals(
				castOther.getDept())))
				&& ((this.getDivision() == castOther.getDivision()) || (this
						.getDivision() != null
						&& castOther.getDivision() != null && this
						.getDivision().equals(castOther.getDivision())))
				&& ((this.getFyQrt() == castOther.getFyQrt()) || (this
						.getFyQrt() != null && castOther.getFyQrt() != null && this
						.getFyQrt().equals(castOther.getFyQrt())))
				&& ((this.getIndx() == castOther.getIndx()) || (this.getIndx() != null
						&& castOther.getIndx() != null && this.getIndx()
						.equals(castOther.getIndx())))
				&& ((this.getFund() == castOther.getFund()) || (this.getFund() != null
						&& castOther.getFund() != null && this.getFund()
						.equals(castOther.getFund())))
				&& ((this.getDepToIdx() == castOther.getDepToIdx()) || (this
						.getDepToIdx() != null
						&& castOther.getDepToIdx() != null && this
						.getDepToIdx().equals(castOther.getDepToIdx())))
				&& ((this.getDepToFund() == castOther.getDepToFund()) || (this
						.getDepToFund() != null
						&& castOther.getDepToFund() != null && this
						.getDepToFund().equals(castOther.getDepToFund())))
				&& ((this.getAmount() == castOther.getAmount()) || (this
						.getAmount() != null && castOther.getAmount() != null && this
						.getAmount().equals(castOther.getAmount())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getDept() == null ? 0 : this.getDept().hashCode());
		result = 37 * result
				+ (getDivision() == null ? 0 : this.getDivision().hashCode());
		result = 37 * result
				+ (getFyQrt() == null ? 0 : this.getFyQrt().hashCode());
		result = 37 * result
				+ (getIndx() == null ? 0 : this.getIndx().hashCode());
		result = 37 * result
				+ (getFund() == null ? 0 : this.getFund().hashCode());
		result = 37 * result
				+ (getDepToIdx() == null ? 0 : this.getDepToIdx().hashCode());
		result = 37 * result
				+ (getDepToFund() == null ? 0 : this.getDepToFund().hashCode());
		result = 37 * result
				+ (getAmount() == null ? 0 : this.getAmount().hashCode());
		return result;
	}

}
