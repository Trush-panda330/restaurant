package com.example.domain.customer.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CustomerErrorResponse {
	private int statusCode;    //HTTPステータスコード（例：404）
	private String error;		   //エラーの種類（例：Not Found）
 	private String message;  // エラーメッセージ（例：顧客IDが見つかりません）

	@Override
	public String toString() {
		return "CustomerErrorResponse{" +
				"statusCode" + statusCode +
				", error='" + error + '\'' +
				", message='" + message + '\'' +
				'}';
	}

}
