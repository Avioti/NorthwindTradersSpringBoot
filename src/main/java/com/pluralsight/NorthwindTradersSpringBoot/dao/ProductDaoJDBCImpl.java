package com.pluralsight.NorthwindTradersSpringBoot.dao;

import com.pluralsight.NorthwindTradersSpringBoot.model.Customer;
import com.pluralsight.NorthwindTradersSpringBoot.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Component
public class ProductDaoJDBCImpl implements ProductDao{

    private List<Product> products;
    private DataSource dataSource;

    @Autowired
    public ProductDaoJDBCImpl(DataSource dataSource) {
        this.products = new ArrayList<>();
        this.dataSource = dataSource;
    }



    @Override
    public List<Product> add(Product product) {
        String sql = "INSERT INTO Products (ProductName, CategoryID, UnitPrice) VALUES (?,?,?);";
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setInt(2, product.getCategory());
            statement.setDouble(3, product.getPrice());
            statement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> getAllProducts() {
        this.products.clear();
        String sql = "SELECT ProductID, ProductName, CategoryID, UnitPrice FROM Products;";
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rows = statement.executeQuery();
            while(rows.next()){
                this.products.add(new Product(rows.getInt(1), rows.getString(2), rows.getInt(3),rows.getDouble(4)));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return this.products;
    }
}
