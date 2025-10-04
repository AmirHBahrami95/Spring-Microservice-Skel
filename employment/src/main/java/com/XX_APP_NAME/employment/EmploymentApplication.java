package com.XX_APP_NAME.employment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

@RefreshScope
@EnableJpaAuditing
@SpringBootApplication
public class EmploymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmploymentApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate(){return new RestTemplate();}

}
