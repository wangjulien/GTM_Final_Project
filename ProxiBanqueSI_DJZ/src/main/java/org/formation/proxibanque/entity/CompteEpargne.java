package org.formation.proxibanque.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Entity CompteEpargne herite de Compte (strategy SINGLE TABLE)
 *
 * @author JW
 *
 */


@Entity
@DiscriminatorValue(value = "COMPTE_EPARGNE")
public class CompteEpargne extends Compte {

	private double tauxInteret;
	
	public CompteEpargne() {
		super();
		this.tauxInteret = 0.03d;
	}
		
	public void setTauxInteret(double tauxInteret) {
		this.tauxInteret = tauxInteret;
	}

	public double getTauxInteret() {
		return tauxInteret;
	}

	@Override
	public String toString() {
		return "CompteEpargne [tauxInteret=" + tauxInteret + "]" + super.toString();
	}
}