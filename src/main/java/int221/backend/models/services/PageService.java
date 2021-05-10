package int221.backend.models.services;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import lombok.NoArgsConstructor;

@Component("PageService")
@Scope("singleton")
@NoArgsConstructor
public class PageService {
	public Sort getSortByAndDirection(String sortBy,String direction) {
		Direction sort_d = Sort.DEFAULT_DIRECTION;
		if (direction.equalsIgnoreCase("asc")) {
			sort_d = Sort.Direction.ASC;
		} else if (direction.equalsIgnoreCase("desc")) {
			sort_d = Sort.Direction.DESC;
		}
		return Sort.by(sort_d,sortBy);
	}
}
