package si.um.feri.measurements.vao;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data @NoArgsConstructor
public class Product {

	public Product(si.um.feri.measurements.dto.Product dto) {
		setName(dto.name());
		setMaxMeasure(dto.maxMeasure());
		setMinMeasure(dto.minMeasure());
	}
	
	public void updateFrom(si.um.feri.measurements.dto.Product dto) {
		setName(dto.name());
		setMaxMeasure(dto.maxMeasure());
		setMinMeasure(dto.minMeasure());
	}
	
	public si.um.feri.measurements.dto.Product toDto() {
		return new si.um.feri.measurements.dto.Product(
			getId(),
			getName(),
			maxMeasure,
			minMeasure);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected int id;

	protected String name;

	protected LocalDateTime created=LocalDateTime.now();

	protected double maxMeasure;

	protected double minMeasure;

}
