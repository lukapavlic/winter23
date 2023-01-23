package si.um.feri.measurements.rest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import si.um.feri.measurements.dao.MeasurementRepository;
import si.um.feri.measurements.vao.Measurement;

@CrossOrigin
@RestController
public class MeasurementHistoryController {

	@Autowired
	private MeasurementRepository dao;

	@Value("${history.dayslimit}")
	private int envHistoryDayslimit;

	@Operation(summary = "Returns all measurements")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returns added measurement",
					content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Measurement.class))}),
			@ApiResponse(responseCode = "403", description = "Return error because measurement data is invalid",
					content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
	})
	@GetMapping("/history")
	public @ResponseBody Iterable<si.um.feri.measurements.dto.Measurement> getHistory() {
		long history = System.currentTimeMillis() - envHistoryDayslimit * 3_600_000 * 24;
		LocalDateTime historyDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(history),TimeZone.getDefault().toZoneId());
		List<si.um.feri.measurements.dto.Measurement> ret = new ArrayList<>();
		dao.findByCreatedGreaterThan(historyDate).forEach(m->ret.add(m.toDto()));
		return ret;
	}

}