package int221.backend.controllers;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import int221.backend.models.entities.Picture;
import int221.backend.models.exception.ImageNotFoundException;
import int221.backend.models.services.ImageService;
import int221.backend.repositories.PictureRepository;

@CrossOrigin(origins = "${cross.origin.url}")
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
	@GetMapping("/img/{id}")
	private ResponseEntity<byte[]> getImage(@PathVariable("id") long id) {
		try {
			Picture pic = pictureRepository.findById(id).orElse(null);
			String img_name = pic.getName();
			MediaType mediaType = imageService.getMediaType(pic.getFiletype());
			byte[] image = imageService.getImageFile(img_name);
			return ResponseEntity.ok().contentType(mediaType).body(image);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ImageNotFoundException();
		} catch (Exception e) {
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
}
