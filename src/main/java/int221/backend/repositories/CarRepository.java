package int221.backend.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import int221.backend.models.entities.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
	
}


