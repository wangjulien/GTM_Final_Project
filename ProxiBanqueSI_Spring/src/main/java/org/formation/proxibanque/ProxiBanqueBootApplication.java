package org.formation.proxibanque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class ProxiBanqueBootApplication {
	private static final String DEV_PORT = ":4200";
	private static final String HTTPS_PORT = ":443";

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				String host = ConfigFileLoader.PROPERTIES.get("CorsHost");
				registry.addMapping("/**").allowedOrigins(host, host + DEV_PORT, host + HTTPS_PORT)
						.allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD");
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ProxiBanqueBootApplication.class, args);
	}
}
