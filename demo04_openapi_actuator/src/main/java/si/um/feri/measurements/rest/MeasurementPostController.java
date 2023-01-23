package si.um.feri.measurements.rest;

import java.util.Optional;
import java.util.logging.Logger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.um.feri.measurements.dao.MeasurementRepository;
import si.um.feri.measurements.dao.ProductRepository;
import si.um.feri.measurements.dto.post.PostMeasurement;
import si.um.feri.measurements.dto.post.PostMeasurementResponse;
import si.um.feri.measurements.vao.Measurement;
import si.um.feri.measurements.vao.Product;

@CrossOrigin
@RestController
public class MeasurementPostController {

	private static final Logger log = Logger.getLogger(MeasurementPostController.class.toString());

	@Autowired
	private MeasurementRepository dao;

	@Autowired
	private ProductRepository productDao;

	@Operation(summary = "Adds a new measurement")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returns added measurement",
					content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Measurement.class))}),
			@ApiResponse(responseCode = "403", description = "Return error because measurement data is invalid",
					content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
	})
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	@PostMapping("/product_measurement")
	public ResponseEntity<PostMeasurementResponse> postProductMeasurement(@RequestBody PostMeasurement m) {
		//validate
		Optional<Product> val=productDao.findById(m.id());
		if (val.isEmpty()) {
			log.info(()->"/product_measurement sent: "+m+"; Product not found!");
			return new ResponseEntity("product-not-found",HttpStatus.NOT_ACCEPTABLE);
		}
		Product product=val.get();

		Measurement vao=new Measurement(m,product);
		boolean ok=true;

		//action?
		if (m.avgTemperature() < product.getMinMeasure()) {
			log.info(()->"/product_measurement sent: "+m+"; product: "+product+"; ACTION NEEDED-lower");
			ok=false;
		}
		if (m.avgTemperature() > product.getMaxMeasure()) {
			log.info(()->"/product_measurement sent: "+m+"; product: "+product+"; ACTION NEEDED-higher");
			ok=false;
		}

		//save
		vao.setOk(ok);
		dao.save(vao);

		//response
	    return ResponseEntity.ok(new PostMeasurementResponse(ok?"ok":"not_ok"));
	}
	
}