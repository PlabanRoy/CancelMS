package com.cg.cap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CapStoreDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapStoreDiscoveryServerApplication.class, args);
	}

}
