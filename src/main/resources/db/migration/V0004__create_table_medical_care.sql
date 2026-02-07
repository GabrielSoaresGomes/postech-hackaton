CREATE TABLE IF NOT EXISTS medical_care (
    id BIGSERIAL PRIMARY KEY,
    document VARCHAR(15) NOT NULL,
    age NUMERIC(3, 0) NOT NULL,
    priority VARCHAR(2) NOT NULL,
    description BPCHAR NOT NULL,
    ai_justification BPCHAR NULL,
    priority_access BOOLEAN NOT NULL,
    phone_number VARCHAR(25) NOT NULL,
    status VARCHAR(30) NOT NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL
);
