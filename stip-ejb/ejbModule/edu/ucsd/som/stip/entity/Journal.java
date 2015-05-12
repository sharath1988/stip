package edu.ucsd.som.stip.entity;


import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.validator.Length;

@Entity
@Table(name = "journal", catalog = "stip")
public class Journal implements java.io.Serializable {

	private static final long serialVersionUID = -5580493615166051583L;
	
	private Integer journalId;
	private String docnum;
	private String qrt;
	private Integer createdBy;
	private Date dateCreated;
	private Date dateModified;
	private Integer modifiedBy;
	private Boolean status;
	private Boolean reversed;
	private String type;
	
	private Boolean edit;

	public Journal() {
	}

	public Journal(Date dateModified) {
		this.dateModified = dateModified;
	}

	public Journal(String docnum, String qrt, Integer createdBy,
			Date dateCreated, Date dateModified, Integer modifiedBy,
			Boolean status,Boolean reversed,String type) {
		this.docnum = docnum;
		this.qrt = qrt;
		this.createdBy = createdBy;
		this.dateCreated = dateCreated;
		this.dateModified = dateModified;
		this.modifiedBy = modifiedBy;
		this.status = status;
		this.setReversed(reversed);
		this.setType(type);
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "journal_id", unique = true, nullable = false)
	public Integer getJournalId() {
		return this.journalId;
	}

	public void setJournalId(Integer journalId) {
		this.journalId = journalId;
	}

	@Column(name = "docnum", length = 20)
	@Length(max = 20)
	public String getDocnum() {
		return this.docnum;
	}

	public void setDocnum(String docnum) {
		this.docnum = docnum;
	}

	@Column(name = "created_by")
	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_created", length = 19)
	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_modified", length = 19)
	public Date getDateModified() {
		return this.dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	@Column(name = "modified_by")
	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Column(name = "status")
	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}


	@Column(name = "reversed")
	public Boolean getReversed() {
		return reversed;
	}

	public void setReversed(Boolean reversed) {
		this.reversed = reversed;
	}

	@Column(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Transient
	public Boolean getEdit() {
		return edit;
	}

	public void setEdit(Boolean edit) {
		this.edit = edit;
	}
	
	@Column(name = "qrt",length = 8)
	public String getQrt() {
		return qrt;
	}

	public void setQrt(String qrt) {
		this.qrt = qrt;
	}

}
