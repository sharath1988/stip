package edu.ucsd.som.stip.beans;

import edu.ucsd.som.stip.entity.Stipdetail;
import edu.ucsd.som.stip.entity.Stipdetailsingle;


public class MissingIndices  implements java.io.Serializable {

	private static final long serialVersionUID = 3356177459039541968L;
	private String indx;
     private String fund;
     private String org;
     private String dept;
     private String division;
     private Double code;
     private String stipfund;
     private String idxAdd;
     private String oldIdx;
     private boolean update;

    public MissingIndices() {
    }

    public MissingIndices(String indx,String fund, String org, String dept, String division, Double code,String stipfund, String idxAdd) {
    	this.indx=indx;
       this.fund = fund;
       this.org = org;
       this.dept = dept;
       this.division = division;
       this.code = code;
       this.idxAdd = idxAdd;
       this.stipfund=stipfund;
    }

	public MissingIndices(Stipdetail missingIndex,String missInact) {
		if(missInact.equals("missing"))
		{
		   this.indx=missingIndex.getIndx();
	       this.fund = missingIndex.getPfFund();
	       this.org = missingIndex.getPoOrganization();
	       this.dept = missingIndex.getMajorGroup();
	       this.division = missingIndex.getDepartment();
	       this.code = missingIndex.getCode();
	       this.idxAdd = missingIndex.getInde();
	       this.stipfund=missingIndex.getPfFund();
	       this.oldIdx=idxAdd;
		}
		else
		{
			   this.indx=missingIndex.getIndx();
		       this.fund = missingIndex.getPfFund();
		       this.org = missingIndex.getOrg();
		       this.dept = missingIndex.getDept();
		       this.division = missingIndex.getDivision();
		       this.code = missingIndex.getCode();
		       this.idxAdd = missingIndex.getInde();
		       this.stipfund=missingIndex.getPfFund();
		       this.oldIdx=idxAdd;
		}
	}

	public MissingIndices(Stipdetailsingle missingIndexSingle,String missInact) {
		if(missInact.equals("missing"))
		{
		   this.indx=missingIndexSingle.getIndx();
	       this.fund = missingIndexSingle.getPfFund();
	       this.org = missingIndexSingle.getPoOrganization();
	       this.dept = missingIndexSingle.getMajorGroup();
	       this.division = missingIndexSingle.getDepartment();
	       this.code = missingIndexSingle.getCode();
	       this.idxAdd = missingIndexSingle.getInde();
		     this.stipfund=missingIndexSingle.getPfFund();
	       this.oldIdx=idxAdd;
		}
		else
		{
			   this.indx=missingIndexSingle.getIndx();
		       this.fund = missingIndexSingle.getPfFund();
		       this.org = missingIndexSingle.getOrg();
		       this.dept = missingIndexSingle.getDept();
		       this.division = missingIndexSingle.getDivision();
		       this.code = missingIndexSingle.getCode();
		       this.idxAdd = missingIndexSingle.getInde();
		       this.stipfund=missingIndexSingle.getPfFund();
		       this.oldIdx=idxAdd;
		}
	}

	public String getIndx() {
		return indx;
	}

	public void setIndx(String indx) {
		this.indx = indx;
	}

	public String getFund() {
		return fund;
	}

	public void setFund(String fund) {
		this.fund = fund;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public Double getCode() {
		return code;
	}

	public void setCode(Double code) {
		this.code = code;
	}

	public String getIdxAdd() {
		return idxAdd;
	}

	public void setIdxAdd(String idxAdd) {
		this.idxAdd = idxAdd;
	}

	public String getStipfund() {
		return stipfund;
	}

	public void setStipfund(String stipfund) {
		this.stipfund = stipfund;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public String getOldIdx() {
		return oldIdx;
	}

	public void setOldIdx(String oldIdx) {
		this.oldIdx = oldIdx;
	}
   
}


