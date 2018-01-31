package org.formation.proxibanque.service;

import java.util.List;

import org.formation.proxibanque.dao.DaoException;
import org.formation.proxibanque.entity.Client;

public interface IConseillerService {
	
	public Client chercherClient(Long clientId) throws DaoException;
	
	public void ajouterClient(Client client) throws DaoException;

	public void modifierClient(Client client) throws DaoException;
	
	public void supprimerClient(Client client) throws DaoException;

	public List<Client> listerTousClients() throws DaoException;

	public List<Client> listerClientsDeConseiller(Long idConseiller) throws DaoException;
}
