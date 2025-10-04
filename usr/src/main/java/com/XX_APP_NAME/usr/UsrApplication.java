package com.XX_APP_NAME.usr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import com.XX_APP_NAME.usr.model.UserConverter;

@RefreshScope
@EnableJpaAuditing
@EnableJpaRepositories
@EnableTransactionManagement
@SpringBootApplication
public class UsrApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsrApplication.class, args);
	}
	
	@Bean
	public UserService userService() {return new UserService();}
	
	@Bean
	public UserConverter userDtoConverter() {return new UserConverter();}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {return new RestTemplate();}

}
