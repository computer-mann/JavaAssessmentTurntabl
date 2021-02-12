import CustomExceptions.ProductAlreadyRegisteredException;
import Interfaces.ProductPricingService;
import Models.Futures;
import Models.Stocks;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MontrealTradedProductsTests {

    @Mock
    ProductPricingService service;


    MontrealTradedProducts tradedProducts;
    Stocks stocksAdded;
    Futures futuresAdded;



    @Before
    public void setUp(){
            tradedProducts=new MontrealTradedProducts();
            stocksAdded=new Stocks(service,"0001","AA","City");
            futuresAdded=new Futures(service,"0002","Diamonds","Dmd",5,2021);


            ///this is my mock data
        when(service.price("TESLA","TSLA")).thenReturn(420.69);
        when(service.price("GOOGLE","GOOGL",3,2021)).thenReturn(652.0);
        when(service.price("AA","City")).thenReturn(389.0);
        when(service.price("Diamonds","Dmd",5,2021)).thenReturn(451.0);
        try{
            tradedProducts.addNewProduct(futuresAdded);
            tradedProducts.addNewProduct(stocksAdded);
        }catch (ProductAlreadyRegisteredException e){
            System.out.println(e.getMessage());
        }

    }



    @Test(expected = ProductAlreadyRegisteredException.class)
    public void addNewProductTests() throws ProductAlreadyRegisteredException{
        tradedProducts.addNewProduct(new Futures(service,"0002","Diamonds","Dmd",5,2021));
    }

    @Test
    public void addNewNonExistentProductTests() throws ProductAlreadyRegisteredException{
        tradedProducts.addNewProduct(new Stocks(service,"0236","TESLA","TSLA"));
    }

    @Test
    public void notRegisteredtradeTests(){
        var inTest=tradedProducts;
        tradedProducts.trade(new Stocks(service,"0028","TESLA","TSLA"),69);
        assertEquals(inTest,tradedProducts);
    }

    @Test
    public void alreadyRegisteredTradeTest(){
        var inTest=tradedProducts;
        tradedProducts.trade(new Stocks(service,"0001","AA","City"),69);
        assertEquals(inTest,tradedProducts);
    }

    @Test
    public void totalTradeQuantityForDayTest(){
        tradedProducts.trade(stocksAdded,100);
        tradedProducts.trade(futuresAdded,69);
        assertEquals(169,tradedProducts.totalTradeQuantityForDay());
    }

    @Test
    public void totalValueOfDaysTradedProductsTest() throws ProductAlreadyRegisteredException{
        Futures f=new Futures(service,"8888","GOOGLE",
                "GOOGL",3,2021);
        Stocks s=new Stocks(service,"7536","TSLA","TESLA");
   tradedProducts.addNewProduct(f);
   tradedProducts.addNewProduct(s);
   tradedProducts.trade(f,19);
   tradedProducts.trade(futuresAdded,20);
   tradedProducts.trade(s,30);
   tradedProducts.trade(stocksAdded,10);
   double expected=(19*652)+(20*451)+(30*420.69)+(10*389);


   assertEquals(expected,tradedProducts.totalValueOfDaysTradedProducts(),0.2);

    }
}
