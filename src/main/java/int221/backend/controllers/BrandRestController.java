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

import int221.backend.models.entities.Brand;
import int221.backend.repositories.BrandRepository;

@CrossOrigin(origins = "${cross.origin.url}")
@RestController
@RequestMapping("/api")
public class BrandRestController {
	@Autowired
	private BrandRepository brandRepository;
	
	@GetMapping("/brands")
    public List<Brand> showAllBrands() {
            return brandRepository.findAll();
    }
	
	@GetMapping(path="/brands/{id}")
	public Brand showBrand(@PathVariable long id) {
		Brand brand = brandRepository.findById(id).orElse(null);
		return brand;
	}
	@PostMapping("/brands")
	protected Brand Brand(@RequestBody Brand newBrand) {
		return brandRepository.save(newBrand);
	} 
}
