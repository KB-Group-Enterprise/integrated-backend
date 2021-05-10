package int221.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import int221.backend.models.entities.CarType;

public interface CarTypeRepository extends JpaRepository<CarType,Long> {
	
}
