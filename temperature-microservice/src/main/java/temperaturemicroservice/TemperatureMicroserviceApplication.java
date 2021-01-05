package temperaturemicroservice;

import java.io.FileReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opencsv.bean.CsvToBeanBuilder;

import temperaturemicroservice.model.Measurement;

@EnableDiscoveryClient
@SpringBootApplication
public class TemperatureMicroserviceApplication {
	
	@Autowired
	private MeasurementRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(TemperatureMicroserviceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner() {
		return args -> {
		  List<Measurement> measurements = new CsvToBeanBuilder<Measurement>(
		            new FileReader("mjerenja.csv"))
		            .withType(Measurement.class).build().parse();
		  
		  long id = 0;
		  for(Measurement measurement: measurements) {
			  measurement.setId(id++);
		  }
		  
		  repo.saveAll(measurements);
		};
	}
	
	
	
	
	@RestController
	class ServiceInstanceRestController {
		
		@Autowired
		private DiscoveryClient discoveryClient;
	
		@RequestMapping("/service-instances/{applicationName}")
		public List<ServiceInstance> serviceInstancesByApplicationName(
				@PathVariable String applicationName) {
			return this.discoveryClient.getInstances(applicationName);
		}
	}

}
