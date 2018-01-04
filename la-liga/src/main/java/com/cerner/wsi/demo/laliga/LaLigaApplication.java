package com.cerner.wsi.demo.laliga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LaLigaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaLigaApplication.class, args);
	}
}
