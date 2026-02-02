CREATE TYPE screening_priority AS ENUM ('CRITICAL', 'HIGH', 'MEDIUM', 'LOW');

CREATE TABLE IF NOT EXISTS screenings (
    id BIGSERIAL PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    health_professional_id BIGINT,
    description TEXT NOT NULL,
    priority screening_priority NOT NULL DEFAULT 'MEDIUM',
    completed BOOLEAN DEFAULT FALSE,
    ai_assessment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    completed_at TIMESTAMP,
    last_modified_date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_screenings_patient FOREIGN KEY (patient_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_screenings_professional FOREIGN KEY (health_professional_id) REFERENCES users(id) ON DELETE SET NULL
);

CREATE INDEX idx_screenings_patient_id ON screenings(patient_id);
CREATE INDEX idx_screenings_health_professional_id ON screenings(health_professional_id);
CREATE INDEX idx_screenings_completed ON screenings(completed);
CREATE INDEX idx_screenings_priority ON screenings(priority);

