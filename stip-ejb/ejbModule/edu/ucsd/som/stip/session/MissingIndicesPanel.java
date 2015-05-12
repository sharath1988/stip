package edu.ucsd.som.stip.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import edu.ucsd.som.stip.beans.MissingIndices;

@Name("missingIndicesPanel")
@Scope(ScopeType.CONVERSATION)
public class MissingIndicesPanel implements Serializable  {

	private static final long serialVersionUID = 6609712756688212775L;

	
	private List<MissingIndices> missIndices=new ArrayList<MissingIndices>();
	
	public List<MissingIndices> getMissIndices() {
		return missIndices;
	}
	public void setMissIndices(List<MissingIndices> missIndices) {
		this.missIndices = missIndices;
	}
}
