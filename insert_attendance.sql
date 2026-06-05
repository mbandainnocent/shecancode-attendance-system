-- Insert attendance records for 5 students
-- Replace the placeholders below with actual values:
-- :attendance_date - the date attendance was recorded
-- :recorded_by_id - UUID of the person recording attendance
-- :recorded_by_name - name of the person recording attendance

INSERT INTO attendance (
    attendance_id,
    student_id,
    program_id,
    cohort_id,
    check_in_time,
    attendance_status,
    remarks,
    attendance_recorded_date,
    created_at,
    updated_at,
    recorded_by_id,
    recorded_by_name
) VALUES
(
    gen_random_uuid(),
    '550e8400-e29b-41d4-a716-446655440011',
    'b3a9b4b9-7f0d-4b9e-8e98-e7b4a88b653d',
    '6487f1b7-df96-44b5-a73f-70880a5159f9',
    '08:00:00',
    'PRESENT',
    'Arrived on time',
    '2026-05-30', -- :attendance_date
    NOW(),
    NOW(),
    '550e8400-e29b-41d4-a716-446655440015', -- recorded_by_id (trainer1)
    'Default Trainer' -- recorded_by_name
),
(
    gen_random_uuid(),
    'd09e2a38-c2cf-48ae-be95-d72bbcbc9d60',
    'b3a9b4b9-7f0d-4b9e-8e98-e7b4a88b653d',
    '6487f1b7-df96-44b5-a73f-70880a5159f9',
    '08:00:00',
    'PRESENT',
    'Arrived on time',
    '2026-05-30', -- :attendance_date
    NOW(),
    NOW(),
    '550e8400-e29b-41d4-a716-446655440015', -- recorded_by_id (trainer1)
    'Default Trainer' -- recorded_by_name
),
(
    gen_random_uuid(),
    'aa4d9767-6e5a-4360-b3ee-db2901a9d9ed',
    'b3a9b4b9-7f0d-4b9e-8e98-e7b4a88b653d',
    '6487f1b7-df96-44b5-a73f-70880a5159f9',
    '08:00:00',
    'PRESENT',
    'Arrived on time',
    '2026-05-30', -- :attendance_date
    NOW(),
    NOW(),
    '550e8400-e29b-41d4-a716-446655440015', -- recorded_by_id (trainer1)
    'Default Trainer' -- recorded_by_name
),
(
    gen_random_uuid(),
    'e7937e44-7b92-4ce9-bd8c-4f969bc6248e',
    'b3a9b4b9-7f0d-4b9e-8e98-e7b4a88b653d',
    '6487f1b7-df96-44b5-a73f-70880a5159f9',
    '08:00:00',
    'PRESENT',
    'Arrived on time',
    '2026-05-30', -- :attendance_date
    NOW(),
    NOW(),
    '550e8400-e29b-41d4-a716-446655440015', -- recorded_by_id (trainer1)
    'Default Trainer' -- recorded_by_name
),
(
    gen_random_uuid(),
    '05c93665-24b8-41dd-8fa1-f8c3518e3a44',
    'b3a9b4b9-7f0d-4b9e-8e98-e7b4a88b653d',
    '6487f1b7-df96-44b5-a73f-70880a5159f9',
    '08:00:00',
    'PRESENT',
    'Arrived on time',
    '2026-05-30', -- :attendance_date
    NOW(),
    NOW(),
    '550e8400-e29b-41d4-a716-446655440015', -- recorded_by_id (trainer1)
    'Default Trainer' -- recorded_by_name
);
