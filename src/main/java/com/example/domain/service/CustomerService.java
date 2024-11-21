package com.example.domain.service;

import java.util.List;

import com.example.domain.model.Customer;
import com.example.domain.response.CustomerResponse;

public interface CustomerService {

    /**
     * すべての顧客情報を取得します。
     * 
     * @return 顧客情報のリスト
     */
    List<CustomerResponse> findAll();

    /**
     * 顧客IDで顧客情報を取得します。
     * 
     * @param id 顧客ID
     * @return 顧客情報
     */
    CustomerResponse findById(int id);

    /**
     * 顧客情報を新規登録します。
     * 
     * @param customer 新規登録する顧客情報
     * @return 登録が成功したかどうか
     */
    boolean insert(Customer customer);

    /**
     * 既存の顧客情報を更新します。
     * 
     * @param customer 更新する顧客情報
     * @return 更新が成功したかどうか
     */
    boolean update(Customer customer);

    /**
     * 顧客IDで顧客情報を削除します。
     * 
     * @param id 削除する顧客のID
     * @return 削除が成功したかどうか
     */
    boolean deleteById(int id);
}
