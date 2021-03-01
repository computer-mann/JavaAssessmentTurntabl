import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MontrealTradedProductsImplTest {

    @Mock
    private ProductPricingService pps;
    private MontrealTradedProductsImpl mon;

    @Before
    public void setUp(){
        mon = new MontrealTradedProductsImpl();
        Mockito.when(pps.price("LON", "AAPL")).thenReturn(1300.0);
        //Mockito.when(pps.price("MON", "COD", 11, 2021 )).thenReturn(1500.0);

    }

    @org.junit.Test(expected = ProductAlreadyRegisteredException.class)
    public void addNewProduct() throws ProductAlreadyRegisteredException {
        Product prod = new Stock("001", "LON", "AAPL", pps);
        mon.addNewProduct(prod);
        Product prod1 = new Stock("001", "LON", "AAPL", pps);
        mon.addNewProduct(prod1);
    }

    @Test
    public void addNewProductTest() throws ProductAlreadyRegisteredException{
         Product prod = new Stock("001", "LON", "AAPL", pps);
        mon.addNewProduct(prod);
        assertTrue(mon.getLengthOfregisteredProducts() == 1);
    }

    @Test
    public void totalValueOfDaysTradedProductsTest()throws ProductAlreadyRegisteredException{

        Product prod = new Stock("001", "LON", "AAPL", pps);
        mon.addNewProduct(prod);

        mon.trade(prod, 1);

        assertEquals(1300, mon.totalValueOfDaysTradedProducts(), 0.0001);
    }
}