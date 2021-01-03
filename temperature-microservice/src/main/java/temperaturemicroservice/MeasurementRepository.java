package temperaturemicroservice;

import org.springframework.data.jpa.repository.JpaRepository;

import temperaturemicroservice.model.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, Long>{

}
