package org.formation.proxibanque.rest;

import java.util.List;

import javax.validation.Valid;

import org.formation.proxibanque.dao.DaoException;
import org.formation.proxibanque.entity.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proxi_banque")
//@PreAuthorize("hasRole('ROLE_CONSEILLER')")
public interface IConseillerRestController {
	
	@GetMapping("/clients/{id}")
	public ResponseEntity<Client> chercherClient(@PathVariable(value = "id") Long clientId) throws DaoException;
	
	@PostMapping("/clients")
	public ResponseEntity<Client> ajouterClient(@Valid @RequestBody Client client) throws DaoException;
	
	@PutMapping("/clients")
	public ResponseEntity<Client> modifierClient(@Valid @RequestBody Client client) throws DaoException;
	
	@DeleteMapping("/clients/{id}")
	public ResponseEntity<Client> supprimerClient(@PathVariable(value = "id") Long clientId) throws DaoException;
	
	@GetMapping("/clients")
	public ResponseEntity<List<Client>> listerTousClients() throws DaoException;
	
	@GetMapping("/clients_du_conseiller/{id}")
	public ResponseEntity<List<Client>> listerClientsDeConseiller(@PathVariable(value = "id") Long idConseiller) throws DaoException;
}
