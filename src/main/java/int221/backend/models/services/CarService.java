package int221.backend.models.services;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import int221.backend.models.entities.Car;
import lombok.NoArgsConstructor;

@Component("CarService")
@Scope("singleton")
@NoArgsConstructor
public class CarService {
	
	public Car convertJsonStringToCar(String jsonString) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Car car = mapper.readValue(jsonString, Car.class);
		return car;
	}
}
