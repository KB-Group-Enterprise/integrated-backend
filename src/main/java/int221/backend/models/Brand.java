package int221.backend.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;



@Entity
@Table(name = "Brands")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Brand {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NonNull
	private String name;
}
