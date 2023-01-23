package si.um.feri.measurements.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import si.um.feri.measurements.dto.post.PostMeasurement;
import si.um.feri.measurements.vao.Measurement;
import si.um.feri.measurements.vao.Product;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@ActiveProfiles("test")
public class MeasurementRepositoryTest {

    @Autowired
    private ProductRepository prodDao;

    @Autowired
    private MeasurementRepository dao;

    Product product1;
    Product product2;

    @BeforeEach
    void beforeAll() {
        dao.deleteAll();
        prodDao.deleteAll();
        product1=prodDao.save(new Product(new si.um.feri.measurements.dto.Product(
                0,"Test product 1",25.0,-12.5
        )));
        product2=prodDao.save(new Product(new si.um.feri.measurements.dto.Product(
                0,"Test product 2",25.0,-12.5
        )));
    }

    @Test
    void countByProductTest() {
        assertEquals(0,dao.count());
        assertEquals(0,dao.countByProduct(product1));
        assertEquals(0,dao.countByProduct(product2));

        dao.save(new Measurement(new PostMeasurement(0,15.0),product1));
        dao.save(new Measurement(new PostMeasurement(0,15.0),product1));
        dao.save(new Measurement(new PostMeasurement(0,15.0),product2));
        dao.save(new Measurement(new PostMeasurement(0,15.0),product2));
        dao.save(new Measurement(new PostMeasurement(0,15.0),product2));

        assertEquals(2,dao.countByProduct(product1));
        assertEquals(3,dao.countByProduct(product2));
    }

    @Test
    void findByCreatedGreaterThanTest() {
        Measurement m=new Measurement(new PostMeasurement(0,15.0),product1);
        m.setCreated(LocalDateTime.of(2022,4,1,11,12));
        dao.save(m);

        m=new Measurement(new PostMeasurement(0,15.0),product1);
        m.setCreated(LocalDateTime.of(2022,5,12,12,00));
        dao.save(m);

        m=new Measurement(new PostMeasurement(0,15.0),product1);
        m.setCreated(LocalDateTime.of(2022,6,3,23,59));
        dao.save(m);

        m=new Measurement(new PostMeasurement(0,15.0),product1);
        m.setCreated(LocalDateTime.of(2022,6,2,10,12));
        dao.save(m);

        m=new Measurement(new PostMeasurement(0,15.0),product1);
        m.setCreated(LocalDateTime.of(2022,6,4,0,0));
        dao.save(m);

        assertEquals(5,dao.findByCreatedGreaterThan(LocalDateTime.of(2021,1,1,0,0)).size());
        assertEquals(2,dao.findByCreatedGreaterThan(LocalDateTime.of(2022,6,3,22,0)).size());
        assertEquals(1,dao.findByCreatedGreaterThan(LocalDateTime.of(2022,6,3,23,59)).size());
        assertEquals(0,dao.findByCreatedGreaterThan(LocalDateTime.of(2023,6,3,22,0)).size());
    }

}
