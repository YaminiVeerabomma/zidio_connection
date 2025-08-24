-- ========================
-- USERS TABLE
-- ========================
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

-- ========================
-- STUDENTS TABLE
-- ========================
CREATE TABLE students (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(20),
    qualification VARCHAR(255),
    gender VARCHAR(20),
    graduation_year DATE,
    skills TEXT,
    experience_level VARCHAR(50),
    resume_url VARCHAR(500),
    github_url VARCHAR(500),
    linkedin_url VARCHAR(500),
    preferred_location VARCHAR(50),
    expected_salary DOUBLE,
    notice_period VARCHAR(50),
    CONSTRAINT fk_student_user FOREIGN KEY (id) REFERENCES users(id) ON DELETE CASCADE
);

-- ========================
-- ADMIN USERS TABLE
-- ========================
CREATE TABLE admin_users (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    role VARCHAR(50),
    active BOOLEAN DEFAULT TRUE,
    blocked BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_admin_user FOREIGN KEY (id) REFERENCES users(id) ON DELETE CASCADE
);

-- ========================
-- RECRUITERS TABLE
-- ========================
CREATE TABLE recruiters (
    id BIGINT NOT NULL,
    name VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(50),
    company_name VARCHAR(255),
    company_description TEXT,
    company_website VARCHAR(255),
    company_address VARCHAR(255),
    company_size VARCHAR(50),
    designation VARCHAR(50),
    PRIMARY KEY (id),
    CONSTRAINT fk_recruiter_user FOREIGN KEY (id) REFERENCES users(id) ON DELETE CASCADE
);

-- ========================
-- JOB POSTS TABLE
-- ========================
CREATE TABLE job_posts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    job_title VARCHAR(255),
    job_description TEXT,
    job_location VARCHAR(255),
    job_type VARCHAR(50),
    company_name VARCHAR(255),
    posted_by_email VARCHAR(255),
    posted_date DATETIME,
    experience_level VARCHAR(50),
    salary_min DECIMAL(10,2),
    salary_max DECIMAL(10,2),
    education VARCHAR(255),
    skills VARCHAR(500),
    is_active BOOLEAN DEFAULT TRUE,
    number_of_vacancies INT
);

-- ========================
-- PASSWORD RESET TOKEN
-- ========================
CREATE TABLE password_reset_token (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    token VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    expiry_date DATETIME
);

-- ========================
-- SUBSCRIPTION PLANS
-- ========================
CREATE TABLE subscription_plans (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    price DOUBLE,
    description TEXT,
    duration_in_days INT,
    razorpay_order_id VARCHAR(255),
    razorpay_payment_id VARCHAR(255)
);

-- ========================
-- INVOICE
-- ========================
CREATE TABLE invoice (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_email VARCHAR(255),
    service_type VARCHAR(255),
    amount DOUBLE,
    payment_method VARCHAR(100),
    status VARCHAR(50),
    purchase_date TIMESTAMP,
    invoice_number VARCHAR(255),
    invoice_downloadurl VARCHAR(500),
    subscription_plan_id BIGINT,
    CONSTRAINT fk_invoice_subscription FOREIGN KEY (subscription_plan_id)
        REFERENCES subscription_plans (id)
);

-- ========================
-- PAYMENTS
-- ========================
CREATE TABLE payments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    plan_id BIGINT,
    transaction_id VARCHAR(255),
    amount DECIMAL(15,2),
    currency VARCHAR(10),
    payment_status VARCHAR(50),
    payment_type VARCHAR(50),
    payment_date TIMESTAMP,
    CONSTRAINT fk_payment_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_payment_plan FOREIGN KEY (plan_id) REFERENCES subscription_plans(id) ON DELETE CASCADE
);

-- ========================
-- USER PAYMENT STATUS
-- ========================
CREATE TABLE user_payment_status (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    plan_id BIGINT,
    user_id BIGINT,
    subscription_start DATE,
    subscription_end DATE,
    status VARCHAR(50),
    transaction_id VARCHAR(255),
    CONSTRAINT fk_userpay_plan FOREIGN KEY (plan_id) REFERENCES subscription_plans(id) ON DELETE CASCADE,
    CONSTRAINT fk_userpay_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- ========================
-- APPLICATIONS
-- ========================
CREATE TABLE applications (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id BIGINT NOT NULL,
    job_id BIGINT NOT NULL,
    resume_url VARCHAR(500),
    status VARCHAR(50),
    applied_date TIMESTAMP,
    CONSTRAINT fk_app_student FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
    CONSTRAINT fk_app_job FOREIGN KEY (job_id) REFERENCES job_posts(id) ON DELETE CASCADE
);
