package si.um.feri.measurements.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import si.um.feri.measurements.vao.Product;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@ActiveProfiles("test")
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository dao;

    @Autowired
    private MeasurementRepository measDao;

    Product product;

    @BeforeEach
    void beforeEach() {
        measDao.deleteAll();
        dao.deleteAll();
        product=dao.save(new Product(new si.um.feri.measurements.dto.Product(
                0,"Test product",25.0,-12.5
        )));
    }

    @Test
    void addAnotherProductTest() {
        assertEquals(1,dao.count());
        assertEquals("Test product",product.getName());
        assertEquals(25.0,product.getMaxMeasure(),0.0001);
        assertEquals(-12.5,product.getMinMeasure(),0.0001);

        Product p=new Product();
        p.setName("Another product");
        p=dao.save(p);

        assertEquals(2,dao.count());

        Optional<Product> optP=dao.findById(p.getId());
        assertFalse(optP.isEmpty());
        assertNotNull(optP.get());
        p=optP.get();

        assertEquals("Another product",p.getName());
        assertEquals(0,p.getMaxMeasure(),0.0001);
        assertEquals(0,p.getMinMeasure(),0.0001);
    }

    @Test
    void deleteProductTest() {
        assertEquals(1,dao.count());
        dao.deleteById(product.getId());
        assertEquals(0,dao.count());
    }

}
