package org.formation.proxibanque.entity;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entity abstract des cartes banquaire
 * 
 * @author JW
 *
 */


@JsonIgnoreProperties(value = {"compteCourant"})
@Entity
@Table(name = "carte")
@DiscriminatorColumn(name = "carte_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Carte {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String noCarte;
	
	@OneToOne
	private CompteCourant compteCourant;
	
	public Carte() {
		super();
	}

	public Carte(String noCarte) {
		super();
		this.noCarte = noCarte;
	}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNoCarte() {
		return noCarte;
	}
	
	public void setNoCarte(String noCarte) {
		this.noCarte = noCarte;
	}

	public CompteCourant getCompteCourant() {
		return compteCourant;
	}

	public void setCompteCourant(CompteCourant compteCourant) {
		this.compteCourant = compteCourant;
	}

	@Override
	public String toString() {
		return "Carte [noCarte=" + noCarte + "]";
	}
}