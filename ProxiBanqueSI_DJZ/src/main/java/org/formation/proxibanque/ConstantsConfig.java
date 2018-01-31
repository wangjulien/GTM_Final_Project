package org.formation.proxibanque;

/**
 * Classs de configuration. a charger depuis un fichier . properties - Pour
 * generer automatiquement prefix
 * 
 * @author JW
 *
 */
public class ConstantsConfig {
	public static final String PREFIX_CLI_REF = "CL00";
	public static final String PREFIX_CONS_REF = "CS00";

	public static final String PREFIX_COMPTE_COURANT = "CC000";
	public static final String PREFIX_COMPTE_EPARGNE = "CE000";
	
	// Security constant

	public static final String SECRET = "SecretKeyToGenJWTs";
	public static final long EXPIRATION_TIME = 864_000_000; // 10 days
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/users/sign-up";
}
