package org.formation.proxibanque.security;

import java.util.HashSet;
import java.util.Set;

import org.formation.proxibanque.dao.IDaoEmployee;
import org.formation.proxibanque.entity.Employee;
import org.formation.proxibanque.entity.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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
				
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (Role role : user.getRoles()) {
			setAuths.add(new SimpleGrantedAuthority(role.toString()));
		}
		
		LOGGER.info("Utilisateur trouve de DB ", user.getNom() + " " + user.getPrenom());
        return new User(user.getLogin(), user.getPassword(), setAuths);
    }
}
