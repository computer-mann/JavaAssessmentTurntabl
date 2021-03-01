package Models;

import java.util.Objects;

public abstract class Products {
    public String productId;
    public double currentValue;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Products)) return false;
        Products products = (Products) o;
        return productId.equals(products.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}
