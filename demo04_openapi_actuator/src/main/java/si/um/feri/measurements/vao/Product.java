package si.um.feri.measurements.vao;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

	@Size(min = 2, max = 20)
	@NotBlank
	protected String name;

	protected LocalDateTime created=LocalDateTime.now();

	@Max(value = 300)
	protected double maxMeasure;

	@Min(value = -276)
	protected double minMeasure;

}
