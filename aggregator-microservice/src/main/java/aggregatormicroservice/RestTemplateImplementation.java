package aggregatormicroservice;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import model.Measurement;


public class RestTemplateImplementation implements RestInterface{

	private String humiditymicroserviceURI;
	private String temperaturemicroserviceURI;
	private String tempFormat;
	private RestTemplate restTemplate;
	
	public RestTemplateImplementation() {
		
	}

	public RestTemplateImplementation(String humiditymicroserviceURI,
									  String temperaturemicroserviceURI,
									  String tempFormat)
	{
		this.humiditymicroserviceURI = humiditymicroserviceURI;
		this.temperaturemicroserviceURI = temperaturemicroserviceURI;
		this.tempFormat = tempFormat;

        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	}
	
	
	@Override
	public String getMeasurement(){
		String humidity = restTemplate.getForObject(humiditymicroserviceURI + "/current-reading", String.class);
		String temperature = restTemplate.getForObject(temperaturemicroserviceURI + "/current-reading", String.class);
		
		if (tempFormat.equals("K")) temperature = Double.toString(Double.parseDouble(temperature) + 273.15);
		
		return new Measurement(temperature, humidity).toString();

	}
}
