package com.example.domain.service;

import java.util.List;

import com.example.domain.model.Customer;
import com.example.domain.response.CustomerResponse;

public interface CustomerService {
    List<CustomerResponse> findAll();
    CustomerResponse findById(int id);
    boolean insert(Customer customer);
    boolean update(Customer customer);
    boolean deleteById(int id);
}
