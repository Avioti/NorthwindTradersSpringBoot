package com.pluralsight.NorthwindTradersSpringBoot;

import java.util.List;

public interface ProductDao {

    public List<Product> add(Product product);
    public List<Product> getAllProducts();
}
