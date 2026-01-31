package com.gatmane.Service;

import com.gatmane.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long id,Customer customer) throws Exception;
    void deleteCustomer(Long id) throws Exception;
    Customer getCustomer(Long id) throws Exception;
    List<Customer>getAllCustomer();
    List<Customer>searchCustomer(String keyword);
}
