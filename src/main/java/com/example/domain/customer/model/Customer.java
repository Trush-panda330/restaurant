package com.example.domain.customer.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Customer {
	
	private int customerId;
	private String lastName;
	private String firstName;
	private String phoneNumber;
	private String email;
	private String address;
	private MembershipLevel membershipLevel;
	private LocalDateTime createdAt;
	private LocalDateTime updateAt;
}
