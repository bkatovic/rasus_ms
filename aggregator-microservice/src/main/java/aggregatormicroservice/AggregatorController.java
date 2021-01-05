package aggregatormicroservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cloud.client.discovery.DiscoveryClient;


@RestController
public class AggregatorController {
	
	@Value("${temperature-format}")
	private String tempFormat;
	
	@Autowired
	private DiscoveryClient discoveryClient;

	@GetMapping("/readings")
	public String readings() {
		//String humiditymicroserviceURI = "http://127.0.0.1:8081";
		//String temperaturemicroserviceURI = "http://127.0.0.1:8082";
		
		String humidityURI = null, temperatureURI = null;
		List<ServiceInstance> list;
	    
		list= this.discoveryClient.getInstances("temperature-ms");
	    if (list != null && list.size() > 0 ) {
	        temperatureURI = list.get(0).getUri().toString();
	    } else {
	    	System.out.println("temperature-ms URI not found!");
	    }
	    
	    list = this.discoveryClient.getInstances("humidity-ms");
	    if (list != null && list.size() > 0 ) {
	        humidityURI = list.get(0).getUri().toString();
	    } else {
	    	System.out.println("humidity-ms URI not found!");
	    }
	  
		
		RestInterface rest = new RestTemplateImplementation(temperatureURI, humidityURI, tempFormat);
		return rest.getMeasurement();
	}
	
}
