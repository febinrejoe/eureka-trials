package com.cerner.wsi.demo.fantasy.league.standings;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
@RequestMapping("teams")
public class TeamsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TeamsController.class);

	private RestTemplate restTemplate;

	private EurekaClient discoveryClient;

	@Autowired
	public TeamsController(RestTemplate restTemplate, EurekaClient discoveryClient) {
		super();
		this.restTemplate = restTemplate;
		this.discoveryClient = discoveryClient;
	}

	private static final String EPL_SVC = "premier-league-svc";

	private static final String LALIGA_SVC = "la-liga-svc";

	private static final String EPL_RES = "epl/table/top";
	
	private static final String LALIGA_RES = "laliga/standings/top";

	@RequestMapping("/uefatop")
	public List<String> fetchTopTeam() {

		LOGGER.info("Entering fetchTopTeam");
		
		List<String> topTeams = null;
		InstanceInfo instance = null;
		String baseURL = null;

		try {
			// The boolean determines HTTP (false) or HTTPS (true)
			instance = discoveryClient.getNextServerFromEureka(EPL_SVC, true);
			baseURL = instance.getHomePageUrl();
			LOGGER.info("SECURE PORT: " + instance.getSecurePort());
			
			LOGGER.info("EPL URL : " + baseURL + EPL_RES);
			
			topTeams = new ArrayList<>();
			
			topTeams.add("English Premier League");
			topTeams.add("----------------------");
			
			topTeams.addAll(this.restTemplate.getForObject(baseURL + EPL_RES, List.class));
			
			// The boolean determines HTTP (false) or HTTPS (true)
			instance = discoveryClient.getNextServerFromEureka(LALIGA_SVC, true);
			baseURL = instance.getHomePageUrl();

			LOGGER.info("EPL URL : " + baseURL + LALIGA_RES);
			
			topTeams.add("");
			topTeams.add("Spanish La Liga");
			topTeams.add("---------------");
			
			topTeams.addAll(this.restTemplate.getForObject(baseURL + LALIGA_RES, List.class));

		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}

		return topTeams;
	}

}
