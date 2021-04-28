package int221.backend.models.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;



@Entity
@Table(name = "Brands")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString @EqualsAndHashCode
public class Brand {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NonNull
	private String name;
}
