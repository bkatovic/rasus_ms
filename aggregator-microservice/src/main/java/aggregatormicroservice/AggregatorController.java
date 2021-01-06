package aggregatormicroservice;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
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
		
		URL url = null;
		try {
			url = new URL("http://eureka-server:8761");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection)url.openConnection();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			connection.setRequestMethod("GET");
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection.connect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int code = 420;
		try {
			code = connection.getResponseCode();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(code);
		
		
		
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
