-- 0. CLEAN EVERYTHING FIRST (Order matters because of Foreign Keys)
-- CASCADE tells Postgres to also clear child tables (customers, seller, orders, etc.)
TRUNCATE TABLE app_user RESTART IDENTITY CASCADE;

-- 1. NOW INSERT USERS
INSERT INTO app_user (username, password) VALUES
                                              ('john_buyer', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.TVuHOnu'),
                                              ('tech_vendor', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.TVuHOnu');

-- 2. INSERT CUSTOMER (Linked to User ID 1)
-- Note: Reusing 'user_id' as the column name since we are using @MapsId
INSERT INTO customers (id, user_name, first_name, second_name, email, phone_number, created_at) VALUES
    (1, 'john_buyer', 'John', 'Doe', 'john@example.com', '1234567890', CURRENT_TIMESTAMP);

-- 3. INSERT SELLER (Linked to User ID 2)
INSERT INTO seller (id, seller_name, email, phone_number, created_at) VALUES
    (2, 'Nexus Electronics', 'sales@nexus.com', '9876543210', CURRENT_TIMESTAMP);


-- 4. INSERT PRODUCTS (Owned by Seller ID 2)
INSERT INTO products (name, description, price, currency, seller_id, created_at) VALUES
                                                                                     ('SuperPhone X', 'Flagship smartphone with 120Hz display', 999.99, 'USD', 2, CURRENT_TIMESTAMP),
                                                                                     ('Silent Keyboard', 'Mechanical keyboard with brown switches', 75.50, 'USD', 2, CURRENT_TIMESTAMP);

-- 5. INSERT ADDRESSES
-- Customer Address
INSERT INTO addresses (address_line, city, state, pincode, customer_id, created_at) VALUES
    ('123 Maple Avenue', 'New York', 'NY', '10001', 1, CURRENT_TIMESTAMP);

-- Seller Address
INSERT INTO addresses (address_line, city, state, pincode, seller_id, created_at) VALUES
    ('Warehouse 4, Industrial Park', 'Austin', 'TX', '73301', 2, CURRENT_TIMESTAMP);

-- 6. INSERT AN ORDER (For Customer 1)
INSERT INTO orders (total_price, currency, payment_method, customer_id, created_at)
VALUES (1075.49, 'USD', 'UPI', 1, CURRENT_TIMESTAMP);

-- 7. MAP PRODUCT TO ORDER (ManyToMany)
-- Maps Product 1 and 2 to Order 1
INSERT INTO orders_product_mapping (order_id, product_id) VALUES
                                                              (1, 1),
                                                              (1, 2);