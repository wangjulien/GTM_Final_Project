package org.formation.proxibanque.service;

import org.formation.proxibanque.dao.DaoException;
import org.formation.proxibanque.entity.Virement;


public interface IVirementService {

	public boolean faireVirement(Virement virement) throws DaoException;

}
