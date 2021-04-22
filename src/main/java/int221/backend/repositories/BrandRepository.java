package int221.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import int221.backend.models.Brand;
@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}
