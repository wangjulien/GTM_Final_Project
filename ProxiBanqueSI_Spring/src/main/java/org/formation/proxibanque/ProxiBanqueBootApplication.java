package org.formation.proxibanque;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ProxiBanqueBootApplication {

	@Value("${security.cors.permit-host}")
	private String corsPermitHost;
	
	private static final String HTTP = "http://";
	private static final String HTTPS = "https://";
	private static final String DEV_PORT = ":4200";
	private static final String HTTPS_PORT = ":443";


	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins(HTTP + corsPermitHost + DEV_PORT, 
										HTTPS + corsPermitHost, 
										HTTPS + corsPermitHost + HTTPS_PORT)
						.allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD");
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ProxiBanqueBootApplication.class, args);
	}
}
