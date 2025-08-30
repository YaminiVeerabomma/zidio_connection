CREATE TABLE subscription_plans (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    description TEXT,
    duration_in_days INT NOT NULL,
    rozorpayorder_id VARCHAR(255),
    rozorpay_payment_id VARCHAR(255)
);
