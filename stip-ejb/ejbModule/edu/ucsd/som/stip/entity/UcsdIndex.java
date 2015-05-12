package edu.ucsd.som.stip.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.validator.NotNull;

@Entity
@Table(name="ucsd_index"
    ,catalog="som_portal"
)
public class UcsdIndex  implements java.io.Serializable {

	private static final long serialVersionUID = 7077348288309348027L;
	private UcsdIndexId id;

    public UcsdIndex() {
    }

    public UcsdIndex(UcsdIndexId id) {
       this.id = id;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="indx", column=@Column(name="indx", nullable=false, length=10,columnDefinition="char") ), 
        @AttributeOverride(name="indxTitle", column=@Column(name="indxTitle", length=35) ), 
        @AttributeOverride(name="endEffectiveDate", column=@Column(name="endEffectiveDate", length=0) ), 
        @AttributeOverride(name="earlyInactivationDate", column=@Column(name="earlyInactivationDate", length=0) ), 
        @AttributeOverride(name="status", column=@Column(name="status", length=8,columnDefinition="char") ), 
        @AttributeOverride(name="orgHierarchyOne", column=@Column(name="orgHierarchyOne", length=7,columnDefinition="char") ), 
        @AttributeOverride(name="program", column=@Column(name="program", length=6,columnDefinition="char") ), 
        @AttributeOverride(name="organization", column=@Column(name="organization", length=6,columnDefinition="char") ), 
        @AttributeOverride(name="orgTitle", column=@Column(name="orgTitle") ), 
        @AttributeOverride(name="fund", column=@Column(name="fund", length=6,columnDefinition="char") ), 
        @AttributeOverride(name="fundTitle", column=@Column(name="fundTitle", length=35) ), 
        @AttributeOverride(name="divisionId", column=@Column(name="divisionId", nullable=false) ), 
        @AttributeOverride(name="departmentId", column=@Column(name="departmentId", nullable=false) ), 
        @AttributeOverride(name="somMajorGroup", column=@Column(name="somMajorGroup", nullable=false) ) } )
    @NotNull
    public UcsdIndexId getId() {
        return this.id;
    }
    
    public void setId(UcsdIndexId id) {
        this.id = id;
    }




}


