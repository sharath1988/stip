package edu.ucsd.som.stip.session;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

import edu.ucsd.som.stip.entity.StipDept;

@Name("stipDeptHome")
public class StipDeptHome extends EntityHome<StipDept>
{	
	private static final long serialVersionUID = 3117310137316064024L;

	@In private BadgIdentity identity;	
	
	//@Logger private Log log;
	
	public void setStipDeptId(Integer id)
    {
        setId(id);
    }

    public Integer getStipDeptId()
    {
        return (Integer) getId();
    }

    @Override
    protected StipDept createInstance()    
    {    	    	
    	StipDept stipDept = new StipDept();        
        return stipDept;
    }
        
    @Override
    public String persist(){
    	this.instance.setDateCreated(new Date());    	
    	this.instance.setCreatedBy(identity.getAccountId());  
    	
    	/*if(this.instance.getStipFund()!=null && !this.instance.getStipFund().isEmpty() && this.instance.getStipFund().startsWith("76") && !this.instance.getIndx().equals("SOM8864"))
		{
    		this.instance.setAcct("723050");
		}
		else
			this.instance.setAcct("720703");*/
    	
    	if(this.instance.getIndx().length()==7 && this.instance.getOrg()!=null && this.instance.getFund()!=null)
    	{
    		
    		return super.persist();
    	}
    	else 
    	{
    		FacesContext.getCurrentInstance().addMessage("persist", 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Index not valid.Plese try again", null));
    		 return null;
    	}
    }

	@Override
	public String update(){		
		this.instance.setDateModified(new Date());
		this.instance.setModifiedBy(identity.getAccountId());
		if(this.instance.getDivision().isEmpty())
			this.instance.setDivision(null);
		return super.update();
	}
    
    
    public void load()
    {
        if (isIdDefined())
        {
            wire();
        }
    }

    public void wire()
    {
        getInstance();
    }

    public boolean isWired()
    {
        return true;
    }

    public StipDept getDefinedInstance()
    {
        return isIdDefined() ? getInstance() : null;
    }
        
}

