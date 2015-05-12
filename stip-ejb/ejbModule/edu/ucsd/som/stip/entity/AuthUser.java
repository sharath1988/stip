package edu.ucsd.som.stip.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "AuthUser", catalog = "som_portal")
@XmlRootElement(name = "user")
public class AuthUser implements Serializable {

	private static final long serialVersionUID = 997185964893963173L;
	
	private String userId;
	private String employeeId;
	private String fullName;
	private String firstName;
	private String lastName;
	private String middleName;
	private String titleCode;
	private String titleDesc;
	private String email;
	private String homeDeptDesc;
	private Integer homeDeptCode;
	private String employeeStatus;
	private String racfId;
	private int impersonateFlag;
	private String phone;
	private List<AuthRole> roles;
	
	public AuthUser() {}
	
	public AuthUser(String userId, String fullName) {
		this.userId = userId;
		this.fullName = fullName;
	}

	@Id
	@Column(name = "userId", unique = true, nullable = false)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "employeeId")
	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	@Column(name = "fullName")
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name = "firstName")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "lastName")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "middleName")
	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Column(name = "titleCode")
	public String getTitleCode() {
		return titleCode;
	}
	
	public void setTitleCode(String titleCode) {
		this.titleCode = titleCode;
	}

	@Column(name = "titleDesc")
	public String getTitleDesc() {
		return titleDesc;
	}

	public void setTitleDesc(String titleDesc) {
		this.titleDesc = titleDesc;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "homeDeptDesc")
	public String getHomeDeptDesc() {
		return homeDeptDesc;
	}

	public void setHomeDeptDesc(String homeDeptDesc) {
		this.homeDeptDesc = homeDeptDesc;
	}

	@Column(name = "homeDeptCode")
	public Integer getHomeDeptCode() {
		return homeDeptCode;
	}

	public void setHomeDeptCode(Integer homeDeptCode) {
		this.homeDeptCode = homeDeptCode;
	}

	@Column(name = "employeeStatus")
	public String getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	@Column(name = "racfId")
	public String getRacfId() {
		return racfId;
	}

	public void setRacfId(String racfId) {
		this.racfId = racfId;
	}

	@Column(name = "impersonateFlag")
	public int getImpersonateFlag() {
		return impersonateFlag;
	}

	public void setImpersonateFlag(int impersonateFlag) {
		this.impersonateFlag = impersonateFlag;
	}

	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@XmlElementWrapper
	@XmlElement(name = "role")
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "AuthUserRole", catalog = "som_portal",
			joinColumns = @JoinColumn(name = "userId", referencedColumnName = "userId"),
			inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "name"))
	public List<AuthRole> getRoles() {
		return roles;
	}

	public void setRoles(List<AuthRole> roles) {
		this.roles = roles;
	}

}
