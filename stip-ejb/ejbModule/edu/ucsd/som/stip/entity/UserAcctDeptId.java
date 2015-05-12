package edu.ucsd.som.stip.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserAcctDeptId  implements java.io.Serializable {

	private static final long serialVersionUID = 3059700883988832221L;
	private int accountId;
     private int departmentId;

    public UserAcctDeptId() {
    }

    public UserAcctDeptId(int accountId, int departmentId) {
       this.accountId = accountId;
       this.departmentId = departmentId;
    }
   


    @Column(name="account_id", nullable=false)
    public int getAccountId() {
        return this.accountId;
    }
    
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }


    @Column(name="department_id", nullable=false)
    public int getDepartmentId() {
        return this.departmentId;
    }
    
    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof UserAcctDeptId) ) return false;
		 UserAcctDeptId castOther = ( UserAcctDeptId ) other; 
         
		 return (this.getAccountId()==castOther.getAccountId())
 && (this.getDepartmentId()==castOther.getDepartmentId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getAccountId();
         result = 37 * result + this.getDepartmentId();
         return result;
   }   


}


