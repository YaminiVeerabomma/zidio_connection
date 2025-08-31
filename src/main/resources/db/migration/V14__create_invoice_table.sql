-- V14__create_invoice_table.sql
CREATE TABLE invoice (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_email VARCHAR(255) NOT NULL,
    service_type VARCHAR(255) NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    payment_method VARCHAR(100) NOT NULL,
    status VARCHAR(100) NOT NULL,
    purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    invoice_number VARCHAR(100) UNIQUE NOT NULL,
    invoice_download_url VARCHAR(500),
    subscription_plan_id BIGINT,
    CONSTRAINT fk_invoice_subscription_plan
        FOREIGN KEY (subscription_plan_id)
        REFERENCES subscription_plans(id)   -- ✅ plural name
        ON DELETE SET NULL
);
