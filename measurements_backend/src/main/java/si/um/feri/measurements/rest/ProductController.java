package si.um.feri.measurements.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import si.um.feri.measurements.dao.ProductRepository;
import si.um.feri.measurements.vao.Product;

@CrossOrigin
@RestController
public class ProductController {

	private static final Logger log = Logger.getLogger(ProductController.class.toString());

	@Autowired
	private ProductRepository dao;

	@GetMapping("/products")
	public @ResponseBody Iterable<si.um.feri.measurements.dto.Product> getAllProducts() {
		List<si.um.feri.measurements.dto.Product> ret=new ArrayList<>();
		dao.findAll().forEach(p -> ret.add(p.toDto()));
		return ret;
	}
	
	@GetMapping("/products/{id}")
	public @ResponseBody ResponseEntity<si.um.feri.measurements.dto.Product> getProductById(@PathVariable("id") int id) {
		//validate
		Optional<Product> val=dao.findById(id);
		if (val.isEmpty()) {
			log.info(()->"/products/"+id+" ; Product not found!");
			return new ResponseEntity("product-not-found",HttpStatus.NOT_ACCEPTABLE);
		}
		return ResponseEntity.ok(val.get().toDto());
	}
	
	@PostMapping("/products")
	public ResponseEntity<si.um.feri.measurements.dto.Product> postProduct(@RequestBody si.um.feri.measurements.dto.Product pc) {
		Product vao=dao.save(new Product(pc));
	    return ResponseEntity.ok(vao.toDto());
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<si.um.feri.measurements.dto.Product> putProduct(@PathVariable("id") int id, @RequestBody si.um.feri.measurements.dto.Product v) {
		//validate
		Optional<Product> val=dao.findById(id);
		if (val.isEmpty()) {
			log.info("/products/"+id+" ; Product not found!");
			return new ResponseEntity("product-not-found",HttpStatus.NOT_ACCEPTABLE);
		}
				
		Product vao=val.get();
		vao.updateFrom(v);
		vao=dao.save(vao);
	    return ResponseEntity.ok(vao.toDto());
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") int id) {
		//validate
		Optional<Product> val=dao.findById(id);
		if (val.isEmpty()) {
			log.info("/products/"+id+" ; Product not found!");
			return new ResponseEntity("product-not-found",HttpStatus.NOT_ACCEPTABLE);
		}
		Product vao=val.get();
		dao.delete(vao);
	    return ResponseEntity.ok("deleted");
	}

}