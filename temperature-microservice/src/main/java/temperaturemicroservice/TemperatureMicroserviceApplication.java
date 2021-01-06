package temperaturemicroservice;

import java.io.FileReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

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
		            new FileReader("src/main/resources/mjerenja.csv"))
		            .withType(Measurement.class).build().parse();
		  
		  long id = 0;
		  for(Measurement measurement: measurements) {
			  measurement.setId(id++);
		  }
		  
		  repo.saveAll(measurements);
		};
	}

}
