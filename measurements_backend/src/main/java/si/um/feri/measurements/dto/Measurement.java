package si.um.feri.measurements.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.format.DateTimeFormatter;

@JsonInclude(value = Include.NON_NULL)
public record Measurement (
	int id,
	String date,
	int productId,
	double avgTemperature,
	boolean isOk) {

	public static final DateTimeFormatter JSON_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

}
