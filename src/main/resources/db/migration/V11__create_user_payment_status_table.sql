CREATE TABLE user_payment_status (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    plan_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    subscription_start DATE NOT NULL,
    subscription_end DATE NOT NULL,
    status VARCHAR(50) NOT NULL,
    transaction_id VARCHAR(100) UNIQUE NOT NULL,

    -- ✅ Use unique constraint names
    CONSTRAINT fk_userpayment_plan FOREIGN KEY (plan_id) REFERENCES subscription_plans(id),
    CONSTRAINT fk_userpayment_user FOREIGN KEY (user_id) REFERENCES users(id)
);
