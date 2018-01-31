package org.formation.proxibanque.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Entity CompteCourant herite de Compte (strategy SINGLE TABLE)
 * 
 * @author JW
 *
 */


@Entity
@DiscriminatorValue(value = "COMPTE_COURANT")
public class CompteCourant extends Compte {
	
	public static final double MAXI_DECOUVERT_PARTICULIER = -5000d;
	public static final double MAXI_DECOUVERT_ENTREPRISE = -50000d;
	
	private static final double DEFAULT_DECOUVERT = 1000d;
	
	private double decouvertAuthorise;
	
//	@OneToOne(mappedBy = "compteCourant", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
//	private Carte carte;
	
	public CompteCourant() {
		super();
		this.decouvertAuthorise = DEFAULT_DECOUVERT;
		// Attribuer une carte par defaut
//		this.setCarte(new VisaElectron());
	}
	
//	public Carte getCarte() {
//		return carte;
//	}
//
//	public void setCarte(Carte carte) {
//		carte.setCompteCourant(this);
//		this.carte = carte;
//	}

	
	public double getDecouvertAuthorise() {
		return decouvertAuthorise;
	}

	public void setDecouvertAuthorise(double decouvertAuthorise) {
		this.decouvertAuthorise = decouvertAuthorise;
	}

	@Override
	public String toString() {
		return "CompteCourant [decouvertAuthorise=" + decouvertAuthorise + "]" + super.toString();
	}
}