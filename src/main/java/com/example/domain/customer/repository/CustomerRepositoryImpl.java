package com.example.domain.customer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.domain.customer.mapper.CustomerMapper;
import com.example.domain.customer.model.Customer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerMapper mapper;

    @Override
    public List<Customer> findAll() {
        return mapper.findAll();
    }
    
    /**
     * 指定されたIDの顧客情報を取得する
     * 
     * @param id 顧客ID
     * @return 顧客情報、存在しない場合はOptional.empty()を返す。
     */
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
