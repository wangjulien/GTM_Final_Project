package org.formation.proxibanque;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class ProxiBanqueBootApplication {

	@Value("${security.cors.permit-host}")
	private String corsPermitHost;

	private static final String DEV_PORT = ":4200";
	private static final String HTTPS_PORT = ":443";

//	@Bean
//	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
//		PropertySourcesPlaceholderConfigurer p = new PropertySourcesPlaceholderConfigurer();
//		p.setIgnoreUnresolvablePlaceholders(true);
//		return p;
//	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				System.out.println(corsPermitHost);
				registry.addMapping("/**")
						.allowedOrigins(corsPermitHost, corsPermitHost + DEV_PORT, corsPermitHost + HTTPS_PORT)
						.allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD");
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ProxiBanqueBootApplication.class, args);
	}
}
