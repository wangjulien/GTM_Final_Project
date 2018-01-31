package org.formation.proxibanque.service;

import org.formation.proxibanque.dao.DaoException;
import org.formation.proxibanque.dao.IDaoClient;
import org.formation.proxibanque.dao.IDaoVirement;
import org.formation.proxibanque.entity.Client;
import org.formation.proxibanque.entity.Compte;
import org.formation.proxibanque.entity.CompteCourant;
import org.formation.proxibanque.entity.Virement;
import org.formation.proxibanque.rest.VirementRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service realisant des Virements d'un Compte a un autre
 * 
 * @author JW
 *
 */

@Service
public class VirementService implements IVirementService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VirementService.class);
	
	@Autowired
	private IDaoClient daoClient;

	@Autowired
	private IDaoVirement daoVirement;

	public VirementService() {
		super();
	}

	/**
	 * Verifie que le montant ne depasse pas le solde du compte
	 * 
	 * @param depart
	 *            le compte a verifier
	 * @param montant
	 *            le montant a virer
	 * @return true si le solde est suffisant
	 */

	private boolean checkMontantSolde(Compte depart, Compte cible, double montant) {
		if (depart.getId() == cible.getId()) {
			LOGGER.error("Virement sur un meme compte interdit : " + depart.getId());
			return false;
		}else if (montant < 0) {
			LOGGER.error("Montant negatif : " + montant);
			return false;
		}
		else if ((depart.getSolde() >= montant) || (depart instanceof CompteCourant
				&& ((CompteCourant) depart).getDecouvertAuthorise() + depart.getSolde() >= montant))
			return true;
		else {
			LOGGER.error("Solde insuffisant : " + depart.getSolde() );
			return false;
		}
	}
	
	@Override
	@Transactional
	public boolean faireVirement(Virement virement)
			throws DaoException {
		
		Client debiteur = virement.getClientDebiteur();
		Client crediteur = virement.getClientCrediteur();
		
		if ( virement.getDepart().getId() == debiteur.getCompteCourant().getId() )
			virement.setDepart(debiteur.getCompteCourant());
		else
			virement.setDepart(debiteur.getCompteEpargne());
		
		if ( virement.getCible().getId() == crediteur.getCompteCourant().getId() )
			virement.setCible(crediteur.getCompteCourant());
		else
			virement.setCible(crediteur.getCompteEpargne());
		
		
		LOGGER.info("Client (" + debiteur.getNom() + " " + debiteur.getPrenom() + ") " + " a effectue un virement : \n "
				+ "compte depart : " + virement.getDepart().getNumCompte()
				+ "\nbeneficiaire : " + crediteur.getNom() + " " + crediteur.getPrenom() 
				+ " compte cible : " + virement.getCible().getNumCompte()
				+ "\npour un montant : " + virement.getMontant());
		
		if (checkMontantSolde(virement.getDepart(), virement.getCible(), virement.getMontant())) {

			virement.getDepart().setSolde(virement.getDepart().getSolde() - virement.getMontant());
			virement.getCible().setSolde(virement.getCible().getSolde() + virement.getMontant());

			// Insertion de virement dans table
			daoClient.save(debiteur);
			daoClient.save(crediteur);
			daoVirement.save(virement);
			

			return true;
		} else
			return false;
	}

}