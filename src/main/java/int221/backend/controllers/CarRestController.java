package int221.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import int221.backend.models.entities.Car;
import int221.backend.repositories.CarRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class CarRestController {
	@Autowired
	private CarRepository carRepository;
	
	@GetMapping("/cars")
    public List<Car> showAllCars() {
		return carRepository.findAll();
    }
	@GetMapping("/cars/{id}")
	public Car showCar(@PathVariable long id) {
		return this.carRepository.findById(id).orElse(null);
	}
	@PostMapping("/cars")
	public Car newCar(@RequestBody Car newCar){
		System.out.println(newCar);
		return this.carRepository.save(newCar);
	}
}
