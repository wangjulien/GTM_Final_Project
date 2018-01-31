package org.formation.proxibanque.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.formation.proxibanque.entity.Employee;
import org.formation.proxibanque.entity.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * Classe implement interface UserDetail (Spring Security) et encapsule l'utilisateur logge (Employee)
 * pour acceder les attributs ID, NOM, PRENOM etc
 * 
 * @author JW
 *
 */
public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	private final Employee user;

	public CustomUserDetails(Employee user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (UserRole userRole : user.getRoles()) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getName()));
		}

		return new ArrayList<GrantedAuthority>(setAuths);
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getUsername() {
		return user.getLogin();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	public Employee getUser() {
		return user;
	}
}
