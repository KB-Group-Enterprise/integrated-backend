package int221.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import int221.backend.models.entities.Picture;

public interface PictureRepository extends JpaRepository<Picture,Long> {
	public boolean existsByName(String name);
}
