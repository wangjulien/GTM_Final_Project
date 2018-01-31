package org.formation.proxibanque.dao;

import org.formation.proxibanque.entity.Agence;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface ext. JapRepository de DAO d'une Agence
 *
 * @author JW
 */

public interface IDaoAgence extends JpaRepository<Agence, Long> {

       
}
