package model;

public class Measurement {
	private String temperature, humidity;

	public Measurement(String temperature, String humidity) {
		this.temperature = temperature;
		this.humidity = humidity;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	@Override
	public String toString() {
		return "Measurement [temperature=" + temperature + ", humidity=" + humidity + "]";
	}
	
}
