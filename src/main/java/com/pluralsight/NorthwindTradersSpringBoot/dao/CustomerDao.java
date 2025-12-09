package com.pluralsight.NorthwindTradersSpringBoot.dao;

import com.pluralsight.NorthwindTradersSpringBoot.model.Customer;

import java.util.List;

public interface CustomerDao {
    public List<Customer> getAll();
    public List<Customer> getByCompanyName();
    public List<Customer> getByContactName();
    public List<Customer> getByAddress();
    public Customer getByCustomerID();
    public void delete();
    public void add(Customer customer);
}
