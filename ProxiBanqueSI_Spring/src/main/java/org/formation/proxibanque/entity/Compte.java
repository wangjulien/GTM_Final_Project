package org.formation.proxibanque.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Entity Compte abstract (strategy SINGLE TABLE)
 * 
 * @author JW
 *
 */


@JsonIgnoreProperties(value = {"client"})
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="type")
@JsonSubTypes({
      @JsonSubTypes.Type(value=CompteCourant.class, name="CompteCourant"),
      @JsonSubTypes.Type(value=CompteEpargne.class, name="CompteEpargne")
  }) 
@Entity
@Table(name = "compte")
@DiscriminatorColumn(name = "compte_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Compte {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String numCompte;
	private double solde;
	
	private boolean etatActif;
	
	@OneToOne
	private Client client;
		
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOuverture;
	
	public Compte() {
		super();
		this.solde = 0;
		this.dateOuverture = new Timestamp(System.currentTimeMillis());
		// tout les comptes cree sont initialement inactifs
		this.etatActif = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumCompte() {
		return numCompte;
	}

	public void setNumCompte(String numCompte) {
		this.numCompte = numCompte;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public Date getDateOuverture() {
		return dateOuverture;
	}

	public void setDateOuverture(Date dateOuverture) {
		this.dateOuverture = dateOuverture;
	}
	
	public boolean isEtatActif() {
		return etatActif;
	}

	public void setEtatActif(boolean etatActif) {
		this.etatActif = etatActif;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}	
}