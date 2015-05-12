package edu.ucsd.som.stip.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "AuthRole", catalog = "som_portal")
@XmlRootElement
public class AuthRole implements Serializable {

	private static final long serialVersionUID = 3975546060982736807L;
	
	private String name;
	private String description;
	private List<AuthUser> users;
	
	public AuthRole() {}
	
	public AuthRole(String name) {
		this.name = name;
	}
	
	@Id
	@Column(name = "name", unique = true, nullable = false)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	@XmlTransient
	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	public List<AuthUser> getUsers() {
		return users;
	}

	public void setUsers(List<AuthUser> users) {
		this.users = users;
	}

}
