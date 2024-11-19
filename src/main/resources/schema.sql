-- 顧客テーブル (Customer)
CREATE TABLE IF NOT EXISTS customer (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,  -- 顧客ID
    last_name VARCHAR(50) NOT NULL,  -- 姓
    first_name VARCHAR(50) NOT NULL,  -- 名
    phone_number VARCHAR(20),  -- 顧客の連絡先
    email VARCHAR(100),  -- 顧客のメールアドレス
    address VARCHAR(255),  -- 顧客の住所
    membership_level ENUM('Non-member', 'Member', 'Premium') DEFAULT 'Non-member',  -- 顧客の会員ランク
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 顧客データ作成日
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  -- 顧客データ更新日
);

CREATE TABLE IF NOT EXISTS reservation (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,  -- 予約ID
    customer_id INT,  -- 顧客ID（外部キー）
    reservation_date DATETIME,  -- 予約日時
    number_of_people INT,  -- 予約人数
    table_number INT,  -- テーブル番号
    --データの整合性やパフォーマンス保守性と拡張性等の観点からenumを採用
    status ENUM('Confirmed', 'Pending', 'Cancelled', 'No Show', 'Completed', 'Modified', 'Awaiting Payment', 'Payment Received') NOT NULL,  -- 予約状態
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 予約データ作成日
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 予約データ更新日
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id)  -- 顧客IDの外部キー
);
