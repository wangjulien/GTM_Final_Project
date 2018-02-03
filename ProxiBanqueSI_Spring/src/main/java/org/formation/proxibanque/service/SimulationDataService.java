package org.formation.proxibanque.service;

import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import org.formation.proxibanque.dao.IDaoAgence;
import org.formation.proxibanque.entity.Adresse;
import org.formation.proxibanque.entity.Agence;
import org.formation.proxibanque.entity.Client;
import org.formation.proxibanque.entity.Conseiller;
import org.formation.proxibanque.entity.Gerant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Classe (Spring Bean) pour charger les donnees simulation au demarrage
 * 
 * 
 * @author JW
 *
 */

@Service
public class SimulationDataService {
	
	private static final Logger LOGGER =  LoggerFactory.getLogger(SimulationDataService.class);
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	@Autowired
	private IDaoAgence daoAgence;
	

	public SimulationDataService() {
		super();
	}

	@PostConstruct
	public void createSomeClient() {

		Adresse add = new Adresse("Paris", 75001, "IdF", "0123456789");

		Client client01 = new Client("Dupont", "Julien", "CL001",
				new Adresse("Palaiseau", 91120, "IdF", "0123456700"));
		client01.getCompteCourant().setEtatActif(true);
		client01.getCompteCourant().setNumCompte("00001");
		client01.getCompteCourant().setSolde(1200.44);
		client01.getCompteCourant().setDecouvertAuthorise(1000.00);
		
		client01.getCompteEpargne().setEtatActif(true);
		client01.getCompteEpargne().setNumCompte("10001");
		client01.getCompteEpargne().setSolde(220000.00);
		client01.getCompteEpargne().setTauxInteret(0.03);
		
		Client client02 = new Client("Leniaud", "Michelle", "CL002",
				new Adresse("Palaiseau", 91120, "IdF", "0123450067"));
		client02.getCompteCourant().setEtatActif(true);
		client02.getCompteCourant().setNumCompte("00002");
		client02.getCompteCourant().setSolde(200.05);
		client02.getCompteCourant().setDecouvertAuthorise(1000.00);
		
		Client client03 = new Client("Durant", "Albert", "CL003",
				new Adresse("Palaiseau", 91120, "IdF", "0120034567"));
		client03.getCompteEpargne().setEtatActif(true);
		client03.getCompteEpargne().setNumCompte("10003");
		client03.getCompteEpargne().setSolde(500000.00);
		client03.getCompteEpargne().setTauxInteret(0.03);
		
		Client client04 = new Client("Lewis", "Karl", "CL004",
				new Adresse("Palaiseau", 91120, "IdF", "0123004567"));
		client04.getCompteCourant().setEtatActif(true);
		client04.getCompteCourant().setNumCompte("00004");
		client04.getCompteCourant().setSolde(-420.44);
		client04.getCompteCourant().setDecouvertAuthorise(1000.00);
		
		client04.getCompteEpargne().setEtatActif(true);
		client04.getCompteEpargne().setNumCompte("10004");
		client04.getCompteEpargne().setSolde(3001.00);
		client04.getCompteEpargne().setTauxInteret(0.03);
		
		Client client05 = new Client("Duval", "Jean", "CL005",
				new Adresse("Palaiseau", 91120, "IdF", "0123456007"));

		Client enterprise1 = new Client("Darty", "EJ001883", "CL006", add);
		enterprise1.getCompteCourant().setEtatActif(true);
		enterprise1.getCompteCourant().setNumCompte("CC00001E");
		enterprise1.getCompteCourant().setSolde(123000);
		enterprise1.getCompteCourant().setDecouvertAuthorise(10000.00);
		
		Client enterprise2 = new Client("Carrefour", "EJ001223", "CL007", add);
		enterprise2.getCompteCourant().setEtatActif(true);
		enterprise2.getCompteCourant().setNumCompte("CC00002E");
		enterprise2.getCompteCourant().setSolde(918787.12);
		enterprise2.getCompteCourant().setDecouvertAuthorise(90000.00);
		
		
		Client enterprise3 = new Client("Auchan", "E001234", "CL008", add);
		enterprise3.getCompteCourant().setEtatActif(true);
		enterprise3.getCompteCourant().setNumCompte("CC00003E");
		enterprise3.getCompteCourant().setSolde(20082.23);
		enterprise3.getCompteCourant().setDecouvertAuthorise(40000.00);
		
		Client enterprise4 = new Client("SFR", "EJ9179875", "CL009", add);
		enterprise4.getCompteCourant().setEtatActif(true);
		enterprise4.getCompteCourant().setNumCompte("CC00004E");
		enterprise4.getCompteCourant().setSolde(-2900);
		enterprise4.getCompteCourant().setDecouvertAuthorise(20000.00);
		

		Conseiller leConseiller1 = new Conseiller("David", "JOSHUA", "C001", add);
		leConseiller1.setLogin("david");
		leConseiller1.setPassword(passwordEncoder.encode("test"));
		
		Conseiller leConseiller2 = new Conseiller("Marc", "MARIN", "C002", add);
		leConseiller2.setLogin("marc");
		leConseiller2.setPassword(passwordEncoder.encode("test"));
		
		Conseiller leConseiller3 = new Conseiller("Zlatka", "GRAS", "C003", add);
		leConseiller3.setLogin("zlatka");
		leConseiller3.setPassword(passwordEncoder.encode("test"));
		
		leConseiller1.addClient(client01);
		leConseiller1.addClient(client02);
		leConseiller1.addClient(client03);
		leConseiller1.addClient(client04);
		leConseiller1.addClient(client05);
		leConseiller1.addClient(enterprise1);
		leConseiller1.addClient(enterprise2);
		leConseiller1.addClient(enterprise3);
		leConseiller1.addClient(enterprise4);

		Gerant leGerant = new Gerant("WANG", "Julien", "CG002", add);
		leGerant.setLogin("gerant");
		leGerant.setPassword(passwordEncoder.encode("test"));
		
		
		Agence leAgence = new Agence("0001", LocalDateTime.now().toString(), leGerant);

		leGerant.addConseiller(leConseiller1);
		leGerant.addConseiller(leConseiller2);
		leGerant.addConseiller(leConseiller3);
		leAgence.setGerant(leGerant);

		daoAgence.save(leAgence);
		
		LOGGER.debug("Create simulation data : " + leAgence.toString());
	}

}
