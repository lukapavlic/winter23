package si.um.feri.measurements.vao;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import si.um.feri.measurements.dto.post.PostMeasurement;

@Entity
@Data @NoArgsConstructor
public class Measurement {
	
	public Measurement(PostMeasurement m, Product p) {
		this.value=m.avgTemperature();
		this.product=p;
	}
	
	public si.um.feri.measurements.dto.Measurement toDto() {
		return new si.um.feri.measurements.dto.Measurement(
			id,
			si.um.feri.measurements.dto.Measurement.JSON_DATE_FORMAT.format(created),
			(product!=null)?product.getId():-1,
			value,
			isOk
		);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "measvalue")
	private double value;
	
	private LocalDateTime created=LocalDateTime.now();
	
	private boolean isOk=true;
	
	@ManyToOne
	private Product product;

}
