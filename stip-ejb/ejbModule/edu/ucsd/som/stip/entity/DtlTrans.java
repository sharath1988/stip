package edu.ucsd.som.stip.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.validator.NotNull;

@Entity
@Table(name="dtlTrans"
    ,catalog="Ledger"
)
public class DtlTrans  implements java.io.Serializable {

	private static final long serialVersionUID = -6476034692947170352L;
	private DtlTransId id;

    public DtlTrans() {
    }

    public DtlTrans(DtlTransId id) {
       this.id = id;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="an", column=@Column(name="an") ), 
        @AttributeOverride(name="accountingPeriod", column=@Column(name="accountingPeriod", nullable=false) ), 
        @AttributeOverride(name="indexNum", column=@Column(name="indexNum", nullable=false, length=10,columnDefinition="char") ), 
        @AttributeOverride(name="fund", column=@Column(name="fund", nullable=false, length=6,columnDefinition="char") ), 
        @AttributeOverride(name="org", column=@Column(name="org", nullable=false, length=6,columnDefinition="char") ), 
        @AttributeOverride(name="account", column=@Column(name="account", nullable=false, length=6,columnDefinition="char") ), 
        @AttributeOverride(name="sub", column=@Column(name="sub", nullable=false, length=1) ), 
        @AttributeOverride(name="program", column=@Column(name="program", nullable=false, length=6,columnDefinition="char") ), 
        @AttributeOverride(name="docNum", column=@Column(name="docNum", nullable=false, length=8,columnDefinition="char") ), 
        @AttributeOverride(name="docRefNum", column=@Column(name="docRefNum", length=10,columnDefinition="char") ), 
        @AttributeOverride(name="budget", column=@Column(name="budget", nullable=false, scale=4) ), 
        @AttributeOverride(name="financial", column=@Column(name="financial", nullable=false, scale=4) ), 
        @AttributeOverride(name="encumbrance", column=@Column(name="encumbrance", nullable=false, scale=4) ), 
        @AttributeOverride(name="fieldIndicator", column=@Column(name="fieldIndicator", nullable=false, length=32) ), 
        @AttributeOverride(name="description", column=@Column(name="description", length=35) ), 
        @AttributeOverride(name="ruleClass", column=@Column(name="ruleClass", nullable=false, length=4,columnDefinition="char") ), 
        @AttributeOverride(name="ledgerIndicator", column=@Column(name="ledgerIndicator", nullable=false, length=1) ), 
        @AttributeOverride(name="transDate", column=@Column(name="transDate", nullable=false, length=0) ), 
        @AttributeOverride(name="createTs", column=@Column(name="create_ts", length=0) ), 
        @AttributeOverride(name="updateTs", column=@Column(name="update_ts", length=0) ) } )
    @NotNull
    public DtlTransId getId() {
        return this.id;
    }
    
    public void setId(DtlTransId id) {
        this.id = id;
    }




}


