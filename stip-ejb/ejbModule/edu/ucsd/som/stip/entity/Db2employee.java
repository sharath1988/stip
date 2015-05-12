package edu.ucsd.som.stip.entity;
/**
 * @author         Sharath A.
 */
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

@Entity
@Table(name="vw_db2employee" ,catalog="stip")

public class Db2employee  implements java.io.Serializable {

	private static final long serialVersionUID = 6915764233563535397L;
	private int embEmployeeNumber;
     private Date refreshDate;
     private int redRefreshType;
     private int embPersonId;
     private String empChangeCode;
     private String embEmployeeName;
     private String empEmploymentStatusCode;
     private Date empActionDate;
     private Date empHireDate;
     private Date empOriginalHireDate;
     private String empHomeDepartmentCode;
     private String empAlternateDepartmentCode;
     private String empMailcode;
     private String empPayDistributionCode;
     private Date empSeparationDate;
     private Date empLoaReturnDate;
     private String empLoaStatusCode;
     private String empEmpRelationUnitCode;
     private Date empNextSalaryReviewDate;
     private String empEmpHomeDepartmentName;
     private String embEmployeeId;
     private String empAllDepartments;
     private String empNameSuffix;
     private String empPriorName;
     private String empFirstName;
     private String empMiddleName;
     private String empLastName;
     private String viceChancellorUnitCode;
     private String empCitizenshipCode;
     private String empEmployeeRelationCode;
     private String empAssignedBenefitEligCode;
     private String empDerivedBenefitEligCode;
     private String empHomePhone;
     private Date empProbationEndDate;
     private String secSeparationReasonCode;
     private Date pieEndDate;

    public Db2employee() {
    }

	
    public Db2employee(int embEmployeeNumber, Date refreshDate, int redRefreshType, int embPersonId, String empChangeCode) {
        this.embEmployeeNumber = embEmployeeNumber;
        this.refreshDate = refreshDate;
        this.redRefreshType = redRefreshType;
        this.embPersonId = embPersonId;
        this.empChangeCode = empChangeCode;
    }
    public Db2employee(int embEmployeeNumber, Date refreshDate, int redRefreshType, int embPersonId, String empChangeCode, String embEmployeeName, String empEmploymentStatusCode, Date empActionDate, Date empHireDate, Date empOriginalHireDate, String empHomeDepartmentCode, String empAlternateDepartmentCode, String empMailcode, String empPayDistributionCode, Date empSeparationDate, Date empLoaReturnDate, String empLoaStatusCode, String empEmpRelationUnitCode, Date empNextSalaryReviewDate, String empEmpHomeDepartmentName, String embEmployeeId, String empAllDepartments, String empNameSuffix, String empPriorName, String empFirstName, String empMiddleName, String empLastName, String viceChancellorUnitCode, String empCitizenshipCode, String empEmployeeRelationCode, String empAssignedBenefitEligCode, String empDerivedBenefitEligCode, String empHomePhone, Date empProbationEndDate, String secSeparationReasonCode, Date pieEndDate) {
       this.embEmployeeNumber = embEmployeeNumber;
       this.refreshDate = refreshDate;
       this.redRefreshType = redRefreshType;
       this.embPersonId = embPersonId;
       this.empChangeCode = empChangeCode;
       this.embEmployeeName = embEmployeeName;
       this.empEmploymentStatusCode = empEmploymentStatusCode;
       this.empActionDate = empActionDate;
       this.empHireDate = empHireDate;
       this.empOriginalHireDate = empOriginalHireDate;
       this.empHomeDepartmentCode = empHomeDepartmentCode;
       this.empAlternateDepartmentCode = empAlternateDepartmentCode;
       this.empMailcode = empMailcode;
       this.empPayDistributionCode = empPayDistributionCode;
       this.empSeparationDate = empSeparationDate;
       this.empLoaReturnDate = empLoaReturnDate;
       this.empLoaStatusCode = empLoaStatusCode;
       this.empEmpRelationUnitCode = empEmpRelationUnitCode;
       this.empNextSalaryReviewDate = empNextSalaryReviewDate;
       this.empEmpHomeDepartmentName = empEmpHomeDepartmentName;
       this.embEmployeeId = embEmployeeId;
       this.empAllDepartments = empAllDepartments;
       this.empNameSuffix = empNameSuffix;
       this.empPriorName = empPriorName;
       this.empFirstName = empFirstName;
       this.empMiddleName = empMiddleName;
       this.empLastName = empLastName;
       this.viceChancellorUnitCode = viceChancellorUnitCode;
       this.empCitizenshipCode = empCitizenshipCode;
       this.empEmployeeRelationCode = empEmployeeRelationCode;
       this.empAssignedBenefitEligCode = empAssignedBenefitEligCode;
       this.empDerivedBenefitEligCode = empDerivedBenefitEligCode;
       this.empHomePhone = empHomePhone;
       this.empProbationEndDate = empProbationEndDate;
       this.secSeparationReasonCode = secSeparationReasonCode;
       this.pieEndDate = pieEndDate;
    }
   
     @Id 

    
    @Column(name="EMB_EMPLOYEE_NUMBER", unique=true, nullable=false)
    public int getEmbEmployeeNumber() {
        return this.embEmployeeNumber;
    }
    
    public void setEmbEmployeeNumber(int embEmployeeNumber) {
        this.embEmployeeNumber = embEmployeeNumber;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="REFRESH_DATE", nullable=false, length=10)
    @NotNull
    public Date getRefreshDate() {
        return this.refreshDate;
    }
    
    public void setRefreshDate(Date refreshDate) {
        this.refreshDate = refreshDate;
    }

    
    @Column(name="RED_REFRESH_TYPE", nullable=false)
    public int getRedRefreshType() {
        return this.redRefreshType;
    }
    
    public void setRedRefreshType(int redRefreshType) {
        this.redRefreshType = redRefreshType;
    }

    
    @Column(name="EMB_PERSON_ID", nullable=false)
    public int getEmbPersonId() {
        return this.embPersonId;
    }
    
    public void setEmbPersonId(int embPersonId) {
        this.embPersonId = embPersonId;
    }

    
    @Column(name = "EMP_CHANGE_CODE", length = 1, nullable = false, columnDefinition = "char(1)")
	@NotNull
    public String getEmpChangeCode() {
        return this.empChangeCode;
    }
    
    public void setEmpChangeCode(String empChangeCode) {
        this.empChangeCode = empChangeCode;
    }

    
    @Column(name="EMB_EMPLOYEE_NAME", length=26)
    @Length(max=26)
    public String getEmbEmployeeName() {
        return this.embEmployeeName;
    }
    
    public void setEmbEmployeeName(String embEmployeeName) {
        this.embEmployeeName = embEmployeeName;
    }

    
    @Column(name = "EMP_EMPLOYMENT_STATUS_CODE", columnDefinition = "char(1)", length = 1)
	@Length(max = 1)
    public String getEmpEmploymentStatusCode() {
        return this.empEmploymentStatusCode;
    }
    
    public void setEmpEmploymentStatusCode(String empEmploymentStatusCode) {
        this.empEmploymentStatusCode = empEmploymentStatusCode;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="EMP_ACTION_DATE", length=10)
    public Date getEmpActionDate() {
        return this.empActionDate;
    }
    
    public void setEmpActionDate(Date empActionDate) {
        this.empActionDate = empActionDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="EMP_HIRE_DATE", length=10)
    public Date getEmpHireDate() {
        return this.empHireDate;
    }
    
    public void setEmpHireDate(Date empHireDate) {
        this.empHireDate = empHireDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="EMP_ORIGINAL_HIRE_DATE", length=10)
    public Date getEmpOriginalHireDate() {
        return this.empOriginalHireDate;
    }
    
    public void setEmpOriginalHireDate(Date empOriginalHireDate) {
        this.empOriginalHireDate = empOriginalHireDate;
    }

    
    @Column(name="EMP_HOME_DEPARTMENT_CODE", length=6)
    @Length(max=6)
    public String getEmpHomeDepartmentCode() {
        return this.empHomeDepartmentCode;
    }
    
    public void setEmpHomeDepartmentCode(String empHomeDepartmentCode) {
        this.empHomeDepartmentCode = empHomeDepartmentCode;
    }

    
    @Column(name="EMP_ALTERNATE_DEPARTMENT_CODE", length=6)
    @Length(max=6)
    public String getEmpAlternateDepartmentCode() {
        return this.empAlternateDepartmentCode;
    }
    
    public void setEmpAlternateDepartmentCode(String empAlternateDepartmentCode) {
        this.empAlternateDepartmentCode = empAlternateDepartmentCode;
    }

    
    @Column(name="EMP_MAILCODE", length=5)
    @Length(max=5)
    public String getEmpMailcode() {
        return this.empMailcode;
    }
    
    public void setEmpMailcode(String empMailcode) {
        this.empMailcode = empMailcode;
    }

    
    @Column(name = "EMP_PAY_DISTRIBUTION_CODE", columnDefinition = "char(1)", length = 1)
	@Length(max = 1)
    public String getEmpPayDistributionCode() {
        return this.empPayDistributionCode;
    }
    
    public void setEmpPayDistributionCode(String empPayDistributionCode) {
        this.empPayDistributionCode = empPayDistributionCode;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="EMP_SEPARATION_DATE", length=10)
    public Date getEmpSeparationDate() {
        return this.empSeparationDate;
    }
    
    public void setEmpSeparationDate(Date empSeparationDate) {
        this.empSeparationDate = empSeparationDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="EMP_LOA_RETURN_DATE", length=10)
    public Date getEmpLoaReturnDate() {
        return this.empLoaReturnDate;
    }
    
    public void setEmpLoaReturnDate(Date empLoaReturnDate) {
        this.empLoaReturnDate = empLoaReturnDate;
    }

    
    @Column(name = "EMP_LOA_STATUS_CODE", columnDefinition = "char(1)", length = 1)
	@Length(max = 1)
    public String getEmpLoaStatusCode() {
        return this.empLoaStatusCode;
    }
    
    public void setEmpLoaStatusCode(String empLoaStatusCode) {
        this.empLoaStatusCode = empLoaStatusCode;
    }

    
    @Column(name="EMP_EMP_RELATION_UNIT_CODE", length=2)
    @Length(max=2)
    public String getEmpEmpRelationUnitCode() {
        return this.empEmpRelationUnitCode;
    }
    
    public void setEmpEmpRelationUnitCode(String empEmpRelationUnitCode) {
        this.empEmpRelationUnitCode = empEmpRelationUnitCode;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="EMP_NEXT_SALARY_REVIEW_DATE", length=10)
    public Date getEmpNextSalaryReviewDate() {
        return this.empNextSalaryReviewDate;
    }
    
    public void setEmpNextSalaryReviewDate(Date empNextSalaryReviewDate) {
        this.empNextSalaryReviewDate = empNextSalaryReviewDate;
    }

    
    @Column(name="EMP_EMP_HOME_DEPARTMENT_NAME", length=30)
    @Length(max=30)
    public String getEmpEmpHomeDepartmentName() {
        return this.empEmpHomeDepartmentName;
    }
    
    public void setEmpEmpHomeDepartmentName(String empEmpHomeDepartmentName) {
        this.empEmpHomeDepartmentName = empEmpHomeDepartmentName;
    }

    
    @Column(name="EMB_EMPLOYEE_ID", length=9)
    @Length(max=9)
    public String getEmbEmployeeId() {
        return this.embEmployeeId;
    }
    
    public void setEmbEmployeeId(String embEmployeeId) {
        this.embEmployeeId = embEmployeeId;
    }

    
    @Column(name="EMP_ALL_DEPARTMENTS", length=62)
    @Length(max=62)
    public String getEmpAllDepartments() {
        return this.empAllDepartments;
    }
    
    public void setEmpAllDepartments(String empAllDepartments) {
        this.empAllDepartments = empAllDepartments;
    }

    
    @Column(name="EMP_NAME_SUFFIX", length=4)
    @Length(max=4)
    public String getEmpNameSuffix() {
        return this.empNameSuffix;
    }
    
    public void setEmpNameSuffix(String empNameSuffix) {
        this.empNameSuffix = empNameSuffix;
    }

    
    @Column(name="EMP_PRIOR_NAME", length=26)
    @Length(max=26)
    public String getEmpPriorName() {
        return this.empPriorName;
    }
    
    public void setEmpPriorName(String empPriorName) {
        this.empPriorName = empPriorName;
    }

    
    @Column(name="EMP_FIRST_NAME", length=30)
    @Length(max=30)
    public String getEmpFirstName() {
        return this.empFirstName;
    }
    
    public void setEmpFirstName(String empFirstName) {
        this.empFirstName = empFirstName;
    }

    
    @Column(name="EMP_MIDDLE_NAME", length=30)
    @Length(max=30)
    public String getEmpMiddleName() {
        return this.empMiddleName;
    }
    
    public void setEmpMiddleName(String empMiddleName) {
        this.empMiddleName = empMiddleName;
    }

    
    @Column(name="EMP_LAST_NAME", length=30)
    @Length(max=30)
    public String getEmpLastName() {
        return this.empLastName;
    }
    
    public void setEmpLastName(String empLastName) {
        this.empLastName = empLastName;
    }

    
    @Column(name = "VICE_CHANCELLOR_UNIT_CODE", columnDefinition = "char(1)", length = 1)
	@Length(max = 1)
    public String getViceChancellorUnitCode() {
        return this.viceChancellorUnitCode;
    }
    
    public void setViceChancellorUnitCode(String viceChancellorUnitCode) {
        this.viceChancellorUnitCode = viceChancellorUnitCode;
    }

    
    @Column(name = "EMP_CITIZENSHIP_CODE", columnDefinition = "char(1)", length = 1)
	@Length(max = 1)
    public String getEmpCitizenshipCode() {
        return this.empCitizenshipCode;
    }
    
    public void setEmpCitizenshipCode(String empCitizenshipCode) {
        this.empCitizenshipCode = empCitizenshipCode;
    }

    
    @Column(name = "EMP_EMPLOYEE_RELATION_CODE", columnDefinition = "char(1)", length = 1)
	@Length(max = 1)
    public String getEmpEmployeeRelationCode() {
        return this.empEmployeeRelationCode;
    }
    
    public void setEmpEmployeeRelationCode(String empEmployeeRelationCode) {
        this.empEmployeeRelationCode = empEmployeeRelationCode;
    }

    
    @Column(name = "EMP_ASSIGNED_BENEFIT_ELIG_CODE", columnDefinition = "char(1)", length = 1)
	@Length(max = 1)
    public String getEmpAssignedBenefitEligCode() {
        return this.empAssignedBenefitEligCode;
    }
    
    public void setEmpAssignedBenefitEligCode(String empAssignedBenefitEligCode) {
        this.empAssignedBenefitEligCode = empAssignedBenefitEligCode;
    }

    
    @Column(name = "EMP_DERIVED_BENEFIT_ELIG_CODE", columnDefinition = "char(1)", length = 1)
	@Length(max = 1)
    public String getEmpDerivedBenefitEligCode() {
        return this.empDerivedBenefitEligCode;
    }
    
    public void setEmpDerivedBenefitEligCode(String empDerivedBenefitEligCode) {
        this.empDerivedBenefitEligCode = empDerivedBenefitEligCode;
    }

    
    @Column(name="EMP_HOME_PHONE", length=25)
    @Length(max=25)
    public String getEmpHomePhone() {
        return this.empHomePhone;
    }
    
    public void setEmpHomePhone(String empHomePhone) {
        this.empHomePhone = empHomePhone;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="EMP_PROBATION_END_DATE", length=10)
    public Date getEmpProbationEndDate() {
        return this.empProbationEndDate;
    }
    
    public void setEmpProbationEndDate(Date empProbationEndDate) {
        this.empProbationEndDate = empProbationEndDate;
    }

    
    @Column(name = "SEC_SEPARATION_REASON_CODE", columnDefinition = "char(2)", length = 2)
	@Length(max = 2)
    public String getSecSeparationReasonCode() {
        return this.secSeparationReasonCode;
    }
    
    public void setSecSeparationReasonCode(String secSeparationReasonCode) {
        this.secSeparationReasonCode = secSeparationReasonCode;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="PIE_END_DATE", length=10)
    public Date getPieEndDate() {
        return this.pieEndDate;
    }
    
    public void setPieEndDate(Date pieEndDate) {
        this.pieEndDate = pieEndDate;
    }




}


