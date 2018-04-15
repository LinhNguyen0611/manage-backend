-- Roles
INSERT INTO roles (id, created_by, created_date, is_deleted, last_modified_by, last_modified_date, display_name, name) VALUES (1, null, null, false, null, null, 'Quản trị viên', 'Admin');

-- Users
INSERT INTO users (id, created_by, created_date, is_deleted, last_modified_by, last_modified_date, email, full_name, password, token, username) VALUES (1, null, null, false, null, null, 'root@mobilestore.uit.vn', 'Quản trị viên', '$2a$12$/zonkRKLqccxLorjhRyIxeHSRv/MAzbFxMI.hXDBypa44DVu9Vr26', null, 'root');

-- User with roles
INSERT INTO roles_users (role_id, users_id) VALUES (1, 1);