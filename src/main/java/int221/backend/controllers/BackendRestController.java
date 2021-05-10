package int221.backend.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "${cross.origin.url}")
@RestController
@RequestMapping("/api")
public class BackendRestController {
	
	@GetMapping(path="/health")
	public String health() {
		return "Server Is Running.";
	}
	
		
}
