package aggregatormicroservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AggregatorController {
	
	@Value("${temperature-format}")
	private String tempFormat;

	@GetMapping("/readings")
	public String readings() {
		String humiditymicroserviceURL = "http://127.0.0.1:8081";
		String temperaturemicroserviceURL = "http://127.0.0.1:8082";
		

		
		RestInterface rest = new RestTemplateImplementation(humiditymicroserviceURL, temperaturemicroserviceURL, tempFormat);
		return rest.getMeasurement();
	}
	
}
