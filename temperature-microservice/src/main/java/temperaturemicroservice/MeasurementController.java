package temperaturemicroservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeasurementController {
	private MeasurementService service;

	public MeasurementController(MeasurementService service) {
		this.service = service;
	}
	
	@GetMapping("/current-reading")
	public String currentReading() {
		return service.getMeasurement();
	}

}
