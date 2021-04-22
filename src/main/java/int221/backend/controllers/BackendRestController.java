package int221.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import int221.backend.models.Brand;
import int221.backend.models.Car;
import int221.backend.models.Color;
import int221.backend.repositories.BrandRepository;
import int221.backend.repositories.CarRepository;
import int221.backend.repositories.ColorRepository;

@CrossOrigin(origins = "*")
@RestController
public class BackendRestController {
	
	@Autowired
	private BrandRepository brandRepository;
	@Autowired
	private ColorRepository colorRepository;
	@Autowired
	private CarRepository carRepository;
	
	@GetMapping(path="/api/health")
	public String health() {
		return "Health is Pretty Good, Thanks for asking! OK!";
	}
	
	@GetMapping(path="/api/brands/{id}")
	public Brand showBrands(@PathVariable long id) {
		Brand brand = brandRepository.findById(id).orElse(null);
		return brand;
	}
	
	@GetMapping("/api/brands")
    public List<Brand> showAllBrands() {
            return brandRepository.findAll();
    }
	
	@GetMapping("/api/colors")
    public List<Color> showAllColors() {
            return colorRepository.findAll();
    }
	
	@GetMapping("/api/cars")
    public List<Car> showAllCars() {
            return carRepository.findAll();
    }
}
