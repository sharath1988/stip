package edu.ucsd.som.stip.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import edu.ucsd.som.stip.beans.StipDiffIFISGA;

@Name("ifisGAMismatchesPanel")
@Scope(ScopeType.CONVERSATION)
public class IfisGAMismatchesPanel implements Serializable  {

	private static final long serialVersionUID = -3966935573107885878L;
	
	private List<StipDiffIFISGA> stipDiffIFISGAs=new ArrayList<StipDiffIFISGA>();

	public List<StipDiffIFISGA> getStipDiffIFISGAs() {
		return stipDiffIFISGAs;
	}
	public void setStipDiffIFISGAs(List<StipDiffIFISGA> stipDiffIFISGAs) {
		this.stipDiffIFISGAs = stipDiffIFISGAs;
	}
	
}
