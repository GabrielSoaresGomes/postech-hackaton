CREATE TABLE IF NOT EXISTS screening_items (
    id BIGSERIAL PRIMARY KEY,
    screening_id BIGINT NOT NULL,
    question TEXT NOT NULL,
    answer TEXT,
    category VARCHAR(100) NOT NULL,
    last_modified_date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_screening_items_screening FOREIGN KEY (screening_id) REFERENCES screenings(id) ON DELETE CASCADE
);

CREATE INDEX idx_screening_items_screening_id ON screening_items(screening_id);

