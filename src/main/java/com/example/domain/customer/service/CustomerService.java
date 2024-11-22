package com.example.domain.customer.service;

import java.util.List;

import com.example.domain.customer.model.Customer;
import com.example.domain.customer.response.CustomerResponse;

public interface CustomerService {
    List<CustomerResponse> findAll();
    CustomerResponse findById(int id);
    boolean insert(Customer customer);
    boolean update(Customer customer);
    boolean deleteById(int id);
}
