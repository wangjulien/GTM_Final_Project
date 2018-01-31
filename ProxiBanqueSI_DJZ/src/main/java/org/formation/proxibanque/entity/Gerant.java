package org.formation.proxibanque.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entity Gerant herite de Employee (strategy TABLE PER CLASS)
 * 
 * @author JW
 *
 */


@JsonIgnoreProperties(value = {"agence", "conseillerList"})
@Entity
@Table(name = "gerant")
public class Gerant extends Employee {
	
	public static final String ROLE_GERANT = "ROLE_GERANT";
	
	@OneToOne
	private Agence agence;

	@OneToMany(mappedBy = "gerant", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Conseiller> conseillerList = new ArrayList<>();

	public Gerant() {
		super();
		super.addRole(new UserRole(ROLE_GERANT));
	}

	public Gerant(String nom, String prenom, String refEmployee, Adresse adresse) {
		super(nom, prenom, refEmployee, adresse);
		super.addRole(new UserRole(ROLE_GERANT));
	}

	public Agence getAgence() {
		return agence;
	}

	public void setAgence(Agence agence) {
		this.agence = agence;
	}

	public List<Conseiller> getConseillerList() {
		return conseillerList;
	}

	public void setConseillerList(List<Conseiller> conseillerList) {
		this.conseillerList = conseillerList;
	}

	public void addConseiller(Conseiller c) {
		c.setGerant(this);
		conseillerList.add(c);
	}

	public void deleteConseiller(Conseiller c) {
		c.setGerant(null);
		conseillerList.remove(c);
	}
}