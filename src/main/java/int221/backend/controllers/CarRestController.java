package int221.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;

import int221.backend.models.entities.Car;
import int221.backend.models.exception.CarNotFoundException;
import int221.backend.models.exception.PageNotFoundException;
import int221.backend.models.exception.UnsupportedMediaTypeException;
import int221.backend.models.response.PageWithTotal;
import int221.backend.models.services.CarService;
import int221.backend.models.services.ImageService;
import int221.backend.models.services.PageService;
import int221.backend.repositories.CarRepository;

@CrossOrigin(origins = "${cross.origin.url}")
@RestController
@RequestMapping("/api")
public class CarRestController {
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private ImageService imageservice;
	@Autowired
	private CarService carservice;
	@Autowired	
	private PageService pageService;
	
	@GetMapping("/cars")
	private List<Car> showAllCars() {
		return carRepository.findAll();
    }
	
	@GetMapping("/cars/{id}")
	public Car showCar(@PathVariable long id) {
		Car response = this.carRepository.findById(id).orElse(null);
		if (response == null) {
			throw new CarNotFoundException();
		}
		return response;
	}
	
	@GetMapping("/cars/pages/{pageNo}/{pageSize}/{sortBy}/{direction}")	
	public  PageWithTotal<Car> showAllCarsWithPage(@PathVariable int pageNo,@PathVariable int pageSize,@PathVariable String sortBy,@PathVariable String direction) {
		Sort sorter = pageService.getSortByAndDirection(sortBy, direction);
		Pageable pageable = PageRequest.of(pageNo, pageSize,sorter);
		Page<Car> pageResult = carRepository.findAll(pageable);
		PageWithTotal<Car> response = new PageWithTotal<Car>(pageResult.getContent(),pageResult.getTotalPages());
		if (pageNo > pageResult.getTotalPages()) {
			throw new PageNotFoundException();
		}
		return response;
	}
	
	@GetMapping("/cars/brand/{id}")
	private List<Car> showAllCarsByBrand(@PathVariable("id") long brandId) {
		return carRepository.findAllByBrandId(brandId);
    }
	
	@GetMapping("/cars/brand/{brandId}/pages/{pageNo}/{pageSize}/{sortBy}/{direction}")	
	public  PageWithTotal<Car>  showAllCarsByBrandWithPage(@PathVariable int brandId,@PathVariable int pageNo,@PathVariable int pageSize,@PathVariable String sortBy,@PathVariable String direction) {
		Sort sorter = pageService.getSortByAndDirection(sortBy, direction);
		Pageable pageable = PageRequest.of(pageNo, pageSize,sorter);
		Page<Car> pageResult = carRepository.findAllByBrandIdWithPage(brandId, pageable);
		PageWithTotal<Car> response = new PageWithTotal<Car>(pageResult.getContent(),pageResult.getTotalPages());
		if (pageNo > pageResult.getTotalPages() || pageResult.getNumberOfElements() < 1) {
			throw new PageNotFoundException();
		}
		return response;
	}
	
	@GetMapping("/cars/cartype/{id}")
	private List<Car> showAllCarsByCartype(@PathVariable("id") long cartypeId) {
		return carRepository.findAllByCartypeId(cartypeId);
    }
	
	@GetMapping("/cars/cartype/{cartypeId}/pages/{pageNo}/{pageSize}/{sortBy}/{direction}")	
	public  PageWithTotal<Car> showAllCarsByCartypeWithPage(@PathVariable int cartypeId,@PathVariable int pageNo,@PathVariable int pageSize,@PathVariable String sortBy,@PathVariable String direction) {
		Sort sorter = pageService.getSortByAndDirection(sortBy, direction);
		Pageable pageable = PageRequest.of(pageNo, pageSize,sorter);
		Page<Car> pageResult = carRepository.findAllByCartypeIdWithPage(cartypeId, pageable);
		PageWithTotal<Car> response = new PageWithTotal<Car>(pageResult.getContent(),pageResult.getTotalPages());
		if (pageNo > pageResult.getTotalPages() || pageResult.getNumberOfElements() < 1) {
			throw new PageNotFoundException();
		}
		return response;
	}
	
	@GetMapping("/cars/brand/{brandid}/cartype/{cartypeid}")
	private List<Car> showAllCarsByBrandAndCartype(@PathVariable long brandid,@PathVariable long cartypeid) {
		return carRepository.findAllByBrandIdAndCartypeId(brandid, cartypeid);
    }
	
	@GetMapping("/cars/brand/{brandId}/cartype/{cartypeId}/pages/{pageNo}/{pageSize}/{sortBy}/{direction}")	
	public  PageWithTotal<Car> showAllCarsByBrandAndCartypeWithPage(@PathVariable int brandId,@PathVariable int cartypeId,@PathVariable int pageNo,@PathVariable int pageSize,@PathVariable String sortBy,@PathVariable String direction) {
		Sort sorter = pageService.getSortByAndDirection(sortBy, direction);
		Pageable pageable = PageRequest.of(pageNo, pageSize,sorter);
		Page<Car> pageResult = carRepository.findAllByBrandIdAndCartypeIdWithPage(brandId, cartypeId, pageable);
		PageWithTotal<Car> response = new PageWithTotal<Car>(pageResult.getContent(),pageResult.getTotalPages());
		if (pageNo > pageResult.getTotalPages() || pageResult.getNumberOfElements() < 1) {
			throw new PageNotFoundException();
		}
		return response;
	}
	
	@PostMapping("/cars")
	private Car newCar(@RequestParam("car") String jsonString,@Nullable @RequestParam("images") List<MultipartFile> imageFiles){
			Car car;
			try {
				car = carservice.convertJsonStringToCar(jsonString);
				Car newCar = carRepository.save(car);
				if(!(imageFiles == null) && !imageFiles.isEmpty()) {
					imageservice.SaveAndInsertAll(imageFiles, newCar.getId());
			   }
				return newCar;
			} catch (UnsupportedMediaTypeException e) {
				e.printStackTrace();
				throw new UnsupportedMediaTypeException();
			} catch (Exception e) {
				e.printStackTrace();
				throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	@PutMapping("/cars/{id}") 
	private Car updateCar(@RequestParam("car") String jsonString,@Nullable @RequestParam("images") List<MultipartFile> imageFiles,@PathVariable("id") long id) {
		
		Car car;
		try {
			car = carservice.convertJsonStringToCar(jsonString);
			if (carRepository.findById(id).orElse(null) == null) {
				return null;
			} 
			Car update_car = this.carRepository.getOne(id);
			update_car.setName(car.getName());
			update_car.setBrand(car.getBrand());
			update_car.setColors(car.getColors());
			update_car.setHorsepower(car.getHorsepower());
			update_car.setDescription(car.getDescription());
			update_car.setPrice(car.getPrice());
			update_car.setReleasedate(car.getReleasedate());
			update_car.setCartype(car.getCartype());
			Car updated_car = carRepository.save(update_car);
			if (!(imageFiles == null) && !imageFiles.isEmpty()) {
					imageservice.deleateAllbyCarIdThenInsertAll(imageFiles,updated_car.getId());
			}
			return updated_car;
		} catch (Exception e) {
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("/cars/{id}")
	private String deleteCar(@PathVariable("id") long id) {
		if (imageservice.deleteAllPictureByCarId(id)) {
			carRepository.deleteById(id);
			return "car deleted";
		}
		throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
