package si.um.feri.sb101.vao;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document("products")
@Data @NoArgsConstructor
public class Product {

	public Product(String name, double maxMeasure, double minMeasure) {
		this.name = name;
		this.maxMeasure = maxMeasure;
		this.minMeasure = minMeasure;
	}

	@Id
	protected String id;

	protected String name;

	protected LocalDateTime created=LocalDateTime.now();

	protected double maxMeasure;

	protected double minMeasure;

}
