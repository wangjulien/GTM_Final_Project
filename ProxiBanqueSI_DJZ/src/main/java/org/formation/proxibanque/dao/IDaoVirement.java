package org.formation.proxibanque.dao;

import org.formation.proxibanque.entity.Virement;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de persister virement
 * 
 * @author JW
 *
 */
public interface IDaoVirement extends JpaRepository<Virement, Long> {
	

//	public List<Virement> findVirementByClientId(Long idClient);
}
