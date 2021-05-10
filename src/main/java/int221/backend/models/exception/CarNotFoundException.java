package int221.backend.models.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such Car") 
public class CarNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9123810148983264352L;

}
