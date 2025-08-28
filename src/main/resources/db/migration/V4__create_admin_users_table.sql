-- V4__create_admin_users_table.sql

CREATE TABLE admin_users (
    id BIGINT NOT NULL, -- shared with users.id
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    role VARCHAR(50),
    active BOOLEAN DEFAULT TRUE,
    blocked BOOLEAN DEFAULT FALSE,

    PRIMARY KEY (id),
    CONSTRAINT fk_admin_user FOREIGN KEY (id) REFERENCES users(id) ON DELETE CASCADE
);
