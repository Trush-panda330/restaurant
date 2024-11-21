package com.example.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.domain.mapper.CustomerMapper;
import com.example.domain.model.Customer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerMapper mapper;

    @Override
    public List<Customer> findAll() {
        return mapper.findAll();
    }

    @Override
    public Optional<Customer> findById(int id) {
        return Optional.ofNullable(mapper.findById(id));
    }

    @Override
    public boolean insert(Customer customer) {
        return mapper.insert(customer) > 0;
    }

    @Override
    public boolean update(Customer customer) {
        return mapper.update(customer) > 0;
    }

    @Override
    public boolean deleteById(int id) {
        return mapper.deleteById(id) > 0;
    }
}
