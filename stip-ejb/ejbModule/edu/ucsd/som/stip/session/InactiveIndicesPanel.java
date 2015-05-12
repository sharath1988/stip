package edu.ucsd.som.stip.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import edu.ucsd.som.stip.beans.MissingIndices;

@Name("inactiveIndicesPanel")
@Scope(ScopeType.CONVERSATION)
public class InactiveIndicesPanel implements Serializable  {

	private static final long serialVersionUID = 7304595469974514902L;
	
	
	private List<MissingIndices> inactiveIndices=new ArrayList<MissingIndices>();
	
	public List<MissingIndices> getInactiveIndices() {
		return inactiveIndices;
	}
	public void setInactiveIndices(List<MissingIndices> inactiveIndices) {
		this.inactiveIndices = inactiveIndices;
	}
	
	
}
