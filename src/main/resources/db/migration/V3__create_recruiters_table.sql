-- ==============================================
-- V3__create_recruiters_table.sql
-- ==============================================

CREATE TABLE recruiters (
    id BIGINT NOT NULL,                  -- shared PK with users.id
    name VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(50),

    company_name VARCHAR(255),
    company_description TEXT,
    company_website VARCHAR(255),
    company_address VARCHAR(255),
    company_size VARCHAR(100),

    designation VARCHAR(100),

    CONSTRAINT pk_recruiters PRIMARY KEY (id),
    CONSTRAINT fk_recruiter_user FOREIGN KEY (id) REFERENCES users(id) ON DELETE CASCADE
);
