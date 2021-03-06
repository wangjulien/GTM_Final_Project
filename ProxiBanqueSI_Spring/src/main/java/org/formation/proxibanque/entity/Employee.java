package org.formation.proxibanque.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * 
 * Entity abstract Employee (strategy JOINED TABLE)
 * 
 * @author JW
 *
 */

@JsonIgnoreProperties(value = {"password"}, allowSetters = true)
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="type")
@JsonSubTypes({
      @JsonSubTypes.Type(value=Conseiller.class, name="Conseiller"),
      @JsonSubTypes.Type(value=Gerant.class, name="Gerant")
  }) 
@Entity
@Table(name = "employee")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	private String refEmployee;

	private String nom;
	private String prenom;

	@Embedded
	private Adresse adresse;

	@ElementCollection(targetClass = Role.class)
	@CollectionTable(name = "role", joinColumns = @JoinColumn(name = "employee_id"))
	@Column(name = "roles", nullable = false)
	@Enumerated(EnumType.STRING)
	private Set<Role> roles = new HashSet<>();

	@Column(unique = true)
	private String login;

	private String password;

	
	public Employee() {
		super();
		this.adresse = new Adresse();
	}

	public Employee(String nom, String prenom, String refEmployee, Adresse adresse) {
		super();
		this.refEmployee = refEmployee;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.login = nom;
		this.password = "test";
	}

	public void addRole(Role role) {
		this.roles.add(role);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRefEmployee() {
		return refEmployee;
	}

	public void setRefEmployee(String refEmployee) {
		this.refEmployee = refEmployee;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}