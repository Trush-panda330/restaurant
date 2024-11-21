package com.example.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.domain.exception.CustomerNotFoundException;
import com.example.domain.exception.CustomerServiceException;
import com.example.domain.model.Customer;
import com.example.domain.repository.CustomerRepository;
import com.example.domain.response.CustomerResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<CustomerResponse> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponse findById(int id) {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        if (customerOpt.isEmpty()) {
            throw new CustomerNotFoundException("Customer with ID " + id + " not found.");
        }
        return convertToResponse(customerOpt.get());
    }

    @Override
    public boolean insert(Customer customer) {
        boolean isInserted = customerRepository.insert(customer);
        if (!isInserted) {
            throw new CustomerServiceException("Failed to insert customer.");
        }
        return isInserted;
    }

    @Override
    public boolean update(Customer customer) {
        boolean isUpdated = customerRepository.update(customer);
        if (!isUpdated) {
            throw new CustomerServiceException("Failed to update customer.");
        }
        return isUpdated;
    }

    @Override
    public boolean deleteById(int id) {
        boolean isDeleted = customerRepository.deleteById(id);
        if (!isDeleted) {
            throw new CustomerServiceException("Failed to delete customer with ID " + id);
        }
        return isDeleted;
    }

    private CustomerResponse convertToResponse(Customer customer) {
        return new CustomerResponse(
            customer.getCustomerId(),
            customer.getLastName(),
            customer.getFirstName(),
            customer.getPhoneNumber(),
            customer.getEmail(),
            customer.getAddress(),
            customer.getMembershipLevel().getValue()  
        );
    }
}
