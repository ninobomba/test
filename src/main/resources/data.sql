CREATE SCHEMA IF NOT EXISTS demo;

CREATE TABLE IF NOT EXISTS demo.USERS
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    username   VARCHAR(50)                         NOT NULL,
    email      VARCHAR(100)                        NOT NULL,
    password   VARCHAR(255)                        NOT NULL,
    first_name VARCHAR(50)                         NULL,
    last_name  VARCHAR(50)                         NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL,
    active     BOOLEAN   DEFAULT TRUE              NULL, -- Changed from tinyint(1) for better portability and clarity
    CONSTRAINT UQ_DEMO_USERS_EMAIL UNIQUE (email),       -- Using a more descriptive constraint name
    CONSTRAINT UQ_DEMO_USERS_USERNAME UNIQUE (username)  -- Using a more descriptive constraint name
);
