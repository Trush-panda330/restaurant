package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.customer.exception.CustomerDeleteException;
import com.example.domain.customer.model.Customer;
import com.example.domain.customer.response.CustomerResponse;
import com.example.service.CustomerService;

import lombok.RequiredArgsConstructor;


/**
 * 顧客情報に関する操作を提供するcontrollerクラスです。<P>
 * 顧客情報の取得、登録、更新、削除のエンドポイントを提供します。
 */
@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
	
	private final CustomerService service;
	
	/**
	 * 全ての顧客情報を取得します。<p>
	 * 
	 * @return 全顧客情報のリスト
	 */
	@GetMapping
	public ResponseEntity<List<CustomerResponse>> getAllCustomer() {
		List<CustomerResponse> customers = service.findAll();
		return ResponseEntity.ok(customers);
	}
	
	/**
	 * 指定したIDの顧客情報を取得します。
	 * 
	 * @param id 顧客ID
	 * @return 指定したIDの顧客情報
	 */
	@GetMapping("/{id}")
	public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable int id) {
		CustomerResponse customer = service.findById(id);
		return ResponseEntity.ok(customer);
	}
	
	/**
	 * 
	 * 新しい顧客情報を登録します。
	 * 
	 * @param customer 登録する顧客情報
	 * @return 登録成功時に201 Createdステータスを返却
	 */
	@PostMapping
	public ResponseEntity<Void> createCustomer(@RequestBody Customer customer) {
		boolean isInserted = service.insert(customer);
		if(isInserted) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	/**
	 * 顧客情報を更新します。
	 * 
	 * @param id 更新する顧客ID
	 * @param customer 更新する顧客情報
	 * @return 更新成功時に204 No Contentステータスを返却
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
		customer.setCustomerId(id);
		boolean isUpdated = service.update(customer);
		if(isUpdated) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	
	/**
	 * 指定したIＤの顧客情報を削除します。
	 * @param id 削除したい顧客のＩＤ
	 * @return 削除成功時に204 No Contentステータスを返却
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable int id) {
		try {
			service.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch (CustomerDeleteException e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	

}
