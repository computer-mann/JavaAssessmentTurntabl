import java.util.Objects;

public abstract class Product {
    protected String id;
    protected String exchange;
    protected ProductPricingService pps;

    public Product( String id, String exchange, ProductPricingService pps){
        this.id = id;
        this.exchange = exchange;
        this.pps = pps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    abstract double price();
}
