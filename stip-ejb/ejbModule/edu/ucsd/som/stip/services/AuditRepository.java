package edu.ucsd.som.stip.services;

public class AuditRepository {
	
	public static final String stipValidateQuery="call validatestip(:qtrYr)";

	public static final String diffInStipAmtQuery="SELECT "+
													"A.quarter, B.fund,  "+
												   " B.fin*-1                   AS ifis_total, "+
													"SUM(A.stip_total)             AS excel_total, "+
													"B.fin*-1-sum(A.stip_total) AS difference  "+
												"FROM "+
													"stip_fund A "+ 
														"JOIN (	SELECT "+
																	"DISTINCT cast(l.fund as CHAR(50)) as fund, "+
																	"SUM(l.financial) as fin, "+
																	"l.docNum  "+
																"FROM "+
																	"Ledger.dtlTrans l "+ 
																	"JOIN stip_dept_ctrl d "+ 
																	"ON l.fund=d.stip_fund AND "+
																	"l.indexNum=d.indx  "+
																"WHERE "+
																	"l.docNum=:docNum AND "+
																	"l.ruleClass='STP1' AND "+
																	"l.accountingPeriod BETWEEN :qtrYrSt AND :QtrYrEnd "+
																"GROUP BY "+
																	"l.fund, "+
																	"l.accountingPeriod, "+
																	"l.docNum  "+
												             ") B  "+
														"ON A.recipient_fund=B.fund "+ 
												"WHERE "+
													"A.quarter=:qtrYr "+ 
												"GROUP BY "+
													"A.recipient_fund, "+
													"A.quarter ";
	
	public static final String qrtymdValidateQuery="call process_stip_distribution(:qtrYr,:intRate)";
	
	public static final String checkMismatchQuery="select ischecksignvalid(:qrtYr)";
	
	public static final String signMismatchesQuery="from Qrtymd where stipAmt <=0 and sumFinalStipFund>0 and qrt=:qrt and single <> 1 " +
												   " union  " +
												   "from Qrtymd where stipAmt >= 0 and sumFinalStipFund<0 and qrt=:qrt and single <> 1 ";
	
	public static final String cerateJournalNonSingleQuery="call createjournal_nonsingle(:qrtYr,:qrtInput)";
	
	public static final String cerateJournalSingleQuery="call createjournal_single(:qrtYr,:qrtInput)";
	
	public static final String missingIndicesQuery="from Stipdetail d where d.qrt = :qrt and d.stipAmt <> 0 and d.stipAmt is not null " +
			 								       " and d.portionStip <> 0 and (d.portionStip > 0.01 or d.portionStip < -0.01) and "
			 								       +" d.dept is null group by d.pfFund order by d.pfFund";
	
	public static final String missingIndicessingleQuery="from Stipdetailsingle d where d.qrt = :qrt and d.stipAmt <> 0 and d.stipAmt is not null" +
			 											 " and (d.stipAmt >=0.01 or d.stipAmt <=-0.01) and d.dept is null group by d.pfFund order by d.pfFund";
	
	public static final String inactiveIndicesQuery="select s from Stipdetail s,UcsdIndex u where s.inde=u.id.indx and s.qrt=:qrt" +
			 															"	and s.inde is not null and u.id.status='Inactive' group by s.inde ";
	
	public static final String inactiveIndicessingleQuery="select s from Stipdetailsingle s,UcsdIndex u where s.inde=u.id.indx and s.qrt=:qrt" +
																				"	and s.inde is not null and u.id.status='Inactive' group by s.inde ";
	
	public static final String missingIndicesExpandQuery="from Stipdetail d where d.qrt = :qrt and d.stipAmt <> 0 and d.stipAmt is not null " +
						 								 " and d.portionStip <> 0 and (d.portionStip > 0.01 or d.portionStip < -0.01) and d.dept is null order by d.pfFund";
	
	public static final String missingIndicessingleExpandQuery="from Stipdetailsingle d where d.qrt = :qrt and d.stipAmt <> 0 and d.stipAmt is not null" +
																" and (d.stipAmt >=0.01 or d.stipAmt <=-0.01) and d.dept is null order by d.pfFund";
	
	public static final String inactiveIndicesExpandQuery="select s from Stipdetail s,UcsdIndex u where s.inde=u.id.indx and s.qrt=:qrt" +
															"	and s.inde is not null and u.id.status='Inactive' group by s.inde ";
	
	public static final String inactiveIndicessingleExpandQuery="select s from Stipdetailsingle s,UcsdIndex u where s.inde=u.id.indx and s.qrt=:qrt" +
																"	and s.inde is not null and u.id.status='Inactive' group by s.inde ";
	
	public static final String singleJournalQuery="from Journal where quarter=:qtrYr and type='Single'";
	
	public static final String nonSingleJournalQuery="from Journal where quarter=:qtrYr and type='Non-Single'";

}
