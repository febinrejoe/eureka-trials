package com.cerner.wsi.demo.laliga.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/standings")
public class StandingsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StandingsController.class);
	
	@RequestMapping("/top")
	public List<String> fetchTopTeam(@RequestParam(name="limit", required=false) String limit) {
		
		// DEBUG < INFO < WARN < ERROR
		LOGGER.debug("This is a debug message");
		LOGGER.info("This is a infomational message");
		LOGGER.warn("This is a warning message");
		LOGGER.error("This is a error message");
		
		List<String> teams = new ArrayList<>();
		
		teams.add("1. Barcelona");
		teams.add("2. Atletico Madrid");
		teams.add("3. Valencia");
		
		return teams;
	}

	@RequestMapping("/info")
	public String info() {
		return "team information";
	}
}
