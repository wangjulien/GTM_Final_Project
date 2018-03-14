package org.formation.proxibanque;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Un singleton chargeur du fichier de configuration "config.properties", qui se
 * trouve dans ClassPath
 * 
 * @author Jiliang.WANG
 *
 */
public enum ConfigFileLoader {
	PROPERTIES;

	private final static String FILE_NAME = "/config.properties";
	private Properties properties = new Properties();

	private ConfigFileLoader() {
		load();
	}

	private void load() {
		final Logger LOGGER = LoggerFactory.getLogger(ConfigFileLoader.class);
		
		try (InputStream input = ConfigFileLoader.class.getResourceAsStream(FILE_NAME)) {
			properties.load(input);
			LOGGER.info("Configuration file {} loaded.", FILE_NAME);
			
		} catch (IOException e) {
			// Logger exception.			
			LOGGER.error(e.toString());

			// Lever une Runtime, car si config non chargé, il faut que module se plante
			throw new RuntimeException(e);
		}
	}

	public String get(String key) {
		return properties.getProperty(key);
	}
}
