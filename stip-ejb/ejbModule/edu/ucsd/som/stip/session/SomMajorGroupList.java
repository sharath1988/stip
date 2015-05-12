package edu.ucsd.som.stip.session;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.event.AbortProcessingException;
import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.jboss.seam.log.Log;

import edu.ucsd.som.stip.entity.SomMajorGroup;
import edu.ucsd.som.stip.entity.StipDept;
import edu.ucsd.som.stip.entity.StipDeptCtrl;
import edu.ucsd.som.stip.entity.UcsdIndex;

@Name("somMajorGroupList")
@Scope(ScopeType.CONVERSATION)
public class SomMajorGroupList extends EntityQuery<SomMajorGroup>
{
	private static final long serialVersionUID = -7298851384935366793L;

	@Logger private Log log;
	
	@In
	EntityManager entityManager;
	
	@In(create=true)
	StipDeptHome stipDeptHome;
	
	@In(create=true)
	StipDeptCtrlHome stipDeptCtrlHome;
	
	private String mjrGrpUpdate;
	
	private SomMajorGroup somMajorGroup = new SomMajorGroup();
	
	private StipDept stipDept = new StipDept();
	
		
	private static final String EJBQL = "select somMajorGroup from SomMajorGroup somMajorGroup";

    private static final String[] RESTRICTIONS = {
        "lower(somMajorGroup.name) like lower(concat(#{somMajorGroupList.somMajorGroup.name},'%'))",
    };
	
    private List<String> indices=new ArrayList<String>();
    
    private List<String> deptList= new ArrayList<String>();
    
    private List<String> divisions=new ArrayList<String>();
	
    @SuppressWarnings("unchecked")
	public void init()
    {
    	if(stipDept.getStipdeptId()!=null)
    	stipDeptHome.setInstance(entityManager.find(StipDept.class,stipDept.getStipdeptId()));
    	else
    	deptList=(List<String>)entityManager.createQuery("select distinct d.dept from StipDept d where d.dept is not null and d.dept not like '' ").getResultList();
    	stipDeptHome.getInstance().setStatus(true);
    }
    
   	public void initCtrl()
       {
       	if(stipDept.getStipdeptId()!=null)
       			stipDeptCtrlHome.setInstance(entityManager.find(StipDeptCtrl.class,stipDept.getStipdeptId()));
       	else
       		stipDeptCtrlHome.getInstance().setDept("Som Control");
       		stipDeptCtrlHome.getInstance().setStatus(true);
       }
    
    @SuppressWarnings("unchecked")
	public List<String> getAllIndices(Object in) {
    	
        String idx = in.toString().toUpperCase() + "%";
		return (List<String>)entityManager.createQuery("select i.id.indx from UcsdIndex i where i.id.indx like :idxName and i.id.status != 'Inactive' ").
										   setParameter("idxName", idx).getResultList();
	}
   	
   	
    @SuppressWarnings("unchecked")
	public void  loadIndices()
    {
    	if(this.stipDept.getDept()!=null)
    	{
    		
    		int smgId=(Integer) entityManager.createNativeQuery("select distinct smgId from som_rollup where smgName=:smgName")
    										 .setParameter("smgName",this.stipDept.getDept()).getSingleResult();
    		this.indices=(List<String>)entityManager.createQuery("select distinct u.id.indx from UcsdIndex u where u.id.somMajorGroup=:somMajorGroup and u.id.status != 'Inactive' order by u.id.indx").
													 setParameter("somMajorGroup",smgId).
													 getResultList();
    		this.divisions=(List<String>)entityManager.createNativeQuery("select distinct divName from som_rollup where smgId=:smgId and divName is not null and divName <> '' ").
													   setParameter("smgId",smgId).
													   getResultList();
    		this.stipDeptHome.getInstance().setDept(this.stipDept.getDept());
    	}
    }

    @SuppressWarnings("unchecked")
	public List<String> getIndices() {
    	if(this.stipDeptHome.getInstance().getDept()!=null)
    	{
    		
    		int smgId=(Integer) entityManager.createNativeQuery("select distinct smgId from som_rollup where smgName=:smgName")
    										 .setParameter("smgName",stipDeptHome.getInstance().getDept() ).getSingleResult();
    		this.indices=(List<String>)entityManager.createQuery("select distinct u.id.indx from UcsdIndex u where u.id.somMajorGroup=:somMajorGroup and u.id.status != 'Inactive' order by u.id.indx").
													 setParameter("somMajorGroup",smgId).
													 getResultList();
    	}
		return indices;
	}


	public SomMajorGroupList()
    {        
    	setEjbql(EJBQL);        
        setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
    }

    public SomMajorGroup getSomMajorGroup()
    {
        return somMajorGroup;
    }
        
    public List<String> getAllMajorGroups(){    	
    	List<SomMajorGroup> majorGroups = getResultList();
    	List<String> majorGroupStrings = new ArrayList<String>();
    	for(SomMajorGroup mg: majorGroups) {
    		majorGroupStrings.add(mg.getName());      		
    	}
    	//This is added explicitly since it cannot be found in any Table.    	
    	majorGroupStrings.add("SOM Control");
    	return majorGroupStrings;
    }    
    
        
		
	
	public void populateOrgsAndFunds()
			throws AbortProcessingException  {
		
		UcsdIndex index=(UcsdIndex) entityManager.
											createQuery("from UcsdIndex u where u.id.indx=:indx").
				                            setParameter("indx", this.stipDeptHome.getInstance().getIndx()).getSingleResult();
		this.stipDeptHome.getInstance().setOrg(index.getId().getOrganization());
		this.stipDeptHome.getInstance().setFund(index.getId().getFund());
		
		if(index.getId().getFund().startsWith("76"))
		this.stipDeptHome.getInstance().setAcct("723050");
		else
		this.stipDeptHome.getInstance().setAcct("720703");
		
	}
	
	public void populateCtrlOrgsAndFunds()
			throws AbortProcessingException  {
		
		UcsdIndex index=(UcsdIndex) entityManager.
											createQuery("from UcsdIndex u where u.id.indx=:indx").
				                            setParameter("indx", this.stipDeptCtrlHome.getInstance().getIndx()).getSingleResult();
		this.stipDeptCtrlHome.getInstance().setOrg(index.getId().getOrganization());
		this.stipDeptCtrlHome.getInstance().setFund(index.getId().getFund());
		if(index.getId().getFund().startsWith("76"))
		this.stipDeptCtrlHome.getInstance().setAcct("723050");
			else
		this.stipDeptCtrlHome.getInstance().setAcct("720703");
		
	}
	
		
	

	public StipDeptHome getStipDeptHome() {
		return stipDeptHome;
	}

	public void setStipDeptHome(StipDeptHome stipDeptHome) {
		this.stipDeptHome = stipDeptHome;
	}

	public void setIndices(List<String> indices) {
		this.indices = indices;
	}

	public StipDept getStipDept() {
		return stipDept;
	}

	public void setStipDept(StipDept stipDept) {
		this.stipDept = stipDept;
	}

	public List<String> getDeptList() {
		return deptList;
	}

	public void setDeptList(List<String> deptList) {
		this.deptList = deptList;
	}

	public List<String> getDivisions() {
		return divisions;
	}

	public void setDivisions(List<String> divisions) {
		this.divisions = divisions;
	}

	public StipDeptCtrlHome getStipDeptCtrlHome() {
		return stipDeptCtrlHome;
	}

	public void setStipDeptCtrlHome(StipDeptCtrlHome stipDeptCtrlHome) {
		this.stipDeptCtrlHome = stipDeptCtrlHome;
	}
	
}
