package org.formation.proxibanque.service;

import java.util.List;

import org.formation.proxibanque.dao.DaoException;
import org.formation.proxibanque.dao.IDaoClient;
import org.formation.proxibanque.entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Classe qui regroupe tous les traitements concernant un Conseiller courrant. -
 * Ajouter un Client - Recuperer un Client par son ID, lire toutes ces
 * informations (data) - Modifier un Client - Supprimer un Client - Lister tous
 * les Client dans persistence - ToDo : simulationCredit et gestionPatrimoine
 * 
 * DaoClient est utilise ici pour Chercher ou Modifier l'information dans
 * persistance *
 * 
 * @author JW
 *
 */

@Service
public class ConseillerService implements IConseillerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConseillerService.class);

	@Autowired
	private IDaoClient daoClient;

	public ConseillerService() {
		super();
	}

	@Override
	public Client chercherClient(@PathVariable(value = "id") Long clientId) throws DaoException {
		try {

			return daoClient.findOne(clientId);

		} catch (Exception e) {
			String msg = "chercherClient" + e.getMessage();
			LOGGER.error(msg);
			
			throw new DaoException("chercherClient", e);
		}
	}

	/**
	 * Ajout d'un client dans la persistence
	 * 
	 * @param client
	 *            : client a ajouter
	 * @throws DaoException
	 *             DaoException
	 */
	public void ajouterClient(Client client) throws DaoException {
		try {
			daoClient.save(client);

			// Strategy local de generer reference client automatique
			if (client.getRefClient().isEmpty())
				client.setRefClient(ConstantsConfig.PREFIX_CLI_REF + client.getId());

			daoClient.save(client);
		} catch (Exception e) {
			throw new DaoException("ConseillerService.ajouterClient " + e);
		}
	}

	/**
	 * Mettre a jour un client dans la persistence
	 * 
	 * @param client
	 *            : le client modifie
	 * @throws DaoException
	 *             DaoException
	 */
	public void modifierClient(Client client) throws DaoException {
		try {
			daoClient.save(client);
		} catch (Exception e) {
			throw new DaoException("ConseillerService.modifierClient" + e);
		}
	}

	/**
	 * Suppression d'un client donne dans persistence
	 * 
	 * @param client
	 *            : le client a supprimer
	 * @throws DaoException
	 *             DaoException
	 */
	public void supprimerClient(Client client) throws DaoException {
		try {
			daoClient.delete(client);
		} catch (Exception e) {
			throw new DaoException("ConseillerService.supprimerClient" + e);
		}
	}

	/**
	 * Recupere tous les client de la persistence
	 * 
	 * @return : une liste de client
	 * @throws DaoException
	 *             DaoException
	 */
	public List<Client> listerTousClients() throws DaoException {
		try {
			return daoClient.findAll();
		} catch (Exception e) {
			throw new DaoException("ConseillerService.listerTousClients" + e);
		}
	}

	/**
	 * Recupere tous les client de la persistence
	 * 
	 * @return : une liste de client
	 * @throws DaoException
	 *             DaoException
	 */
	public List<Client> listerClientsDeConseiller(Long idConseiller) throws DaoException {
		try {
			return daoClient.findByConseillerId(idConseiller);
		} catch (Exception e) {
			throw new DaoException("ConseillerService.listerClientsDeConseiller" + e);
		}
	}
}
