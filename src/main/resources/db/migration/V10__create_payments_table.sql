CREATE TABLE payments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    plan_id BIGINT NOT NULL,
    transaction_id VARCHAR(255),
    amount DECIMAL(19,2),
    currency VARCHAR(10),
    payment_status VARCHAR(50),
    payment_type VARCHAR(50),
    payment_date TIMESTAMP,
    
    CONSTRAINT fk_payment_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_payment_plan FOREIGN KEY (plan_id) REFERENCES subscription_plans(id)
);
