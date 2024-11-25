package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.common.ApiErrorResponse;
import com.example.domain.customer.exception.CustomerDeleteException;
import com.example.domain.customer.exception.CustomerInsertException;
import com.example.domain.customer.exception.CustomerNotFoundException;
import com.example.domain.customer.exception.CustomerServiceException;
import com.example.domain.customer.exception.CustomerUpdateException;

import jakarta.servlet.http.HttpServletRequest;


@RestControllerAdvice
public class CustomerExceptionHandler {

	/**
	 * 顧客が見つからない時の例外ハンドラーです。<p>
	 * 
	 * @param ex {@link CustomerNotFoundException} 顧客が見つからない場合にスローされる例外
	 * @param request {@link HttpServletRequest} リクエスト情報
	 * @return {@link ApiErrorResponse}HttpStatusとメッセージ、URI、タイムスタンプを含むApiErrorResponse
	 */
	@ExceptionHandler(CustomerNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrorResponse handleCustomerNotFoundException(CustomerNotFoundException ex, HttpServletRequest request) {
		return new ApiErrorResponse(HttpStatus.NOT_FOUND.value(),
				"Customer Not Found",
				ex.getMessage(),
				request.getRequestURI(),
				System.currentTimeMillis());
	}

	/**
	 *顧客データの作成に失敗した時の例外ハンドラーです。<p>
	 *
	 * @param ex {@link CustomerInsertException} 顧客データの作成に失敗した場合にスローされる例外
	 * @param request {@link HttpServletRequest} リクエスト情報
	 * @return {@link ApiErrorResponse} HttpStatusとメッセージ、URI、時間を含むApiErrorResponse
	 */
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

	/**
	 * 顧客データの削除に失敗した時の例外ハンドラーです。<p>
	 * @param ex {@link CustomerDeleteException} スローされる例外
	 * @param req {@link HttpServletRequest} リクエスト情報
	 * @return {@link ApiErrorResponse} HttpStatusとメッセージ、URI,時間を含むApiErrorResponse
	 */
	@ExceptionHandler(CustomerDeleteException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrorResponse handleCustomerDeleteExecption(CustomerDeleteException ex, HttpServletRequest req) {
		return new ApiErrorResponse(
				HttpStatus.BAD_REQUEST.value(),
				"顧客データの削除に失敗しました。",
				ex.getMessage(),
				req.getRequestURI(),
				System.currentTimeMillis());
	}

	/**
	 * 顧客データの更新に失敗したときの例外ハンドラーです。<p>
	 * @param ex {@link CustomerUpdateException} スローされる例外
	 * @param req {@link HttpServletRequest} リクエスト情報
	 * @return {@link ApiErrorResponse} HttpStatusとメッセージ、URI,時間を含むApiErrorResponse
	 */
	@ExceptionHandler(CustomerUpdateException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrorResponse handleCustomerUpdateException(CustomerUpdateException ex, HttpServletRequest req) {
		return new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(),
				"顧客情報の更新に失敗しました。",
				ex.getMessage(),
				req.getRequestURI(),
				System.currentTimeMillis());
	}

	/**
	 * 顧客に関するサービスで予期しないエラーが発生した場合の例外ハンドラーです。<p>
	 * 
	 * @param ex {@link CustomerServiceException} 顧客サービスで発生した予期せぬエラー
	 * @paramreq {@link HttpServletRequest} リクエスト情報
	 * @return {@link ApiErrorResponse} HttpStatusとメッセージ、URI,時間を含むApiErrorResponse
	 */
	@ExceptionHandler(CustomerServiceException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiErrorResponse handleCustomerServiceException(CustomerServiceException ex, HttpServletRequest req) {
		return new ApiErrorResponse(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"サーバーで予期せぬエラーが発生しました。",
				ex.getMessage(),
				req.getRequestURI(),
				System.currentTimeMillis());
	}

}
