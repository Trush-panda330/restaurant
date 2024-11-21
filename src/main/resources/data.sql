-- 顧客テーブル (Customer) にデータを挿入
INSERT INTO customer (last_name, first_name, phone_number, email, address, membership_level, created_at, updated_at) VALUES
('植田', '拓也', '090-1234-5678', 'ueda.takuya@example.com', '大阪府大阪市阿倍野区', 'Premium', NOW(), NOW()),
('望月', '薫', '080-2345-6789', 'kaoru@example.com', '埼玉県', 'Member', NOW(), NOW()),
('松澤', '葵', '070-3456-7890', 'aoi@example.com', '千葉県', 'Non-member', NOW(), NOW());

-- 顧客住所テーブル (customer_address) にデータを挿入
INSERT INTO customer_address (customer_id, address, created_at, updated_at) VALUES
(1, '大阪府大阪市阿倍野区', NOW(), NOW()),
(2, '埼玉県', NOW(), NOW()),
(3, '千葉県', NOW(), NOW());

-- 予約テーブル (reservation) にデータを挿入
INSERT INTO reservation (customer_id, reservation_date, number_of_people, table_number, status, created_at, updated_at) VALUES
(1, '2024-11-25 19:00:00', 2, 1, 'Confirmed', NOW(), NOW()),
(2, '2024-11-26 18:30:00', 3, 3, 'Pending', NOW(), NOW()),
(3, '2024-11-27 20:00:00', 4, 8, 'Confirmed', NOW(), NOW());

-- メニュー (menu) にデータを挿入
INSERT INTO menu (price, category, description, created_at, updated_at) VALUES
(10000.00, 'Main Course', 'Spécialité', NOW(), NOW()),
(8000.00, 'Main Course', 'Dégustation', NOW(), NOW()),
(6000.00, 'Main Course', 'Préfixe', NOW(), NOW());

-- 注文テーブル (orders) にデータを挿入
INSERT INTO orders (customer_id, order_date, total_amount, payment_method, payment_status, created_at, updated_at) VALUES
(1, '2024-11-25 19:05:00', 12000.00, 'Credit Card', 'PAID', NOW(), NOW()),
(2, '2024-11-26 18:35:00', 20000.00, 'Cash', 'WAITING', NOW(), NOW()),
(3, '2024-11-27 20:10:00', 32000.00, 'Credit Card', 'UNPAID', NOW(), NOW());

-- 注文詳細テーブル (order_item) にデータを挿入
INSERT INTO order_item (order_id, menu_id, unit_price) VALUES
(1, 3, 6000.00),
(1, 3, 6000.00),
(2, 1, 10000.00),
(2, 1, 10000.00),
(3, 2, 8000.00),
(3, 2, 8000.00),
(3, 2, 8000.00),
(3, 2, 8000.00);

-- 支払いテーブル (payment) にデータを挿入
INSERT INTO payment (order_id, amount, payment_date, payment_method, status) VALUES
(1, 12000.00, NOW(), 'Credit Card', 'COMPLETED'),
(2, 20000.00, NOW(), 'Cash', 'PENDING'),
(3, 32000.00, NOW(), 'Credit Card', 'COMPLETED');

-- フィードバックテーブル (feedback) にデータを挿入
INSERT INTO feedback (customer_id, rating, comment, created_at) VALUES
(1, 5, 'とても美味しくて、サービスも素晴らしいです。', NOW()),
(2, 4, '料理は良かったが、待ち時間が長かったです。', NOW()),
(3, 4, '味は普通でしたが、サービスがとても良かった。', NOW());

-- 社員テーブル (employee) にデータを挿入
INSERT INTO employee (name, role, phone_number, hire_date, salary, created_at, updated_at) VALUES
('佐藤健一', 'シェフ', '03-1234-5678', '2020-01-15', 400000, NOW(), NOW()),
('鈴木真美', 'ホールスタッフ', '03-2345-6789', '2021-06-10', 200000, NOW(), NOW()),
('田中一郎', 'マネージャー', '03-3456-7890', '2019-11-22', 400000, NOW(), NOW());
