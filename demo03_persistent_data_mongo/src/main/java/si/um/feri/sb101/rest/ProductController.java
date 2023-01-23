package si.um.feri.sb101.rest;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import si.um.feri.sb101.dao.ProductRepository;
import si.um.feri.sb101.vao.Product;

@RestController
public class ProductController {

	private static final Logger log = Logger.getLogger(ProductController.class.toString());

	@Autowired
	private ProductRepository dao;

	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return dao.findAll();
	}

	@GetMapping("/products/{id}")
	public @ResponseBody ResponseEntity<Product> getProductById(@PathVariable("id") String id) {
		//validate
		Optional<Product> val=dao.findById(id);
		if (val.isEmpty()) {
			log.info(()->"/products/"+id+" ; Product not found!");
			return new ResponseEntity("product-not-found",HttpStatus.NOT_ACCEPTABLE);
		}
		return ResponseEntity.ok(val.get());
	}

	@PostMapping("/products")
	public ResponseEntity<Product> postProduct(@RequestBody Product pc) {
		Product vao=dao.save(new Product(pc.getName(),pc.getMaxMeasure(), pc.getMinMeasure()));
	    return ResponseEntity.ok(vao);
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") String id) {
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