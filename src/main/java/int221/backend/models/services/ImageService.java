package int221.backend.models.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import int221.backend.models.entities.Picture;
import int221.backend.repositories.PictureRepository;

@Component("imageService")
@Scope("singleton")
public class ImageService {
	@Autowired
	private PictureRepository pictureRepository;
	private final String FOLDER_URL;
	
	ImageService(@Value("${photos_url}") String url) {
		this.FOLDER_URL = System.getProperty("user.dir") + url;
	}
	
	public String save(MultipartFile imageFile,String fileName) throws IOException {
		byte[] bytes = imageFile.getBytes();
		Path path = Paths.get(FOLDER_URL + fileName);
		Files.write(path,bytes);
		return "Picture saved Successfully";
	}
	
	public boolean SaveAndInsert(MultipartFile imageFile, long carId) throws IOException {
		String image_name = generateImageName(carId,imageFile.getContentType());
		String filetype = imageFile.getContentType();
		//save image files
		save(imageFile,image_name);
		//Insert references to Database
		Picture newPic = new Picture(0,carId,image_name,filetype);
		pictureRepository.save(newPic);
		return true;
	}
	
	 
	
	public byte[] getImageFile(String fileName) throws IOException {
		String url = FOLDER_URL+fileName;
		System.out.println(url);
		Path path = Paths.get(FOLDER_URL+fileName);
		byte[] data = Files.readAllBytes(path);
		System.out.println(data);
		return data;
 	}
	
	public String getFileTypeFromContentType(String contentType) {
		String fileType = "";
		switch (contentType) {
		case "image/jpeg":
			fileType = ".jpg";
			break;
		case "image/png":
			fileType = ".png";
			break;
		default:
			break;
		}
		return fileType;
	}
	
	public MediaType getMediaType(String contentType) {
		MediaType mediaType = null;
		switch (contentType) {
		case "image/jpeg":
			mediaType = MediaType.IMAGE_JPEG;
			break;
		case "image/png":
			mediaType = MediaType.IMAGE_PNG;
			break;
		default:
			break;
		}
		return mediaType;
	}
	
	public String generateImageName(long carId,String contentType) {
		String name = carId + "-" + this.generateRandomString() + getFileTypeFromContentType(contentType);
		if (pictureRepository.existsByName(name)) {
			while(pictureRepository.existsByName(name)) {
				name = carId + "-" + this.generateRandomString() + getFileTypeFromContentType(contentType);
			}
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
