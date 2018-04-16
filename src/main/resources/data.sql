-- Roles
INSERT INTO roles (id, created_by, created_date, is_deleted, last_modified_by, last_modified_date, display_name, name) VALUES (1, null, null, false, null, null, 'Quản trị viên', 'Admin');

-- Users (Token for root user: 03srLwfpmPxsOgXHN0DgSCpG0P5kg2HrwQqUZSGHmcR3WpKp6iREcw==)
INSERT INTO users (id, created_by, created_date, is_deleted, last_modified_by, last_modified_date, email, full_name, password, token, username) VALUES (1, null, null, false, null, null, 'root@mobilestore.uit.vn', 'Quản trị viên', '$2a$12$/zonkRKLqccxLorjhRyIxeHSRv/MAzbFxMI.hXDBypa44DVu9Vr26', 'ed35786a-b995-4de9-8dd4-a6341c3f6e90', 'root');

-- User with roles
INSERT INTO users_roles (roles_id, users_id) VALUES (1, 1);