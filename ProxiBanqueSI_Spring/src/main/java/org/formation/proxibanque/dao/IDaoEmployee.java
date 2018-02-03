package org.formation.proxibanque.dao;

import org.formation.proxibanque.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * Interface ext. JapRepository de DAO Employee (Abstract parent de Conseiller et Gerant)
 * 
 * @author JW
 *
 */
public interface IDaoEmployee extends JpaRepository<Employee, Long> {
	
	public Employee findEmployeeByLogin(@Param("login") String login);
}
