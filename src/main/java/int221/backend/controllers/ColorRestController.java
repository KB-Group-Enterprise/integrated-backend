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

import int221.backend.models.entities.Color;
import int221.backend.repositories.ColorRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ColorRestController {
	@Autowired
	private ColorRepository colorRepository;
	@GetMapping("/colors")
	protected List<Color> showAllColors() {
            return colorRepository.findAll();
    }
	@GetMapping("/colors/{id}")
	protected Color showColor(@PathVariable long id) {
		return this.colorRepository.findById(id).orElse(null);
	}
	@PostMapping("/colors")
	public Color newCar(@RequestBody Color newColor){
		System.out.println(newColor);
		return this.colorRepository.save(newColor);
	}
}
