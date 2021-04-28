package int221.backend.models.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component("imageService")
@Scope("singleton")
public class ImageService {
	
	private final String FOLDER_URL;
	
	ImageService(@Value("${photos_url}") String url) {
		this.FOLDER_URL = System.getProperty("user.dir") + url;
	}
	
	public String save(MultipartFile imageFile) throws IOException {
		byte[] bytes = imageFile.getBytes();
		Path path = Paths.get(FOLDER_URL + imageFile.getOriginalFilename());
		Files.write(path,bytes);
		return "Picture saved Successfully";
	}
}
