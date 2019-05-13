package com.eib.themoviedb.controllers;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eib.themoviedb.model.MoviedbWebServiceClient;
import com.eib.themoviedb.model.entity.MovieInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class MovieController {

	
	
	@RequestMapping("/list")
	public String showMovies(Model model) {
		
		ObjectMapper JSON_MAPPER = new ObjectMapper();
		MoviedbWebServiceClient webServiceClient = new MoviedbWebServiceClient();
		
		//MovieInfo minfo = new MovieInfo();
		//ArrayList<MovieInfo> movies = new ArrayList<MovieInfo>();
		String json = webServiceClient.searchMovieByTitle("76341", "04d5f9132068690523d6ce6e5b7d77b8");
		
		
		try {
			JSON_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			MovieInfo minfo =  JSON_MAPPER.readValue(json, MovieInfo.class);
			
			//movies = JSON_MAPPER.readValue(json , JSON_MAPPER.getTypeFactory().constructCollectionType(ArrayList.class, MovieInfo.class));		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(webServiceClient.searchMovieByTitle("76341", "04d5f9132068690523d6ce6e5b7d77b8"));
		
		model.addAttribute("titulo", "Listado Películas");
		//model.addAttribute("library" );
		return "index";
	}

}
