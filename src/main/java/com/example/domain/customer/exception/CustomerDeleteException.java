package com.example.domain.customer.exception;

/**
 * 顧客削除に関する例外クラス。
 * 顧客削除に失敗した場合にスローされます。
 */
public class CustomerDeleteException extends RuntimeException {

    // コンストラクタ1：エラーメッセージのみを受け取る
    public CustomerDeleteException(String message) {
        super(message);
    }

    // コンストラクタ2：エラーメッセージと例外を受け取る
    public CustomerDeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}
