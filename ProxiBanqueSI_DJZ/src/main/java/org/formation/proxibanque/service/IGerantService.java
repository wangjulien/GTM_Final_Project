package org.formation.proxibanque.service;

import java.util.List;

import org.formation.proxibanque.dao.DaoException;
import org.formation.proxibanque.entity.Agence;
import org.formation.proxibanque.entity.Client;
import org.formation.proxibanque.entity.Conseiller;

public interface IGerantService {
	
	public Conseiller chercherConseiller(Long idConseiller) throws DaoException;
	
	public void ajouterConseiller(Conseiller conseiller) throws DaoException;

	public void modifierConseiller(Conseiller conseiller) throws DaoException;

	public void supprimerConseiller(Conseiller conseiller) throws DaoException;

	public List<Conseiller> listerConseillersDuGerant(Long idGerent) throws DaoException;

	public boolean faireAudite(Agence a, List<Client> listDebiteurs);
}
