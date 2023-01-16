package si.um.feri.measurements.dao;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import si.um.feri.measurements.dto.Measurement;
import si.um.feri.measurements.dto.Product;
import si.um.feri.measurements.dto.post.PostMeasurement;
import si.um.feri.measurements.rest.MeasurementHistoryController;
import si.um.feri.measurements.rest.MeasurementPostController;
import si.um.feri.measurements.rest.ProductController;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
//@ActiveProfiles("test")
public class RestScenarioTest {

    @Autowired
    ProductController productRest;

    @Autowired
    MeasurementPostController measurememtPostRest;

    @Autowired
    MeasurementHistoryController measurementHistoryRest;

    Product newProduct=null;

    @BeforeEach
    void createProduct() {
        newProduct=productRest.postProduct(
                new Product(0,"Product",22.0,-23.5)
        ).getBody();
    }

    @Test
    void checkProductExistence() {
        Product fromServer=productRest.getProductById(newProduct.id()).getBody();
        assertEquals(fromServer.name(),newProduct.name());
        assertEquals(-23.5, fromServer.minMeasure(),0.0001);
    }

    @Test
    void newOKMeasurement() {
        String result=
                measurememtPostRest.postProductMeasurement(
                        new PostMeasurement(newProduct.id(),5.5)
                ).getBody().result();
        assertEquals("ok",result);
    }

    @Test
    void newMeasurementForFakeProduct() {
        Object res=measurememtPostRest.postProductMeasurement(
                new PostMeasurement(1111,5.5)
        ).getBody();
        assertInstanceOf(String.class,res);
        assertEquals("product-not-found",res.toString());
    }

    @Test
    void newNotOKMeasurement() {
        String result=
                measurememtPostRest.postProductMeasurement(
                        new PostMeasurement(newProduct.id(),-23.6)
                ).getBody().result();
        assertEquals("not_ok",result);
    }

    @Test
    void measurementHistory() {
        measurememtPostRest.postProductMeasurement(new PostMeasurement(newProduct.id(),1));
        measurememtPostRest.postProductMeasurement(new PostMeasurement(newProduct.id(),1.4));
        measurememtPostRest.postProductMeasurement(new PostMeasurement(newProduct.id(),0.8));

        List<Measurement> history=new ArrayList<>();
        measurementHistoryRest.getHistory().forEach(m->history.add(m));

        assertEquals(3,history.size());
    }

}
