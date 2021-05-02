package int221.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import int221.backend.models.entities.Picture;

public interface PictureRepository extends JpaRepository<Picture,Long> {
	public boolean existsByName(String name);
	public List<Picture> findAllByCarid(long carId);
}
