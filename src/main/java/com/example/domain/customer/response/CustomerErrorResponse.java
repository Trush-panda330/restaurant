package com.example.domain.customer.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerErrorResponse {
	private int statusCode; 				//HTTPステータスコード（例：404）
	private String error;						//エラーの種類（例：Not Found）
	private String message; 				// エラーメッセージ（例：顧客IDが見つかりません）
	private long timestamp; //エラー発生時刻


	@Override
	public String toString() {
		return "CustomerErrorResponse{" +
				"statusCode" + statusCode +
				", error='" + error + '\'' +
				", message='" + message + '\'' +
				", timestamp=" + timestamp +
				'}';
	}
}
