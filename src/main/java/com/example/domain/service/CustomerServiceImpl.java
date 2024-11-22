package com.example.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.exception.customer.CustomerDeleteException;
import com.example.domain.exception.customer.CustomerInsertException;
import com.example.domain.exception.customer.CustomerNotFoundException;
import com.example.domain.exception.customer.CustomerServiceException;
import com.example.domain.exception.customer.CustomerUpdateException;
import com.example.domain.model.Customer;
import com.example.domain.repository.CustomerRepository;
import com.example.domain.response.CustomerResponse;

import lombok.RequiredArgsConstructor;

/**
 * 顧客情報を管理するサービス実装クラス。
 * このクラスは、顧客データの取得、登録、更新、削除を行うためのビジネスロジックを提供します。
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    /**
     * 全ての顧客情報を取得します。 <p>
     * 顧客情報を{@link  CustomerResponse}のリストとして返却します。
     * 
     * @return 全ての顧客情報リスト({@link CustomerResponse}型)
     */
    @Override
    public List<CustomerResponse> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    /**
     * 指定したIDの顧客情報を取得します。<p>
     * 顧客IDが存在しない場合、{@link CustomerNotFoundException}がスローされます。
     * 
     * @param id 顧客ID
     * @return {@link CustomerResponse} 顧客情報
     * @throws CustomerNotFoundException 指定したIDの顧客情報がない場合
     */
    @Override
    public CustomerResponse findById(int id) {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        if (customerOpt.isEmpty()) {
            throw new CustomerNotFoundException("顧客ID： " + id + " は見つかりませんでした。.");
        }
        return convertToResponse(customerOpt.get());
    }

    /**
     * 新しい顧客情報を登録します。<p>
     * 登録に失敗した場合、{@link CustomerInsertException} がスローされます。
     * 
     * @param customer 登録する顧客情報
     * @return 登録が成功した場合はtrue、登録に失敗場合はfalse
     * @throws CustomerServiceException 登録に失敗した場合
     */
    @Override
    public boolean insert(Customer customer) {
        boolean isInserted = customerRepository.insert(customer);
        if (!isInserted) {
            throw new CustomerInsertException("Failed to insert customer.");
        }
        return isInserted;
    }

    /**
     * 顧客情報を更新します。<p>
     * 更新に失敗した場合、{@link CustomerUpdateException} がスローされます。
     * 
     * @param customer 更新する顧客情報
     * @return 更新が失敗した場合にはtrue、失敗した場合にはfalse
     * @throws CustomerServiceException 更新に失敗した場合
     */
    @Override
    public boolean update(Customer customer) {
        boolean isUpdated = customerRepository.update(customer);
        if (!isUpdated) {
            throw new CustomerUpdateException("Failed to update customer.");
        }
        return isUpdated;
    }

    /**
     * 顧客情報を削除します。<p>
     * 更新に失敗した場合、{@link CustomerDeleteException} がスローされます。
     * 
     * @param id   削除する顧客のID
     * @return 削除が成功した場合はtrue、失敗した場合はfalse
     * @throws CustomerServiceException 削除に失敗した場合
     */
    @Override
    public boolean deleteById(int id) {
        boolean isDeleted = customerRepository.deleteById(id);
        if (!isDeleted) {
            throw new CustomerDeleteException("Failed to delete customer with ID " + id);
        }
        return isDeleted;
    }

    /**
     * {@link Customer}を{@link CustomerResponse}に変換します。
     * 
     * @param customer 変換する顧客情報
     * @return 変換後の{@link CustomerResponse} オブジェクト
     */
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
