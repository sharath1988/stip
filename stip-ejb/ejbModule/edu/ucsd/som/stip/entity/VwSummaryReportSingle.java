package edu.ucsd.som.stip.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="vw_summary_report_single"
    ,catalog="stip"
)
public class VwSummaryReportSingle  implements java.io.Serializable {

	private static final long serialVersionUID = 1434504974832963930L;
	private VwSummaryReportIdSingle id;

    public VwSummaryReportSingle() {
    }

    public VwSummaryReportSingle(VwSummaryReportIdSingle id) {
       this.id = id;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="dept", column=@Column(name="Dept", length=50) ), 
        @AttributeOverride(name="division", column=@Column(name="Division", length=50) ), 
        @AttributeOverride(name="fyQrt", column=@Column(name="FyQrt", length=7) ), 
       // @AttributeOverride(name="indx", column=@Column(name="indx", length=8) ), 
        @AttributeOverride(name="fund", column=@Column(name="Fund", length=6) ), 
        @AttributeOverride(name="depToIndex", column=@Column(name="Dep_to_idx", length=7) ), 
        @AttributeOverride(name="depToFund", column=@Column(name="Dep_to_Fund", length=6) ), 
        @AttributeOverride(name="amount", column=@Column(name="Amount", precision=22, scale=0) ) } )
    public VwSummaryReportIdSingle getId() {
        return this.id;
    }
    
    public void setId(VwSummaryReportIdSingle id) {
        this.id = id;
    }




}


