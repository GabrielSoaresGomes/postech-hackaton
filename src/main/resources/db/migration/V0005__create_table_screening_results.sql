CREATE TABLE IF NOT EXISTS screening_results (
    id BIGSERIAL PRIMARY KEY,
    screening_id BIGINT NOT NULL UNIQUE,
    recommended_priority screening_priority NOT NULL,
    ai_analysis TEXT,
    confidence_score DECIMAL(5, 4),
    recommendations TEXT,
    generated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_screening_results_screening FOREIGN KEY (screening_id) REFERENCES screenings(id) ON DELETE CASCADE
);

CREATE INDEX idx_screening_results_screening_id ON screening_results(screening_id);

