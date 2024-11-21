-- 顧客テーブル (Customer)
CREATE TABLE IF NOT EXISTS customer (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,  -- 顧客ID
    last_name VARCHAR(50) NOT NULL,  -- 姓
    first_name VARCHAR(50) NOT NULL,  -- 名
    phone_number VARCHAR(20),  -- 顧客の連絡先
    email VARCHAR(100) UNIQUE,  -- 顧客のメールアドレス (一意制約)
    address VARCHAR(255),  -- 顧客の住所
    membership_level ENUM('Non-member', 'Member', 'Premium') DEFAULT 'Non-member',  -- 顧客の会員ランク
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 顧客データ作成日
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  -- 顧客データ更新日
);

-- 住所テーブル（顧客が複数の住所を持つ場合）
CREATE TABLE IF NOT EXISTS customer_address (
    address_id INT AUTO_INCREMENT PRIMARY KEY,  -- 住所ID
    customer_id INT,  -- 顧客ID
    address VARCHAR(255) NOT NULL,  -- 住所
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 住所データ作成日
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 住所データ更新日
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id)  -- 顧客テーブルへの外部キー
);

-- 予約テーブル
CREATE TABLE IF NOT EXISTS reservation (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,  -- 予約ID
    customer_id INT,  -- 顧客ID（外部キー）
    reservation_date DATETIME,  -- 予約日時
    number_of_people INT NOT NULL,  -- 予約人数 (必須)
    table_number INT NOT NULL,  -- テーブル番号 (必須)
    status ENUM('Confirmed', 'Pending', 'Cancelled', 'No Show', 'Completed', 'Modified', 'Awaiting Payment', 'Payment Received') NOT NULL,  -- 予約状態
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 予約データ作成日
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 予約データ更新日
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id)  -- 顧客IDの外部キー
);

-- オーダーテーブル
CREATE TABLE IF NOT EXISTS `order` (
    order_id INT AUTO_INCREMENT PRIMARY KEY,   -- Order ID
    customer_id INT NOT NULL,                   -- 顧客ID（外部キー）
    order_date DATETIME NOT NULL,               -- 注文日時
    total_amount DECIMAL(10, 2) NOT NULL,       -- 注文総額
    payment_method VARCHAR(50) NOT NULL,        -- 支払い方法 (e.g., Credit Card, Cash)
    payment_status ENUM('UNPAID', 'WAITING', 'PAID', 'REFUND_PENDING', 'REFUNDED') NOT NULL,  -- 支払いステータス
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 注文作成日時
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 更新日時
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id)  --顧客テーブルへの外部キー
);

-- メニューテーブル
CREATE TABLE IF NOT EXISTS menu (
    menu_id VARCHAR(100) NOT NULL PRIMARY KEY,  -- メニューID (主キー)
    price DECIMAL(10,2) NOT NULL,  -- 価格 (NULL不可)
    category ENUM('Appetizer', 'Main Course', 'Dessert', 'Beverage') NOT NULL,  -- カテゴリ (ENUM化)
    description TEXT,  -- 料理の説明
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 作成日時
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  -- 更新日時
);

-- 注文詳細テーブル
CREATE TABLE IF NOT EXISTS order_item (
    order_item_id INT AUTO_INCREMENT PRIMARY KEY,  -- 注文詳細ID
    order_id INT,  -- 注文ID（外部キー）
    menu_id INT,  -- メニューID（外部キー）
    unit_price DECIMAL(10,2),  -- 単価
    FOREIGN KEY (order_id) REFERENCES `order`(order_id),  -- 外部キー: 注文テーブル
    FOREIGN KEY (menu_id) REFERENCES menu(menu_id)  -- 外部キー: メニューテーブル
);

-- 社員テーブル
CREATE TABLE IF NOT EXISTS employee (
    employee_id INT AUTO_INCREMENT PRIMARY KEY, -- 従業員ID
    name VARCHAR(100) NOT NULL,                 -- 名前
    role VARCHAR(50),                           -- 役職
    phone_number VARCHAR(20),                   -- 連絡先
    hire_date DATE,                             -- 入社日
    salary DECIMAL(10, 2),                      -- 給与
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 作成日時
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 更新日時
);

-- 支払いテーブル
CREATE TABLE IF NOT EXISTS payment (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,  -- 支払いID
    order_id INT,                               -- 関連する注文ID
    amount DECIMAL(10, 2),                      -- 支払い金額
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 支払い日時
    payment_method VARCHAR(50),                -- 支払い方法（例: クレジットカード、現金）
    status ENUM('PENDING', 'COMPLETED', 'FAILED', 'REFUNDED') NOT NULL,  -- 支払い状態
    FOREIGN KEY (order_id) REFERENCES `order`(order_id) -- 注文テーブルへの外部キー
);

-- フィードバックテーブル
CREATE TABLE IF NOT EXISTS feedback (
    feedback_id INT AUTO_INCREMENT PRIMARY KEY, -- フィードバックID
    customer_id INT,                            -- 顧客ID
    rating INT CHECK (rating BETWEEN 1 AND 5),  -- 評価スコア（1～5限定）
    comment TEXT,                               -- コメント
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 作成日時
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id) -- 顧客テーブルへの外部キー
);
