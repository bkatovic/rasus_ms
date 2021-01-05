package temperaturemicroservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Measurement {
	
	@Id
	private long Id;
	
	private String temperature;

	public Measurement(long Id, String temperature) {
		this.Id = Id;
		this.temperature = temperature;
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
}
