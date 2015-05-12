package edu.ucsd.som.stip.session;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

import edu.ucsd.som.stip.entity.Journal;

@Name("journalHome")
public class JournalHome extends EntityHome<Journal> {

	private static final long serialVersionUID = 8717702960808449276L;

	public void setJournalJournalId(Integer id) {
		setId(id);
	}

	public Integer getJournalJournalId() {
		return (Integer) getId();
	}

	@Override
	protected Journal createInstance() {
		Journal journal = new Journal();
		return journal;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public Journal getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
