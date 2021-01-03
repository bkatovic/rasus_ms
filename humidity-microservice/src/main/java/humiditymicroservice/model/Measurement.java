package humiditymicroservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Measurement {
	
	@Id
	private long Id;
	
	private String temperature, pressure, humidity, co, no2, so2;

	public Measurement(long Id, String temperature, String pressure, String humidity, String co, String no2, String so2) {
		this.Id = Id;
		this.temperature = temperature;
		this.pressure = pressure;
		this.humidity = humidity;
		this.co = co;
		this.no2 = no2;
		this.so2 = so2;
	}
	
	public Measurement() {
		
	}
	

	public String getTemperature() {
		return temperature;
	}


	public String getHumidity() {
		return humidity;
	}
	
}
