package org.formation.proxibanque.rest;

import java.util.List;

import javax.validation.Valid;

import org.formation.proxibanque.dao.DaoException;
import org.formation.proxibanque.dao.IDaoEmployee;
import org.formation.proxibanque.entity.Client;
import org.formation.proxibanque.entity.Employee;
import org.formation.proxibanque.service.IConseillerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
public class ConseillerRestController implements IConseillerRestController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConseillerRestController.class);

	@Autowired
	private IConseillerService conseillerService;

	public ConseillerRestController() {
		super();
	}
	
	
	@Autowired
    private IDaoEmployee daoEmployee;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@PostMapping("/auth")
	public ResponseEntity<Employee> login(@Valid @RequestBody Employee user) throws DaoException {
		
		LOGGER.info("Utilisateur : " + user.getLogin() + " essaye de logger " + user.getPassword() + "\n"
				+ passwordEncoder.encode(user.getPassword()) ) ;
		
		Employee foundUser = daoEmployee.findEmployeeByLogin(user.getLogin());
		if ( null != foundUser ) {
			
			LOGGER.info("Utilisateur trouve : " + foundUser.getLogin() + " " + foundUser.getPassword());
			
			if ( passwordEncoder.matches( user.getPassword(), foundUser.getPassword() ) 
					|| foundUser.getPassword().equals(user.getPassword()) ) {
				foundUser.setToken("Fake token");
				
				LOGGER.info("Logger reussi : " + foundUser.getNom() + " " + foundUser.getPrenom());
				
				return ResponseEntity.ok(foundUser);
			} else
				throw new DaoException("Mot de passe incorrect");		
			
		} else {
			throw new DaoException("Client avec login non trouve");
		}
	}
	
	@Override
	public ResponseEntity<Client> chercherClient(@PathVariable(value = "id") Long clientId) throws DaoException {
		try {
			LOGGER.info("Chercher client id : " + clientId);
			
			Client foundClient = conseillerService.chercherClient(clientId);
			if (null == foundClient) {
				LOGGER.error("Client avec id : " + clientId + " non trouve");
				throw new DaoException("Client avec id : " + clientId + " non trouve");
			}
			
			LOGGER.info("Client trouve : " + foundClient.getNom() + " " + foundClient.getPrenom());
			
			return ResponseEntity.ok(foundClient);

		} catch (DaoException e) {			
			throw e;
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
	public ResponseEntity<Client> ajouterClient(@Valid @RequestBody Client client) throws DaoException {
		try {
			LOGGER.info("Client a ajoute : Id=" + client.getId() + " " + client.getNom() + " " + client.getPrenom());
			
			conseillerService.ajouterClient(client);
			
			LOGGER.info("Client ajoute : Id=" + client.getId() + " " + client.getNom() + " " + client.getPrenom());
			
			return ResponseEntity.ok(client);
			
		} catch (DaoException e) {
			throw e;
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
	public ResponseEntity<Client> modifierClient(@Valid @RequestBody Client client) throws DaoException {
		try {
			LOGGER.info("Client a modifie : Id=" + client.getId() + " " + client.getNom() + " " + client.getPrenom());
						
			conseillerService.modifierClient(client);
			
			LOGGER.info("Client modifie : Id=" + client.getId() + " " + client.getNom() + " " + client.getPrenom());
			
			return ResponseEntity.ok(client);
		} catch (Exception e) {
			throw e;
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
	public ResponseEntity<Client> supprimerClient(@PathVariable(value = "id") Long clientId) throws DaoException {
		try {
			
			Client foundClient = conseillerService.chercherClient(clientId);
			if (null == foundClient) {
				LOGGER.error("Client avec id : " + clientId + " non trouve");
				throw new DaoException("Client avec id : " + clientId + " non trouve");
			}
			
			conseillerService.supprimerClient(foundClient);
			
			LOGGER.info("Client supprime : Id=" + foundClient.getId() + " " + foundClient.getNom() + " " + foundClient.getPrenom());
			
			return ResponseEntity.ok(foundClient);
		} catch (DaoException e) {
			throw e;
		}
	}

	/**
	 * Recupere tous les client de la persistence
	 * 
	 * @return : une liste de client
	 * @throws DaoException
	 *             DaoException
	 */
	public ResponseEntity<List<Client>> listerTousClients() throws DaoException {
		try {
			List<Client> clis = conseillerService.listerTousClients();
			
			LOGGER.info("Clients trouves : nombre=" + clis.size());
			
			return ResponseEntity.ok(clis);
		} catch (DaoException e) {
			throw e;
		}
	}

	/**
	 * Recupere tous les client de la persistence
	 * 
	 * @return : une liste de client
	 * @throws DaoException
	 *             DaoException
	 */
	public ResponseEntity<List<Client>> listerClientsDeConseiller(@PathVariable(value = "id") Long idConseiller) throws DaoException {
		try {
			List<Client> clis = conseillerService.listerClientsDeConseiller(idConseiller);
			
			LOGGER.info("Clients du conseiller id=" + idConseiller + " trouves : nombre=" + clis.size());
			
			return ResponseEntity.ok(clis);
		} catch (DaoException e) {
			throw e;
		}
	}
}
