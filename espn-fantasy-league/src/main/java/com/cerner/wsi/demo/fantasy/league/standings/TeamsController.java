package com.cerner.wsi.demo.fantasy.league.standings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/teams")
public class TeamsController {
    @Autowired
    private DiscoveryClient discoveryClient;

	@Autowired
	private RestTemplate restTemplate;

	private static final String EPL_SVC = "premier-league-svc";

	private static final String LALIGA_SVC = "la-liga-svc";

	private static final String EPL_RES = "epl/table/top";
	
	private static final String LALIGA_RES = "laliga/standings/top";

	@RequestMapping("/uefatop")
	public List<String> fetchTopTeam() {

		System.out.println("Entering fetchTopTeam");

        List<ServiceInstance> instances = discoveryClient.getInstances(LALIGA_SVC);
        for (ServiceInstance instance : instances){
            System.out.println("a serviceInstnace: " + instance.getHost());
        }

        List<String> topTeams = new ArrayList<>();
        topTeams.add("");
        topTeams.add("English Premier League");
        topTeams.add("----------------------");


        // let's construct the URI based on the service VIP name and a context
        URI premierUri = UriComponentsBuilder.fromUriString("//" + EPL_SVC + "/table/top")
                .build()
                .toUri();

        // let's use RestTemplate to call PREMIER_LEAGUE
        List<String> premierList = restTemplate.getForObject(premierUri, List.class);
        topTeams.addAll(premierList);

        System.out.println("premier league teams: " + premierList);

        // let's construct the URI based on the service VIP name and a context
        URI laLigaUri = UriComponentsBuilder.fromUriString("//" + LALIGA_SVC + "/standings/top")
                .build()
                .toUri();

        // let's use RestTemplate to call LALIGA_SVC
        List<String> laLigaTeams = restTemplate.getForObject(laLigaUri, List.class);
        topTeams.add("");
        topTeams.add("Spanish La Liga");
        topTeams.add("---------------");
        topTeams.addAll(laLigaTeams);

        System.out.println("la liga teams: " + laLigaTeams);

		return topTeams;
	}

	@RequestMapping("/info")
	public String info() {
		return "teams controller";
	}

}
