package Models;

import Interfaces.ProductPricingService;

public class Futures extends Products{
    private String exchange;
    private String contractCode;
    private int month;
    private int year;
    private ProductPricingService service;

    public Futures(ProductPricingService service,String productId,String exchange,String contractCode,
                   int month,int year){

        this.exchange=exchange;
        this.contractCode=contractCode;
        this.month=month;
        this.year=year;
        this.productId=productId;
        currentValue=service.price(this.exchange,this.contractCode,month,year);

    }
}
