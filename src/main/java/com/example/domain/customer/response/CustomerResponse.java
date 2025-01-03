package com.example.domain.customer.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerResponse {
	private int customerId;
	private String lastName;
	private String firstName;
	private String phoneNumber;
	private String email;
	private String address;
	private String membershipLevel;
}
