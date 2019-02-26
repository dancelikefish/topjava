DELETE
FROM user_roles;
DELETE
FROM users;
DELETE
FROM meals;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (user_id, role)
VALUES (100000, 'User'),
       (100001, 'Admin');

INSERT INTO meals (datetime, description, calories, userid)
VALUES ('2011-05-05 00:00:00', 'test meal1', 400, 100000),
       ('2012-05-05 00:00:00', 'test meal2', 500, 100000),
       ('2013-05-05 00:00:00', 'test meal3', 600, 100000),
       ('2014-05-05 00:00:00', 'test meal4', 700, 100001);


