import CustomExceptions.ProductAlreadyRegisteredException;
import Interfaces.IMontrealTradedProducts;
import Models.Products;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MontrealTradedProducts implements IMontrealTradedProducts {

    private final HashMap<Products,Integer> productMap=new HashMap<>();

    @Override
    public void addNewProduct(Products product) throws ProductAlreadyRegisteredException {
        if(productMap.containsKey(product)) throw new ProductAlreadyRegisteredException("Product Already exists");
        productMap.put(product,0);

    }

    @Override
    public void trade(Products product, int quantity) {
            if(!productMap.containsKey(product)) return;
            int alreadyTradedQuantity=productMap.get(product);
            productMap.replace(product,alreadyTradedQuantity+quantity);

    }

    @Override
    public int totalTradeQuantityForDay() {
        return productMap.values().stream().mapToInt(a->a).sum();
    }

    @Override
    public double totalValueOfDaysTradedProducts() {
        double result=0;
        for(Map.Entry<Products, Integer> entry:productMap.entrySet()){
            result = result + (((Products)entry.getKey()).currentValue * entry.getValue());
        }
        return result;
    }
}
