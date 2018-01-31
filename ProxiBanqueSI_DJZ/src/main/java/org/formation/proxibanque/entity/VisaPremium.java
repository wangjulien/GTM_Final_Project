package org.formation.proxibanque.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * La classe VisaPremium herite de Carte et a un numero de carte.
 * 
 * @author JW
 *
 */


@Entity
@DiscriminatorValue(value = "PREMIUM")
public class VisaPremium extends Carte {
	
		
	public VisaPremium() {
		super();
	}

	public VisaPremium(String noCarte) {
		super(noCarte);
	}
}