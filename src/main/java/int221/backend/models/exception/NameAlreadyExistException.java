package int221.backend.models.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT, reason="Name Already Existed") 
public class NameAlreadyExistException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8231042156290853592L;

}
