package int221.backend.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import int221.backend.models.entities.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
	
	@Query("SELECT c FROM Car c WHERE c.brand.id = ?1")
	public List<Car> findAllByBrandId(long brandId);
	@Query("SELECT c FROM Car c WHERE c.brand.id = ?1")
	public Page<Car> findAllByBrandIdWithPage(long brandId,Pageable pageable);
	
	@Query("SELECT c FROM Car c WHERE c.cartype.id = ?1")
	public List<Car> findAllByCartypeId(long cartypeid);
	@Query("SELECT c FROM Car c WHERE c.brand.id = ?1")
	public Page<Car> findAllByCartypeIdWithPage(long brandId,Pageable pageable);
	
	@Query("SELECT c FROM Car c WHERE c.brand.id = ?1 AND c.cartype.id = ?2")
	public List<Car> findAllByBrandIdAndCartypeId(long brandId,long cartypeId);
	@Query("SELECT c FROM Car c WHERE c.brand.id = ?1 AND c.cartype.id = ?2")
	public Page<Car> findAllByBrandIdAndCartypeIdWithPage(long brandId,long cartypeId,Pageable pageable);
}


