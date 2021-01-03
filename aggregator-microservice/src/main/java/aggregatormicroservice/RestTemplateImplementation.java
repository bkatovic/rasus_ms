package aggregatormicroservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import model.Measurement;


public class RestTemplateImplementation implements RestInterface{

	private String humiditymicroserviceURL;
	private String temperaturemicroserviceURL;
	private String tempFormat;
	private RestTemplate restTemplate;
	
	public RestTemplateImplementation() {
		
	}

	public RestTemplateImplementation(String humiditymicroserviceURL,
									  String temperaturemicroserviceURL,
									  String tempFormat)
	{
		this.humiditymicroserviceURL = humiditymicroserviceURL;
		this.temperaturemicroserviceURL = temperaturemicroserviceURL;
		this.tempFormat = tempFormat;

        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	}
	
	
	@Override
	public String getMeasurement(){
		String humidity = restTemplate.getForObject(humiditymicroserviceURL + "/current-reading", String.class);
		String temperature = restTemplate.getForObject(temperaturemicroserviceURL + "/current-reading", String.class);
		
		if (tempFormat.equals("K")) temperature = Double.toString(Double.parseDouble(temperature) + 273.15);
		
		return new Measurement(temperature, humidity).toString();

	}
}
