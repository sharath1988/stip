package edu.ucsd.som.stip.services;

public class FiscalYearRptRepository {
	
	
	public static final String fiscalYearList ="select distinct SUBSTRING(s.qrt,1,4) from Stipdetail s where s.status=1 order by s.qrt desc ";
	
	
	public static final String majorGrouplistAdmin ="select distinct s.majorGroup from Stipdetail s where s.status=1 and s.qrt like (:qrt) order by s.majorGroup ";
	
	
	public static final String majorGrouplistDbo=   "select distinct s.majorGroup from Stipdetail s where s.status=1 and s.qrt like (:qrt)  "
													+ " and s.majorGroup in "
													+ " (select uad.department.deptLongName from UserAcctDept uad where uad.userAcct.accountId=:accountId) "
													+ " order by s.majorGroup ";
	
	public static String getFiscalYearRptQuery(String majorGrouplcl, String qtrlcl) {
		return "SELECT "+
				"major_group, "+
				"department, "+
				"index_generated, "+
				"fund_generated, "+
				"index_deposited, "+
				"fund_deposited, "+
				"cast(group_concat(qrt) AS char)  AS qrt_list, "+
				"cast(group_concat(sum_stip) AS char) AS qrt_total_list, "+
				"SUM(sum_stip)          AS fiscal_total "+ 
			"FROM "+
				"vw_fiscal_rpt_main "+ 
			"WHERE "+
				"major_group LIKE ("+majorGrouplcl+") AND "+
				"qrt LIKE ("+qtrlcl+") AND "+
				"sum_stip <> 0 " +
			"GROUP BY "+
				"major_group, "+
				"department, "+
				"index_generated, "+
				"fund_generated, "+
				"index_deposited, "+
				"fund_deposited "+
			"ORDER BY major_group,department,index_generated desc,"+
				"qrt ";
	}

}
