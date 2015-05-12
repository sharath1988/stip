package edu.ucsd.som.stip.session;

import java.util.List;

import javax.ejb.Local;

@Local
public interface Impersonate {
    public void impersonate();
    public String getValue();
    public void setValue(String value);
    public List<String> getUsers();
    public void destroy();

    // add additional interface methods here

}
