/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.formation.proxibanque.dao;

import org.formation.proxibanque.entity.Gerant;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface ext. JapRepository de DAO  Gerant
 *
 * @author JW
 */

public interface IDaoGerant extends JpaRepository<Gerant, Long>  {

       
}
