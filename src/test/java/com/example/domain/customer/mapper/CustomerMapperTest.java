package com.example.domain.customer.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.domain.customer.model.Customer;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 実際のデータベースを使用
class CustomerMapperTest {

	@Autowired
	private CustomerMapper customerMapper;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// テスト用データの挿入
	@BeforeEach
	void setUp() {
		// テストデータを挿入（AUTO_INCREMENTによりIDは自動設定）
		jdbcTemplate.update(
				"INSERT INTO customer (last_name, first_name, phone_number, email, address, membership_level) " +
						"VALUES (?, ?, ?, ?, ?, ?)",
				"Yamada", "Taro", "08012345678", "taro@example.com", "Tokyo", "Member");
	}

	// findByIdメソッドのテスト
	@Test
	void testFindById() {
		// 最後に挿入された顧客のIDを取得
		Integer insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

		// findByIdメソッドでIDを指定して顧客情報を取得
		Customer customer = customerMapper.findById(insertedId);

		// 取得した顧客がnullでないことを確認
		assertNotNull(customer, "Customer should not be null");

		// 顧客情報が正しいかを確認
		assertEquals("Yamada", customer.getLastName());
		assertEquals("Taro", customer.getFirstName());
		assertEquals("08012345678", customer.getPhoneNumber());
		assertEquals("taro@example.com", customer.getEmail());
		assertEquals("Tokyo", customer.getAddress());
		assertEquals("MEMBER", customer.getMembershipLevel().toString());
	}

	// findAllメソッドのテスト
	@Test
	void testFindAll() {
		// findAllメソッドで全顧客を取得
		List<Customer> customers = customerMapper.findAll();

		// 顧客が1件以上存在することを確認
		assertNotNull(customers);
		assertTrue(customers.size() > 0, "There should be at least one customer");
	}

	// insertメソッドのテスト
	@Test
	void testInsert() {
		// 新規顧客データの作成
		Customer newCustomer = new Customer();
		newCustomer.setLastName("Sato");
		newCustomer.setFirstName("Jiro");
		newCustomer.setPhoneNumber("09098765432");
		newCustomer.setEmail("jiro@example.com");
		newCustomer.setAddress("Osaka");
		newCustomer.setMembershipLevel("Premium");

		// insertメソッドで顧客を挿入
		int result = customerMapper.insert(newCustomer);

		// 挿入が成功したことを確認
		assertEquals(1, result, "Insert should return 1 for successful insertion");

		// 挿入後に新規作成した顧客にIDが設定されていることを確認
		assertNotNull(newCustomer.getCustomerId(), "Customer ID should not be null after insert");

		// 顧客IDが正しく設定されたか確認
		Integer customerId = newCustomer.getCustomerId();
		assertNotNull(customerId, "Customer ID should not be null");

		// 顧客情報をIDで再取得
		Customer insertedCustomer = customerMapper.findById(customerId);

		// 顧客情報が正しいことを確認
		assertNotNull(insertedCustomer, "Inserted customer should not be null");
		assertEquals("Sato", insertedCustomer.getLastName());
		assertEquals("Jiro", insertedCustomer.getFirstName());
	}

	// updateメソッドのテスト
	@Test
	void testUpdate() {
		// 最後に挿入された顧客のIDを取得
		Integer insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

		// findByIdメソッドで顧客を取得
		Customer customer = customerMapper.findById(insertedId);

		// 顧客情報を変更
		customer.setPhoneNumber("08011223344");
		customer.setEmail("updated@example.com");

		// updateメソッドで顧客情報を更新
		int result = customerMapper.update(customer);

		// 更新が成功したことを確認
		assertEquals(1, result, "Update should return 1 for successful update");

		// 更新後の顧客情報を再取得して確認
		Customer updatedCustomer = customerMapper.findById(insertedId);
		assertEquals("08011223344", updatedCustomer.getPhoneNumber());
		assertEquals("updated@example.com", updatedCustomer.getEmail());
	}

	// deleteメソッドのテスト
	@Test
	void testDeleteById() {
		// 最後に挿入された顧客のIDを取得
		Integer insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

		// deleteByIdメソッドで顧客を削除
		int result = customerMapper.deleteById(insertedId);

		// 削除が成功したことを確認
		assertEquals(1, result, "Delete should return 1 for successful deletion");

		// 削除後にfindByIdで顧客を取得し、nullが返されることを確認
		Customer deletedCustomer = customerMapper.findById(insertedId);
		assertNull(deletedCustomer, "Customer should be null after deletion");
	}
}
