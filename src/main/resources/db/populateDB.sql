DELETE FROM user_role;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO meals (user_id, date_time, description, calories)
VALUES (100000, '2020-01-30 10:00', 'User - Завтрак', 500),
       (100001, '2020-01-30 10:00', 'Admin - Завтрак', 500),
       (100000, '2020-01-30 13:00', 'User - Обед', 1000),
       (100001, '2020-01-30 13:00', 'Admin - Обед', 1000),
       (100000, '2020-01-30 20:00', 'User - Ужин', 500),
       (100001, '2020-01-30 20:00', 'Admin - Ужин', 500),
       (100000, '2020-01-31 00:00', 'User - Еда на граничное значение', 100),
       (100001, '2020-01-31 00:00', 'Admin - Еда на граничное значение', 100),
       (100000, '2020-01-31 10:00', 'User - Завтрак', 1000),
       (100001, '2020-01-31 10:00', 'Admin - Завтрак', 1000),
       (100000, '2020-01-31 13:00', 'User - Обед', 500),
       (100001, '2020-01-31 13:00', 'Admin - Обед', 500),
       (100000, '2020-01-31 20:00', 'User - Ужин', 410),
       (100001, '2020-01-31 20:00', 'Admin - Ужин', 410);

INSERT INTO user_role (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);
