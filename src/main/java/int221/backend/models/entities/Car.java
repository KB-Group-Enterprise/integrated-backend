package int221.backend.models.entities;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Cars")
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode
public class Car {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
    private String name;
	@Column(nullable = false)
    private double price;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private long horsepower;
	@Column(nullable = false)
	private Date releasedate;
	@OneToMany(cascade=CascadeType.REMOVE,fetch=FetchType.LAZY,mappedBy="carid")
	private List<Picture> pictures;
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "carcolors", joinColumns = @JoinColumn(name = "cars_id",referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "colors_id",referencedColumnName = "id"))
	private Set<Color> colors;
	@ManyToOne
	@JoinColumn(name="brandid")
	private Brand brand;
	@ManyToOne
	@JoinColumn(name="cartypeid")
	private CarType cartype;
}
