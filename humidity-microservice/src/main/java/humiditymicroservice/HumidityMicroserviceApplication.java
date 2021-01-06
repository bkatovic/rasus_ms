package humiditymicroservice;

import java.io.FileReader;
import java.io.InputStream;
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
	
    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }
	

}
