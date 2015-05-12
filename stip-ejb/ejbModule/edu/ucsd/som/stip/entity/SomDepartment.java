package edu.ucsd.som.stip.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.Length;

@Entity
@Table(name="som_department"
    ,catalog="som_portal"
)
public class SomDepartment  implements java.io.Serializable {

	private static final long serialVersionUID = -6716536674935683338L;
	
	 private int somDepartmentId;
     private String name;
     private String dboEmployeeId;
     private String dboUcsdId;
     private int majorGroupId;
     private Character vcUnitCode;
     
    public SomDepartment() {
    }
	
    public SomDepartment(int somDepartmentId, int majorGroupId) {
        this.somDepartmentId = somDepartmentId;
        this.majorGroupId = majorGroupId;
    }
    public SomDepartment(int somDepartmentId, String name, String dboEmployeeId, String dboUcsdId, int majorGroupId, Character vcUnitCode) {
       this.somDepartmentId = somDepartmentId;
       this.name = name;
       this.dboEmployeeId = dboEmployeeId;
       this.dboUcsdId = dboUcsdId;
       this.majorGroupId = majorGroupId;
       this.vcUnitCode = vcUnitCode;
    }
   
     @Id 
  
    @Column(name="som_department_id", unique=true, nullable=false)
    public int getSomDepartmentId() {
        return this.somDepartmentId;
    }
    
    public void setSomDepartmentId(int somDepartmentId) {
        this.somDepartmentId = somDepartmentId;
    }

    
    @Column(name="name", length=50)
    @Length(max=50)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="dbo_employee_id", length=11)
    @Length(max=11)
    public String getDboEmployeeId() {
        return this.dboEmployeeId;
    }
    
    public void setDboEmployeeId(String dboEmployeeId) {
        this.dboEmployeeId = dboEmployeeId;
    }

    
    @Column(name="dbo_ucsd_id", length=10)
    @Length(max=10)
    public String getDboUcsdId() {
        return this.dboUcsdId;
    }
    
    public void setDboUcsdId(String dboUcsdId) {
        this.dboUcsdId = dboUcsdId;
    }

    
    @Column(name="major_group_id", nullable=false)
    public int getMajorGroupId() {
        return this.majorGroupId;
    }
    
    public void setMajorGroupId(int majorGroupId) {
        this.majorGroupId = majorGroupId;
    }

    
    @Column(name="vc_unit_code", length=1)
    public Character getVcUnitCode() {
        return this.vcUnitCode;
    }
    
    public void setVcUnitCode(Character vcUnitCode) {
        this.vcUnitCode = vcUnitCode;
    }
}


