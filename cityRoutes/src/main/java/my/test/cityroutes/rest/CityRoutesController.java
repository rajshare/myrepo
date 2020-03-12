package my.test.cityroutes.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import my.test.cityroutes.CityRoutesConfig;

@RestController
public class CityRoutesController {

	
	@RequestMapping(value = "/connected",  method = RequestMethod.GET)
	public String getCityRoute( @RequestParam("origin") String origin,@RequestParam("destination") String dstn, HttpServletResponse response )  {

		List<String> cityList1 = CityRoutesConfig.cityRoutes.get(origin);
		if (cityList1 != null) {
			if (cityList1.contains(dstn)) {
				return "yes";
			}
		}
		
		List<String> cityList2 = CityRoutesConfig.cityRoutes.get(dstn);
		if (cityList2 != null) {
			if (cityList2.contains(origin)) {
				return "yes";
			}
		}	
		
		if ( (cityList1 != null) && (cityList2 != null) ) {
			
			for (String city : cityList1) {
				if (cityList2.contains(city)) {
					return "yes";
				}
			}
		}
		return "no";
	}

}
