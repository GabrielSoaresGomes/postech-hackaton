CREATE TABLE IF NOT EXISTS users (
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_login ON users(login);

);
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    last_modified_date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    password VARCHAR(255) NOT NULL,
    login VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) UNIQUE,
    name VARCHAR(255) NOT NULL,
    user_type VARCHAR(50) NOT NULL,
    id BIGSERIAL PRIMARY KEY,

