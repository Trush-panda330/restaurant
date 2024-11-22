package com.example.domain.customer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import com.example.domain.customer.model.Customer;

@Mapper
public interface CustomerMapper {
    
    List<Customer> findAll();
    
    Customer findById(int id);
    
    @Options(useGeneratedKeys = true, keyProperty = "customerId")
    int insert(Customer customer);
    
    int update(Customer customer);
    
    int deleteById(int id);
}
