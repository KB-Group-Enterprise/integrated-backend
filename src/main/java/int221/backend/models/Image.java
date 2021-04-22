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
@Table(name = "Images")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long cars_id;
	@NonNull
	private String name;
}
