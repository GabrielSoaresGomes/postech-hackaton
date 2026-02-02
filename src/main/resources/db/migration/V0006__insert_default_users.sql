-- Insert default user types
INSERT INTO users (user_type, name, email, login, password, last_modified_date_time)
VALUES
    ('ADMIN', 'Administrator', 'admin@hackaton.com', 'admin', '$2a$10$slYQmyNdGzin7olVN7Tje.Hqf1s1qDvVLJwhfpFLs3WXU.yA4yUCe', CURRENT_TIMESTAMP),
    ('HEALTH_PROFESSIONAL', 'Dr. João Silva', 'joao@hackaton.com', 'joao.silva', '$2a$10$slYQmyNdGzin7olVN7Tje.Hqf1s1qDvVLJwhfpFLs3WXU.yA4yUCe', CURRENT_TIMESTAMP),
    ('PATIENT', 'Patient Test', 'patient@hackaton.com', 'patient.test', '$2a$10$slYQmyNdGzin7olVN7Tje.Hqf1s1qDvVLJwhfpFLs3WXU.yA4yUCe', CURRENT_TIMESTAMP)
ON CONFLICT (login) DO NOTHING;

