package si.um.feri.measurements.dto;

public record Measurement(
	int id,
	String date,
	int productId,
	double avgTemperature) {}
