package si.um.feri.sb101.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import si.um.feri.sb101.vao.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
	
}