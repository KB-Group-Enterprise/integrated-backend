package int221.backend.models.entities;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Cartypes")
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode
public class CarType {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NonNull
	private String name;
}
