package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.common.ApiErrorResponse;
import com.example.domain.customer.exception.CustomerInsertException;
import com.example.domain.customer.exception.CustomerNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class CustomerExceptionHandler {

	@ExceptionHandler(CustomerNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrorResponse handleCustomerNotFoundException(CustomerNotFoundException ex, HttpServletRequest request) {
		return new ApiErrorResponse(HttpStatus.NOT_FOUND.value(), 
				"Customer Not Found", 
				ex.getMessage(),
				request.getRequestURI(), 
				System.currentTimeMillis());
	}

	@ExceptionHandler(CustomerInsertException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrorResponse handleCustomerCreateException(CustomerInsertException ex, HttpServletRequest request) {
		return new ApiErrorResponse(
				HttpStatus.BAD_REQUEST.value(),
				"顧客データの作成に失敗しました。",
				ex.getMessage(),
				request.getRequestURI(),
				System.currentTimeMillis());
	}

}
