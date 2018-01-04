package com.cerner.wsi.demo.premierleague;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PremierLeagueApplication {

	public static void main(String[] args) {
		SpringApplication.run(PremierLeagueApplication.class, args);
	}
}
