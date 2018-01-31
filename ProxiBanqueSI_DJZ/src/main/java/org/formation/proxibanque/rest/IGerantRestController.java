package org.formation.proxibanque.rest;

import java.util.List;

import javax.validation.Valid;

import org.formation.proxibanque.dao.DaoException;
import org.formation.proxibanque.entity.Agence;
import org.formation.proxibanque.entity.Client;
import org.formation.proxibanque.entity.Conseiller;
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
//@PreAuthorize("hasRole('ROLE_GERANT')")
public interface IGerantRestController {
	
	@GetMapping("/conseillers/{id}")
	public ResponseEntity<Conseiller> chercherConseiller(@PathVariable(value = "id") Long idConseiller) throws DaoException;
	
	@PostMapping("/conseillers")
	public ResponseEntity<Conseiller> ajouterConseiller(@Valid @RequestBody Conseiller conseiller) throws DaoException;
	
	@PutMapping("/conseillers")
	public ResponseEntity<Conseiller> modifierConseiller(@Valid @RequestBody Conseiller conseiller) throws DaoException;
	
	@DeleteMapping("/conseillers")
	public ResponseEntity<Conseiller> supprimerConseiller(@Valid @RequestBody Conseiller conseiller) throws DaoException;
	
	@GetMapping("/conseillers_du_gerant/{id}")
	public ResponseEntity<List<Conseiller>> listerConseillersDuGerant(@PathVariable(value = "id") Long idGerent) throws DaoException;

	public boolean faireAudite(Agence a, List<Client> listDebiteurs);
}
