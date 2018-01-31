package org.formation.proxibanque.security;

import org.formation.proxibanque.dao.IDaoEmployee;
import org.formation.proxibanque.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe implements UserDetailsService (Spring Security) pour offrir se logger a partir user dans DB
 * 
 * @author JW
 *
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

	private static final Logger LOGGER =  LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	
	@Autowired
    private IDaoEmployee daoEmployee;
	
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    	
    	Employee user = daoEmployee.findEmployeeByLogin(login);
    	
    	LOGGER.info("Utilisateur login ", login);
		if(user == null){
			LOGGER.error("L'utilisateur non trouve");
			throw new UsernameNotFoundException("L'utilisateur non trouve par login " + login);
		}
		
		user.getRoles().size();
		
		LOGGER.info("Utilisateur trouve de DB ", user.getNom() + " " + user.getPrenom());
        return new CustomUserDetails(user);
    }
}
