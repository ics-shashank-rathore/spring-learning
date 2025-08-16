INSERT INTO roles (name, created_on, created_by) values
('ADMIN', now(), 'System'),
('MASTER', now(), 'System');

INSERT INTO privileges (name, created_on, created_by) values
('CAN_CREATE', now(), 'System'),
('CAN_VIEW', now(), 'System'),
('CAN_DELETE', now(), 'System');

INSERT INTO roles_privileges (role_id, privilege_id) values
(1, 1),
(2, 1),
(2, 2),
(2, 3);

INSERT INTO users(username, password, role_id, created_on, created_by, updated_on, updated_by) values
("shashank_admin", "$2a$12$BFG.WdvpMvZn35OpB6MfauiLKAg.Bt5XvHb8rhWfuKDo0QyplYo0O", 1, now(), 'System', now(), 'System'),
("shashank_master", "$2a$12$BFG.WdvpMvZn35OpB6MfauiLKAg.Bt5XvHb8rhWfuKDo0QyplYo0O", 2, now(), 'System', now(), 'System');
