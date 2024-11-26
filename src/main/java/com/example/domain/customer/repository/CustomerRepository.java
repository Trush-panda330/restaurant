package com.example.domain.customer.repository;

import java.util.List;
import java.util.Optional;

import com.example.domain.customer.model.Customer;

public interface CustomerRepository {
    List<Customer> findAll();
    Optional<Customer> findById(int id);
    boolean insert(Customer customer);
    boolean update(Customer customer);
    void deleteCustomerAddressById(int customerId);
    void deleteById(int customerId);
}
