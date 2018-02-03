package org.formation.proxibanque.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entity agence
 * 
 * @author JW
 *
 */


@Entity
@Table(name = "agence")
public class Agence {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String reference;
	private String dateCreation;

	@OneToOne(mappedBy = "agence", cascade = { CascadeType.PERSIST, CascadeType.MERGE } )
	private Gerant gerant;
	

	public Agence() {
		super();
		this.setGerant(new Gerant());
	}

	public Agence(String reference, String dateCreation, Gerant gerant) {
		super();
		this.reference = reference;
		this.dateCreation = dateCreation;
		this.setGerant(gerant);
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Gerant getGerant() {
		return gerant;
	}

	public void setGerant(Gerant gerant) {
		gerant.setAgence(this);
		this.gerant = gerant;
	}

}