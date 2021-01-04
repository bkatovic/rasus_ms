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

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getPressure() {
		return pressure;
	}

	public void setPressure(String pressure) {
		this.pressure = pressure;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getCo() {
		return co;
	}

	public void setCo(String co) {
		this.co = co;
	}

	public String getNo2() {
		return no2;
	}

	public void setNo2(String no2) {
		this.no2 = no2;
	}

	public String getSo2() {
		return so2;
	}

	public void setSo2(String so2) {
		this.so2 = so2;
	}
	
}
