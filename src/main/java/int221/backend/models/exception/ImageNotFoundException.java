package int221.backend.models.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such Iamge") 
public class ImageNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7238781142984410957L;
}
