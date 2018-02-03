package org.formation.proxibanque.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity Virement qui serve comme une historique des virements effectues
 * 
 * @author JW
 *
 */


@Entity
@NamedQueries({ @NamedQuery(name = "findAllVirements", query = "select m from Virement m"),
		@NamedQuery(name = "findVirementByClientId", query = "select m from Virement m join m.clientDebiteur c where c.id = :clientid") })

@Table(name = "virement")
public class Virement {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOperation;

	@ManyToOne
	@JoinColumn(name = "id_debiteur")
	private Client clientDebiteur;

	@OneToOne
	@JoinColumn(name = "id_crediteur")
	private Client clientCrediteur;

	@OneToOne
	@JoinColumn(name = "id_depart")
	private Compte depart;

	@OneToOne
	@JoinColumn(name = "id_cible")
	private Compte cible;

	private double montant;

	public Virement() {
		this.dateOperation = new Timestamp(System.currentTimeMillis());
	}

	public Virement(Client clientDebiteur, Compte depart, Client clientCrediteur, Compte cible, double montant) {
		super();
		this.dateOperation = new Timestamp(System.currentTimeMillis());

		this.clientDebiteur = clientDebiteur;
		this.clientCrediteur = clientCrediteur;
		this.depart = depart;
		this.cible = cible;
		this.montant = montant;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateOperation() {
		return dateOperation;
	}

	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}

	public Client getClientDebiteur() {
		return clientDebiteur;
	}

	public void setClientDebiteur(Client clientDebiteur) {
		this.clientDebiteur = clientDebiteur;
	}

	public Client getClientCrediteur() {
		return clientCrediteur;
	}

	public void setClientCrediteur(Client clientCrediteur) {
		this.clientCrediteur = clientCrediteur;
	}

	public Compte getDepart() {
		return depart;
	}

	public void setDepart(Compte depart) {
		this.depart = depart;
	}

	public Compte getCible() {
		return cible;
	}

	public void setCible(Compte cible) {
		this.cible = cible;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	@Override
	public String toString() {
		return "Virement [id=" + id + ", dateOperation=" + dateOperation + ", clientDebiteur=" + clientDebiteur.getId()
				+ ", clientCrediteur=" + clientCrediteur.getId() + ", depart=" + depart.getId() + ", cible="
				+ cible.getId() + ", montant=" + montant + "]";
	}

}
