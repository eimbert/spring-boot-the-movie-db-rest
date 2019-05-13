package com.eib.themoviedb.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MoviedbWebServiceClient {
	
	public static final String SEARCH_URL = "https://api.themoviedb.org/3/movie/MOVIE?api_key=APIKEY";
	public static final String API_KEY = "04d5f9132068690523d6ce6e5b7d77b8";
	
	
	public String sendGetRequest(String requestURL) {
		StringBuffer response = new StringBuffer();
		
		try {
			URL url = new URL(requestURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "*/*");
			connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			InputStream stream = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader buffer = new BufferedReader(reader);
			
			String line;
			while((line = buffer.readLine()) != null) {
				response.append(line);
			}
			buffer.close();
			connection.disconnect();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response.toString();
	}
	
	public String searchMovieByTitle(String title, String key) {
		String requestURL = SEARCH_URL.replaceAll("MOVIE", title).replaceAll("APIKEY",key);
		//String title = URLEncoder.encode(title, "UTF-8));
		return sendGetRequest(requestURL);
	}
}
