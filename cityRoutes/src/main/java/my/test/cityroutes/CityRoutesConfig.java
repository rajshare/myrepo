package my.test.cityroutes;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Configuration
public class CityRoutesConfig {

	public static Map<String, List<String>> cityRoutes = new HashMap<>();
	
	
	 @Bean
	 boolean intialize() throws Exception {

		 try (BufferedReader br = Files.newBufferedReader(
				 Paths.get(CityRoutesConfig.class.getResource("city.txt").toURI() ))) {	 

	            String line;
	            String[] tokens;
	            while ((line = br.readLine()) != null) {
	                
	            	tokens = line.split(",");	
	            	List<String> cityList;
	            	if (tokens.length == 2) {
	            		tokens[0] = tokens[0].trim();
	            		tokens[1] = tokens[1].trim();
	            		
	            		// origin
	            		cityList = cityRoutes.get(tokens[0]);
	            		if (cityList != null) {
	            			if (!cityList.contains(tokens[1])) {
	            				cityList.add(tokens[1]);
	            			}
	            		} else {		            	
	            			cityList = new ArrayList<String>();
	            			cityList.add(tokens[1]);
	            			cityRoutes.put(tokens[0], cityList);
	            		}
	            		
	            		// destination
	            		cityList = cityRoutes.get(tokens[1]);
	            		if (cityList != null) {
	            			if (!cityList.contains(tokens[0])) {
	            				cityList.add(tokens[0]);
	            			}
	            		} else {	            		
	            			cityList = new ArrayList<String>();
	            			cityList.add(tokens[0]);
	            			cityRoutes.put(tokens[1], cityList);
	            		}
	            	}
	            }

	            System.out.println("******** KEYS " + cityRoutes.keySet().toString());
	            System.out.println("******** VALUES " + cityRoutes.values().toString());
	        } catch (IOException e) {
	            System.err.format("IOException: %s%n", e);
	            return false;
	        }
		 
		 return true;
	 }
}
