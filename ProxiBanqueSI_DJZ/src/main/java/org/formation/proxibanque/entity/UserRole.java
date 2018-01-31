package org.formation.proxibanque.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entity de Role, utilse pour Employee(Gerant ou Conseiller) pour avoir different previliges
 * 
 * @author JW
 *
 */
@Entity
@JsonIgnoreProperties(value = {"users"})
@Table(name = "userrole")
public class UserRole {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    private String name;
    
    @ManyToMany(mappedBy = "roles")
    private Set<Employee> users = new HashSet<>();
	
	public UserRole() {
		super();
	}

	public UserRole(String role) {
		this.name = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Employee> getUsers() {
		return users;
	}

	public void setUsers(Set<Employee> users) {
		this.users = users;
	}

	public void addUser(Employee e) {
		this.users.add(e);
		
	}	
}
