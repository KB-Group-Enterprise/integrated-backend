package int221.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import int221.backend.models.Color;


public interface ColorRepository  extends JpaRepository<Color, Long> {
}
