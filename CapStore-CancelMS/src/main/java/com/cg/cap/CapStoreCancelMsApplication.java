package com.cg.cap;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@EnableEurekaClient
@EnableSwagger2
@EnableCircuitBreaker
@SpringBootApplication
public class CapStoreCancelMsApplication {
	
	

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(CapStoreCancelMsApplication.class, args);
	}
	
	
	/***************************** Swagger Configuration ***********************************/ 
	
	@Bean
	  public Docket SwaggerCongiguration()
	  {
		//here we need to create instance of docket to user swagger2 documentation
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.cg.cap"))
				.build()
				.apiInfo(apiDetails());
		  
	  }
	private ApiInfo apiDetails()
	{
		return new ApiInfo(
		"Cancel MS Api",
		"Api for Cancel Ms of CapStore",
		"1.0",
		"Api end points for crud operation",
		new springfox.documentation.service.Contact("Plaban Roy","plabanroy09@gmail.com","www.google.com"),
		"Capgemini BootCamp Training",
		"using swagger for documentation",
		Collections.emptyList());
	}


}
