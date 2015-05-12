package edu.ucsd.som.stip.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.Length;

@Entity
@Table(name="som_major_group"
    ,catalog="som_portal"
)
public class SomMajorGroup  implements java.io.Serializable {

	private static final long serialVersionUID = -4608082506098456968L;
	private int somMajorGroupId;
     private String name;

    public SomMajorGroup() {
    }

	
    public SomMajorGroup(int somMajorGroupId) {
        this.somMajorGroupId = somMajorGroupId;
    }
    public SomMajorGroup(int somMajorGroupId, String name) {
       this.somMajorGroupId = somMajorGroupId;
       this.name = name;
    }
   
     @Id 

    
    @Column(name="som_major_group_id", unique=true, nullable=false)
    public int getSomMajorGroupId() {
        return this.somMajorGroupId;
    }
    
    public void setSomMajorGroupId(int somMajorGroupId) {
        this.somMajorGroupId = somMajorGroupId;
    }

    
    @Column(name="name", length=50)
    @Length(max=50)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }




}


