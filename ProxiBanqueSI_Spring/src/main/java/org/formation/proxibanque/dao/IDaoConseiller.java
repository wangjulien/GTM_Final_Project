package org.formation.proxibanque.dao;

import java.util.List;

import org.formation.proxibanque.entity.Conseiller;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface ext. JapRepository de DAO Conseiller
 * 
 * @author JW
 *
 */
public interface IDaoConseiller extends JpaRepository<Conseiller, Long> {


	public List<Conseiller> findByGerantId(Long idGerant);

}
