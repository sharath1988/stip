package edu.ucsd.som.stip.services;
/**
 * @author         Sharath A.
 */

public class EmployeeRepository {

	public final static String employeeEmailQuery=
													" select A.email from "+
															" ( "+
															" 		select employee_ucsd_id,email from dw.affiliates_dw  "+
																	" where employee_ucsd_id=:empNumber "+
																	" UNION  "+
																	" select EMB_EMPLOYEE_NUMBER,EMPLOYEE_EMAIL from dw.db2_employee_phone   "+
																	" where EMB_EMPLOYEE_NUMBER=:empNumber "+
																	" UNION  "+
																	" select employee_ucsd_id,email from far.far_ds " +
																	" where employee_ucsd_id=:empNumber "+
															" ) A "+
															" where A.email !=:emptyString and A.email is not null ";
	
	public final static String employeeByUcsdId=
								"select e from Employee e where e.ucsdId=:ucsdId ";
	
	public final static String userAuth=					
								"from UserAcct ua where ua.enabled = true and  ua.ucsdId = :ucsdId";
	
	public final static String userAuthRACFID=
								"from UserAcct ua where ua.enabled = true and " +
								"ltrim(rtrim(upper(ua.name))) = :nameRACFID ";
	
	
	public static final  String allDept=
								"from Department d";
	 
	public static final  String deptsForPI=
								"select ed.department from EmployeeDept ed where ed.employee.employeeId=:eid";
	 
	public static final  String pisForBuildings=
								"Select distinct s.id.piUcsdId from Scope s where s.id.buildingName  in (select distinct build.name from Building build " +
								" where build.buildingId in (:buildList) ) " +
						 		" and s.id.departmentName=:departmentName ";
	
	public static final  String pisForReview=
							"from UserAcct u where u.ucsdId in (:piIds)";
	
	public static final String empAutoComplete = 
							   "select user from Db2employee user where user.embEmployeeName like :name and user.embEmployeeNumber" +
		          			   " not in (select user1.employeeId from UserAcct user1 order by user.embEmployeeName)";
	
	public static final String addEmpAutoComplete = 
							   "select user from Db2employee user where user.embEmployeeName like :name and user.embEmployeeNumber" +
							   " not in (select se.employee.ucsdId from SurveyEmployee se where se.survey.surveyId=:surveyId)";
	
	public static final String addProgProjEmplAutoComplete = 
			   "select user from Db2employee user where user.embEmployeeName like :name and user.embEmployeeNumber" +
			   " not in (select pp.piId from ProgramProject pp where pp.survey.surveyId=:surveyId)";
	
	public static final String addFacGrantEmplAutoComplete = 
			   "select user from Db2employee user where user.embEmployeeName like :name and user.embEmployeeNumber" +
			   " not in (select s.piId from Survey s where s.surveyId=:surveyId) and user.embEmployeeNumber" +
			   " not in (select fs.piId from FacultySpace fs where fs.survey.surveyId=:surveyId and fs.piId is not null)";
	
	public static final String addRechargeCoreEmplAutoComplete = 
			   "select user from Db2employee user where user.embEmployeeName like :name and user.embEmployeeNumber" +
			   " not in (select rc.piId from RechargeCore rc where rc.survey.surveyId=:surveyId)";
	
	
	
	public static final String  rolesQuery =
								"select role" +
								" from " +
									" UserRole role" +
								" where " +
									"role.roleId not in" +
								" (" +
								"select ur.roleId from UserAcctRole uar join uar.userRole ur  where uar.userAcct.accountId =:accountId)  ";
	
	public static final String userByUcsdID = 
							   "select u from  UserAcct u where u.ucsdId=:ucsdId";
	
	
	public static final String userByName = 
								"select userAcct from UserAcct userAcct " +
								" where userAcct.name like :name order by userAcct.name ";
	
	public static final String editLinkRender = 
								"select ur.userRole.role from UserAcctRole ur  " +
								" where ur.userAcct.accountId=:accountId and  ur.userAcct.ucsdId not in " +
								" ( select pis.id.embEmployeeNumber from HealthSciencePis pis ) ";
	
	public static final String userFromDb2Emp =
								"from Db2employee employee where " +
								" employee.embEmployeeNumber =:ucsdId "+
								" group by employee.embEmployeeNumber";
	
	public static final String healthSciencePI =
							   " select pi from HealthSciencePis pi " +
						       " where pi.id.embEmployeeNumber =:piId";
	
	public static final String  getUserDeptsNotAssigned =
								" select d from Department d where d.departmentId not in " +
								" (select usd.department.departmentId from UserAcctDept usd " +
								" where usd.userAcct.accountId=:accountId ) " +
								" order by d.deptLongName";
	
	public final static String employeeTitle=
								"	select app_title_name from dw.db2_p_appointment a1 "+
								"	inner join "+
								"	( "+
								"	SELECT  "+
								" 	emb_employee_number,MAX(app_full_time_percent) AS MaxPercentTime,app_end_date "+
								"	FROM "+
								"	dw.db2_p_appointment "+
								"	where  "+
								"   to_days(app_end_date) >= to_days(NOW())  "+
								"	group by "+
								"	emb_employee_number  "+
								"	) a2 "+
								"	on a1.emb_employee_number=a2.emb_employee_number and a1.app_full_time_percent=a2.MaxPercentTime "+
								"	where "+
								"	a1.emb_employee_number =:ucsdId "+
								"	limit 1";
	
	public static final String getAllDepts=
							   " select d from Department d";
	
	public static final String  getRolesForUser=
								" select a from UserAcctRole a join a.userAcct b where b.accountId=:accountId";
	
	public static final String  getSpaceForUser=
								" select r from Room r where " +
								" r.building.buildingId in (:buildIds) " +
								" and r.piUcsdId like :piId " +
								" and r.roomNbr in (select distinct s.id.room from Scope s where s.id.buildingName=r.building.name and s.id.stations=r.stations) ";
	

	
	
	
	public static final String  usersByDeptRole="SELECT distinct u.name,r.role,d.dept_long_name "+
												" FROM "+
												"	tbl_user_acct u "+ 
												"			JOIN tbl_user_acct_role ur "+ 
												"			ON u.account_id=ur.account_id "+ 
												"				JOIN tbl_user_role r  "+
												"				ON r.role_id=ur.role_id  "+
												"               join tbl_user_acct_dept uad "+
												"                  on u.account_id=uad.account_id "+ 
												"                    join tbl_department d on "+
												"                        uad.department_id=d.department_id "+
												"	where r.role_id=3 and d.department_id=:deptId ";
	
}
