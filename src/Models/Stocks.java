package Models;

import Interfaces.ProductPricingService;

public class Stocks extends Products{
    private String stockTicker;
    private String exchange;
    private ProductPricingService service;

    public Stocks(ProductPricingService service,String productId,String stockTicker,String exchange){
        currentValue=service.price(exchange,stockTicker);
        this.stockTicker=stockTicker;
        this.exchange=exchange;
        this.productId=productId;
    }

}
