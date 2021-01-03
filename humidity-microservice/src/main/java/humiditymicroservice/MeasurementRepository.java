package humiditymicroservice;

import org.springframework.data.jpa.repository.JpaRepository;

import humiditymicroservice.model.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, Long>{

}
