public class Future extends Product{

    private int month;
    private int year;
    private String contractCode;

    public Future(String id, String exchange, int month, int year, String contractCode, ProductPricingService pps){
        super(id, exchange, pps);
        this.month = month;
        this.year = year;
        this.contractCode = contractCode;
    }

    public double price(){
        return pps.price(exchange, contractCode, month, year);
    }
}
