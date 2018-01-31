package org.formation.proxibanque.rest;

import javax.validation.Valid;

import org.formation.proxibanque.dao.DaoException;
import org.formation.proxibanque.entity.Virement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proxi_banque")
//@PreAuthorize("hasRole('ROLE_GERANT')")
public interface IVirementRestController {
	
	
	@PostMapping("/virements")
	public ResponseEntity<Virement> faireVirement(@Valid @RequestBody Virement virement	) throws DaoException;	
	
}
