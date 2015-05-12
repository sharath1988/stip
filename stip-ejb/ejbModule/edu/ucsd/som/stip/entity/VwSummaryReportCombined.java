package edu.ucsd.som.stip.entity;


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.validator.NotNull;

@Entity
@Table(name = "vw_summary_report_combined", catalog = "stip")
public class VwSummaryReportCombined implements java.io.Serializable {

	private static final long serialVersionUID = -4405510375284382244L;
	private VwSummaryReportCombinedId id;

	public VwSummaryReportCombined() {
	}

	public VwSummaryReportCombined(VwSummaryReportCombinedId id) {
		this.id = id;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "dept", column = @Column(name = "Dept", length = 50)),
			@AttributeOverride(name = "division", column = @Column(name = "Division", length = 50)),
			@AttributeOverride(name = "fyQrt", column = @Column(name = "FyQrt", length = 7)),
			@AttributeOverride(name = "indx", column = @Column(name = "indx", length = 8)),
			@AttributeOverride(name = "fund", column = @Column(name = "Fund", length = 6)),
			@AttributeOverride(name = "depToIdx", column = @Column(name = "Dep_to_idx", nullable = false, length = 7)),
			@AttributeOverride(name = "depToFund", column = @Column(name = "Dep_to_Fund", nullable = false, length = 6)),
			@AttributeOverride(name = "amount", column = @Column(name = "Amount", precision = 22, scale = 0)) })
	@NotNull
	public VwSummaryReportCombinedId getId() {
		return this.id;
	}

	public void setId(VwSummaryReportCombinedId id) {
		this.id = id;
	}

}
