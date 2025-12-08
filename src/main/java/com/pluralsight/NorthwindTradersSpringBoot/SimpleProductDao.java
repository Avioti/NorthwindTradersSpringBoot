package com.pluralsight.NorthwindTradersSpringBoot;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SimpleProductDao implements ProductDao {
    private List<Product> products;


    public SimpleProductDao() {
        products = new ArrayList<>();
        products.add(new Product(1, "Chai", "Beverages", 18.0));
        products.add(new Product(2, "Chang", "Beverages", 19.0));
        products.add(new Product(3, "Aniseed Syrup", "Condiments", 10.0));
    }

    @Override
    public List<Product> add(Product product) {
        products.add(product);
        return products;
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }


}
