package edu.ucsd.som.stip.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserAcctRoleId  implements java.io.Serializable {

	private static final long serialVersionUID = -8026498070401323468L;

	private int roleId;
     private int accountId;

    public UserAcctRoleId() {
    }

    public UserAcctRoleId(int roleId, int accountId) {
       this.roleId = roleId;
       this.accountId = accountId;
    }
   


    @Column(name="role_id", nullable=false)
    public int getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }


    @Column(name="account_id", nullable=false)
    public int getAccountId() {
        return this.accountId;
    }
    
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof UserAcctRoleId) ) return false;
		 UserAcctRoleId castOther = ( UserAcctRoleId ) other; 
         
		 return (this.getRoleId()==castOther.getRoleId())
 && (this.getAccountId()==castOther.getAccountId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getRoleId();
         result = 37 * result + this.getAccountId();
         return result;
   }   


}


