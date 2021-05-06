package int221.backend.models.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.UNSUPPORTED_MEDIA_TYPE, reason="Media Type Not Supported, try PNG,JPEG") 
public class UnsupportedMediaTypeException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4370607966616509167L;

}
