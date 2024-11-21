package com.example.domain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.model.Customer;

@Mapper
public interface CustomerMapper {
	
	List<Customer> findAll();
	Customer findById(int id);
	int insert(Customer customer);
	int update(Customer customer);
	int deleteById(int id);
}
