package edu.ucsd.som.stip.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

@Entity
@Table(name="som_division"
    ,catalog="som_portal"
)
public class SomDivision  implements java.io.Serializable {

	private static final long serialVersionUID = 2335826989582875489L;
	
	 private Integer divisionId;
     private byte refNum;
     private String name;
     private int departmentId;
     private String dboEmployeeId;

    public SomDivision() {
    }

	
    public SomDivision(byte refNum, String name, int departmentId) {
        this.refNum = refNum;
        this.name = name;
        this.departmentId = departmentId;
    }
    public SomDivision(byte refNum, String name, int departmentId, String dboEmployeeId) {
       this.refNum = refNum;
       this.name = name;
       this.departmentId = departmentId;
       this.dboEmployeeId = dboEmployeeId;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="division_id", unique=true, nullable=false)
    public Integer getDivisionId() {
        return this.divisionId;
    }
    
    public void setDivisionId(Integer divisionId) {
        this.divisionId = divisionId;
    }

    
    @Column(name="ref_num", nullable=false,columnDefinition="int")
    public byte getRefNum() {
        return this.refNum;
    }
    
    public void setRefNum(byte refNum) {
        this.refNum = refNum;
    }

    
    @Column(name="name", nullable=false, length=35)
    @NotNull
    @Length(max=35)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="department_id", nullable=false)
    public int getDepartmentId() {
        return this.departmentId;
    }
    
    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    
    @Column(name="dbo_employee_id", length=10)
    @Length(max=10)
    public String getDboEmployeeId() {
        return this.dboEmployeeId;
    }
    
    public void setDboEmployeeId(String dboEmployeeId) {
        this.dboEmployeeId = dboEmployeeId;
    }




}


