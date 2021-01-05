package humiditymicroservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Measurement {
	
	@Id
	private long Id;
	
	private String humidity;

	public Measurement(long Id, String humidity) {
		this.Id = Id;
		this.humidity = humidity;
	}
	
	public Measurement() {
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}


	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

}
