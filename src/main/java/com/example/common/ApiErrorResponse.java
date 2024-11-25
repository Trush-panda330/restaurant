package com.example.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiErrorResponse {
	private int status;
	private String error;
	private String message;
	private String path;
	private long timestamp;

	// timestampを動的に設定するカスタムコンストラクタ
	public ApiErrorResponse(int status, String error, String message, String path) {
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
		this.timestamp = System.currentTimeMillis();
	}
	
	// toString() のカスタマイズ
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedTimestamp = sdf.format(new Date(this.timestamp));
		
		return "ApiErrorResponse{" +
        "status=" + status +
        ", error='" + error + '\'' +
        ", message='" + message + '\'' +
        ", path='" + path + '\'' +
        ", timestamp=" + formattedTimestamp +
        '}';
	}
}
