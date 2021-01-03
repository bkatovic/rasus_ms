package humiditymicroservice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import humiditymicroservice.model.Measurement;

@SpringBootApplication
public class HumidityMicroserviceApplication {
	
	@Autowired
	private MeasurementRepository repo;

	public static void main(String[] args) {

		
		SpringApplication.run(HumidityMicroserviceApplication.class, args);
	}
	
    @Bean
    CommandLineRunner runner(){
        return args -> {
    		List<String> rawCsv = new ArrayList<String>();
    		File file = new File("mjerenja.csv");
    		try {
    			Scanner scanner = new Scanner(file);
    			while(scanner.hasNext()) {
    				rawCsv.add(scanner.next());
    			}
    			scanner.close();
    		}catch(FileNotFoundException e) {
    			e.printStackTrace();
    		}
    		rawCsv.remove(0);
    		
    		List<Measurement> readings = new ArrayList<Measurement>();
    		long cnt = 0;
    		for (String entry : rawCsv) {
    			String[] entryValues = entry.split(",");
    			List<String> temp = new ArrayList<String>();
    			for (int i=0; i<=5; i++) {
    				try {
    					temp.add(entryValues[i]);
    				}
    				catch (ArrayIndexOutOfBoundsException e) {
    					temp.add("");	
    				}
    			}
    			readings.add(new Measurement(cnt,
    										 temp.get(0),
    										 temp.get(1),
    										 temp.get(2),
    										 temp.get(3),
    										 temp.get(4),
    										 temp.get(5)));
    			cnt++;
    		}
    		
    		repo.saveAll(readings);


        };
    }
	

}
