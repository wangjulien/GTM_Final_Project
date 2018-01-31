package org.formation.proxibanque.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.formation.proxibanque.service.ConstantsConfig;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entity Client
 * 
 * @author JW
 *
 */


//@JsonIgnoreProperties(value = {"conseiller"}, allowSetters = true)
@Entity
@Table(name = "client")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true)
	private String refClient;
	
	private String nom;
	private String prenom;

	@Embedded
	private Adresse adresse;

	@OneToOne(mappedBy = "client", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	private CompteCourant compteCourant;

	@OneToOne(mappedBy = "client", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	private CompteEpargne compteEpargne;

	@ManyToOne
	private Conseiller conseiller;

	public Client() {
		super();
		adresse = new Adresse();
		
		this.setCompteCourant(new CompteCourant());
		this.setCompteEpargne(new CompteEpargne());
	}

	public Client(String nom, String prenom, String refClient, Adresse adresse) {
		super();
		this.refClient = refClient;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;

		this.setCompteCourant(new CompteCourant());
		this.setCompteEpargne(new CompteEpargne());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRefClient() {
		return refClient;
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
	
	public void setRefClient(String refClient) {
		this.refClient = refClient;
		this.compteCourant.setNumCompte(ConstantsConfig.PREFIX_COMPTE_COURANT + refClient);
		this.compteEpargne.setNumCompte(ConstantsConfig.PREFIX_COMPTE_EPARGNE + refClient);
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Conseiller getConseiller() {
		return conseiller;
	}

	public void setConseiller(Conseiller monConseiller) {
		this.conseiller = monConseiller;
	}

	public CompteCourant getCompteCourant() {
		return compteCourant;
	}

	public CompteEpargne getCompteEpargne() {
		return compteEpargne;
	}

	public void setCompteCourant(CompteCourant compteCourant) {
		compteCourant.setClient(this);
		this.compteCourant = compteCourant;
	}

	public void setCompteEpargne(CompteEpargne compteEpargne) {
		compteEpargne.setClient(this);
		this.compteEpargne = compteEpargne;
	}
}