package int221.backend.controllers;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@PostMapping("/test/img")
	private String test(@RequestParam("images") List<MultipartFile> imageFiles) {
		for ( MultipartFile imageFile : imageFiles) {
			try {
				imageService.SaveAndInsert(imageFile, 2);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "Ok!";
	}
	@GetMapping("/test/img/{id}")
	private ResponseEntity<byte[]> testGet(@PathVariable("id") long id) {
		Picture pic = pictureRepository.findById(id).orElse(null);
		String img_name = pic.getName();
		MediaType mediaType = imageService.getMediaType(pic.getFiletype());
		
		try {
			byte[] image = imageService.getImageFile(img_name);
			return ResponseEntity.ok().contentType(mediaType).body(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	} 
	@GetMapping("/test/converter")
	public String testConverter() {
		return imageService.getFileTypeFromContentType("image/jpeg");
	}
}
