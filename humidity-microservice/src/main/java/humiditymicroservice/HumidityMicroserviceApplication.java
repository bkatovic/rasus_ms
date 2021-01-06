package humiditymicroservice;

import java.io.File;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.opencsv.bean.CsvToBeanBuilder;

import humiditymicroservice.model.Measurement;

@EnableDiscoveryClient
@SpringBootApplication
public class HumidityMicroserviceApplication {
	
	@Autowired
	private MeasurementRepository repo;

	public static void main(String[] args) {

		
		SpringApplication.run(HumidityMicroserviceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner() {
		return args -> {
		  File mjerenjaFile = getFileFromResource("mjerenja.csv");

		  List<Measurement> measurements = new CsvToBeanBuilder<Measurement>(
		            new FileReader(mjerenjaFile))
		            .withType(Measurement.class).build().parse();
		  
		  long id = 0;
		  for(Measurement measurement: measurements) {
			  measurement.setId(id++);
		  }
		  
		  repo.saveAll(measurements);
		};
	}
	
    private File getFileFromResource(String fileName) throws URISyntaxException{

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }

    }

}
