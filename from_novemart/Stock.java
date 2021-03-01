public class Stock extends Product {
    private String ticker;

    public Stock(String id, String exchange, String ticker, ProductPricingService pps) {
        super(id, exchange, pps);
        this.ticker = ticker;
    }

    public double price(){
        return pps.price(exchange, ticker);
    }
}
