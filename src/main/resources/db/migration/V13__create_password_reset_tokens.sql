CREATE TABLE password_reset_tokens (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    token VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL,
    expiry_date TIMESTAMP NOT NULL,
    CONSTRAINT fk_reset_user FOREIGN KEY (email) REFERENCES users(email)
);

CREATE INDEX idx_token ON password_reset_tokens(token);
