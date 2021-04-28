package int221.backend.controllers;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import int221.backend.models.entities.Picture;
import int221.backend.models.services.ImageService;
import int221.backend.repositories.PictureRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class PictureRestController {
	@Autowired
	private PictureRepository pictureRepository;
	@Autowired
	private ImageService imageService;
	@GetMapping("/pictures")
    private List<Picture> showAllPictures() {
            return pictureRepository.findAll();
    }

	private String saveImageFile(@RequestParam("image") MultipartFile imageFile) {
		String res = "";
		try {
			res = this.imageService.save(imageFile);
		} catch (IOException e) {
			e.printStackTrace();
			return e.toString();
		}
		return res;
	}

	@PostMapping("/test/img")
	private String test(@RequestParam("images") List<MultipartFile> imageFiles) {
		for ( MultipartFile imageFile : imageFiles) {
			try {
				imageService.save(imageFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "Ok!";
	}
	
	private Picture savePicture(MultipartFile imageFile,Long carId,String imageName) {
		Picture newPic = new Picture(0, carId, imageName);
		return pictureRepository.save(newPic);
	}

	protected List<Picture> savePictures(MultipartFile[] imageFiles,long carId){
		for ( MultipartFile imageFile : imageFiles) {
			this.savePicture(imageFile,carId,this.generateImageName(carId));
		}
		return null;
	}
	
	private String generateImageName(long carId) {
		String name = carId + "_" + this.generateRandomString();
		int limit  = 10;
		int count = 0;
		while (!this.pictureRepository.existsByName(name) && count < limit) {
			name = carId + "_" +generateRandomString();
			limit++;
		}
		return name;

	    
	}
	private String generateRandomString() {
	    int leftLimit = 97;
	    int rightLimit = 122;
	    int targetStringLength = 10;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    return buffer.toString();
	}
}
