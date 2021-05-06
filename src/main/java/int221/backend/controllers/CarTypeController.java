package int221.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import int221.backend.repositories.CarTypeRepository;
import int221.backend.models.entities.CarType;

@CrossOrigin(origins = "${cross.origin.url}")
@RestController
@RequestMapping("/api")
public class CarTypeController {
	@Autowired
	private CarTypeRepository carTypeRepository;
	
	@GetMapping("/cartypes")
	private List<CarType> showAll() {
		return carTypeRepository.findAll();
	}
}
