package temperaturemicroservice;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class MeasurementService {
	private MeasurementRepository repo;

	public MeasurementService(MeasurementRepository repo) {
		this.repo = repo;
	}
	
	public String getMeasurement() {
		LocalDateTime now = LocalDateTime.now();
		int hour = now.getHour();
		int minute = now.getMinute();
		
		long id = (4 * hour) + (minute / 15);

		
		return repo.findById(id).get().getTemperature();
	}
}
