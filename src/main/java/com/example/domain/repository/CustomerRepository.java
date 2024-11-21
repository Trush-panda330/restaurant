package com.example.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.domain.mapper.CustomerMapper;
import com.example.domain.model.Customer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class CustomerRepository {
	
	private final CustomerMapper mapper;
	
	public List<Customer> findAll() {
		return mapper.findAll();
	}
	
	public Customer findById(int id) {
		return mapper.findById(id);
	}
	
	public boolean insert(Customer customer) {
		return mapper.insert(customer) > 0;
	}
	
	public boolean update(Customer customer) {
		return mapper.update(customer) > 0;
	}

	public boolean deleteById(int id) {
		return mapper.deleteById(id) > 0;
	}
}
