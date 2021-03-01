import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MontrealTradedProductsImpl implements MontrealTradedProducts{

    private List<Product> registeredProducts = new ArrayList<>();
    private HashMap<Product, Integer> tradedProducts = new HashMap<Product, Integer>();

    @Override
    public void addNewProduct(Product product) throws ProductAlreadyRegisteredException {
        if ( registeredProducts.contains(product) ){
            throw new ProductAlreadyRegisteredException("This product is already registered");
        } else{
            registeredProducts.add(product);
        }
    }

    @Override
    public void trade(Product product, int quantity) {
        if ( registeredProducts.contains(product) ) {
            int newQuant = tradedProducts.get(product) != null ? tradedProducts.get(product) + quantity : quantity;
            tradedProducts.put(product, newQuant);
        } else {
            tradedProducts.put(product, 0);
        }
    }

    @Override
    public int totalTradeQuantityForDay() {
        // return tradedProducts.values().stream().reduce(0, (x,y)->x +y);
        return tradedProducts.values().stream().reduce(0, Integer::sum);
    }

    @Override
    public double totalValueOfDaysTradedProducts() {
        double totalPrice = 0.0;
        for (Product prod: tradedProducts.keySet()){
            totalPrice += prod.price() * tradedProducts.get(prod);
        }
        return totalPrice;
    }

    public int getLengthOfregisteredProducts(){
        return registeredProducts.size();
    }
}
