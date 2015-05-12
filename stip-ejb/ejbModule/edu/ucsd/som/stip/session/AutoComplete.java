package edu.ucsd.som.stip.session;
/**
 * @author         Sharath A.
 */
import java.util.List;

import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import edu.ucsd.som.stip.entity.Db2employee;
import edu.ucsd.som.stip.services.EmployeeRepository;


@Scope(ScopeType.SESSION)
@Name("autoCompleteBean")
public class AutoComplete implements java.io.Serializable {

       private static final long serialVersionUID = 3244108844938966151L;
       @In
       EntityManager entityManager; 
       @SuppressWarnings("unchecked")

       public List<Db2employee> getFullName(Object in) {
              String fullName = in.toString().toUpperCase() + "%";
              return (List<Db2employee>) this.entityManager.createQuery(EmployeeRepository.empAutoComplete).setParameter("name",fullName).setMaxResults(20).getResultList();
       }      
}

