
INSERT INTO `user` (id, username, nickname, password, created_time, updated_time)
VALUES ('1', 'admin', 'vex', '$2a$10$t/9Fu1/5zDt/0XUbxoFYP.bzaLNOz5Apr8J6QIFDh9IDsaKSGB7xa',
        '2022-06-18 09:27:12.260000',
        '2022-06-18 09:27:12.260000');
INSERT INTO `role` (id, name, title, created_time, updated_time)
VALUES ('1', 'ROLE_USER', '普通用户', '2022-06-18 09:27:12.260000', '2022-06-18 09:27:12.260000');
INSERT INTO `role` (id, name, title, created_time, updated_time)
VALUES ('2', 'ROLE_ADMIN', '超级管理员', '2022-06-18 09:27:12.260000', '2022-06-18 09:27:12.260000');
INSERT INTO `user_role` (user_id, role_id)
VALUES ('1', '1');
INSERT INTO `user_role` (user_id, role_id)
VALUES ('1', '2');