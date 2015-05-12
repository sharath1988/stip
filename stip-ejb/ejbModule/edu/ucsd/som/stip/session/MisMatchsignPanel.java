package edu.ucsd.som.stip.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import edu.ucsd.som.stip.entity.Qrtymd;

@Name("misMatchsignPanel")
@Scope(ScopeType.CONVERSATION)
public class MisMatchsignPanel implements Serializable  {

	private static final long serialVersionUID = -1536314546319675245L;

	
	private List<Qrtymd> signMismatches= new ArrayList<Qrtymd>();
	
	public List<Qrtymd> getSignMismatches() {
		return signMismatches;
	}
	public void setSignMismatches(List<Qrtymd> signMismatches) {
		this.signMismatches = signMismatches;
	}
}
