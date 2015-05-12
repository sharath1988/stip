package edu.ucsd.som.stip.session;

import edu.ucsd.som.stip.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("somMajorGroupHome")
public class SomMajorGroupHome extends EntityHome<SomMajorGroup>
{


    public void setSomMajorGroupSomMajorGroupId(Integer id)
    {
        setId(id);
    }

    public Integer getSomMajorGroupSomMajorGroupId()
    {
        return (Integer) getId();
    }

    @Override
    protected SomMajorGroup createInstance()
    {
        SomMajorGroup somMajorGroup = new SomMajorGroup();
        return somMajorGroup;
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

    public SomMajorGroup getDefinedInstance()
    {
        return isIdDefined() ? getInstance() : null;
    }


}

