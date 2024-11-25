-- 外部キー制約を無効にする (必要に応じて有効化)
-- SET foreign_key_checks = 0;


-- トランザクションの開始
START TRANSACTION;

-- SET FOREIGN_KEY_CHECKS = 0; -- 外部キー制約を一時的に無効化

DELETE FROM order_item;
DELETE FROM payment;
DELETE FROM orders;
DELETE FROM reservation;
DELETE FROM feedback;
DELETE FROM customer_address;
DELETE FROM customer;
DELETE FROM menu;

-- SET FOREIGN_KEY_CHECKS = 1; -- 外部キー制約を再び有効化

-- 顧客1のデータを挿入
INSERT INTO customer (last_name, first_name, phone_number, email, address, membership_level, created_at, updated_at) 
VALUES ('植田', '拓也', '090-1234-5678', 'ueda.takuya@example.com', '大阪府大阪市阿倍野区', 'Premium', NOW(), NOW());

-- 顧客1のcustomer_idを取得
SET @customer_id1 = LAST_INSERT_ID();

-- 顧客1の住所データを挿入
INSERT INTO customer_address (customer_id, address, created_at, updated_at) 
VALUES (@customer_id1, '大阪府大阪市阿倍野区', NOW(), NOW());

-- 顧客2のデータを挿入
INSERT INTO customer (last_name, first_name, phone_number, email, address, membership_level, created_at, updated_at) 
VALUES ('望月', '薫', '080-2345-6789', 'kaoru@example.com', '埼玉県', 'Member', NOW(), NOW());

-- 顧客2のcustomer_idを取得
SET @customer_id2 = LAST_INSERT_ID();

-- 顧客2の住所データを挿入
INSERT INTO customer_address (customer_id, address, created_at, updated_at) 
VALUES (@customer_id2, '埼玉県', NOW(), NOW());

-- 顧客3のデータを挿入
INSERT INTO customer (last_name, first_name, phone_number, email, address, membership_level, created_at, updated_at) 
VALUES ('松澤', '葵', '070-3456-7890', 'aoi@example.com', '千葉県', 'Non-member', NOW(), NOW());

-- 顧客3のcustomer_idを取得
SET @customer_id3 = LAST_INSERT_ID();

-- 顧客3の住所データを挿入
INSERT INTO customer_address (customer_id, address, created_at, updated_at) 
VALUES (@customer_id3, '千葉県', NOW(), NOW());

-- メニュー (menu) にデータを挿入
INSERT INTO menu (menu_id, price, menu, description) 
VALUES 
(1, 12000, 'Special', 'Menu Special Description'),
(2, 10000, 'Season', 'Menu Season Description'),
(3, 8000, 'Prefix', 'Menu Prefix Description');



-- 予約テーブル (reservation) にデータを挿入
INSERT INTO reservation (customer_id, reservation_date, number_of_people, table_number, status, created_at, updated_at) VALUES
(@customer_id1, '2024-11-25 19:00:00', 2, 1, 'Confirmed', NOW(), NOW()),
(@customer_id2, '2024-11-26 18:30:00', 3, 3, 'Pending', NOW(), NOW()),
(@customer_id3, '2024-11-27 20:00:00', 4, 8, 'Confirmed', NOW(), NOW());

-- 注文テーブル (orders) にデータを挿入
INSERT INTO orders (customer_id, order_date, total_amount, payment_method, payment_status, created_at, updated_at)
VALUES 
(@customer_id1, '2024-11-25 19:00:00', 12000.00, 'Credit Card', 'PAID', NOW(), NOW()),
(@customer_id2, '2024-11-26 18:30:00', 20000.00, 'Cash', 'WAITING', NOW(), NOW()),
(@customer_id3, '2024-11-27 20:00:00', 32000.00, 'Credit Card', 'PAID', NOW(), NOW());

-- 各注文IDを取得
SET @order_id1 = LAST_INSERT_ID();
SET @order_id2 = @order_id1 + 1;
SET @order_id3 = @order_id1 + 2;

-- 注文詳細テーブル (order_item) にデータを挿入
INSERT INTO order_item (order_id, menu_id, unit_price) VALUES
(@order_id1, 3, 6000.00),  -- 顧客1の注文
(@order_id1, 1, 10000.00), -- 顧客1の注文
(@order_id2, 2, 8000.00);  -- 顧客2の注文

-- 支払いテーブル (payment) にデータを挿入
INSERT INTO payment (order_id, amount, payment_date, payment_method, status) VALUES
(@order_id1, 12000.00, NOW(), 'Credit Card', 'COMPLETED'),
(@order_id2, 20000.00, NOW(), 'Cash', 'PENDING'),
(@order_id3, 32000.00, NOW(), 'Credit Card', 'COMPLETED');

-- フィードバックテーブル (feedback) にデータを挿入
INSERT INTO feedback (customer_id, rating, comment, created_at) VALUES
(@customer_id1, 5, 'とても美味しくて、サービスも素晴らしいです。', NOW()),
(@customer_id2, 4, '料理は良かったが、待ち時間が長かったです。', NOW()),
(@customer_id3, 4, '味は普通でしたが、サービスがとても良かった。', NOW());

-- 社員テーブル (employee) にデータを挿入
INSERT INTO employee (name, role, phone_number, hire_date, salary, created_at, updated_at) VALUES
('佐藤健一', 'シェフ', '03-1234-5678', '2020-01-15', 400000, NOW(), NOW()),
('鈴木真美', 'ホールスタッフ', '03-2345-6789', '2021-06-10', 200000, NOW(), NOW()),
('田中一郎', 'マネージャー', '03-3456-7890', '2019-11-22', 400000, NOW(), NOW());


-- トランザクションのコミット
COMMIT;

-- 外部キー制約を有効にする (必要に応じて有効化)
-- SET foreign_key_checks = 1;
