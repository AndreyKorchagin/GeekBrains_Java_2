BEGIN;

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (id bigserial NOT NULL PRIMARY KEY, user_name varchar(255) NOT NULL, age integer NOT NULL);
INSERT INTO users (user_name, age) VALUES   ('USER1', 10),
                                            ('USER2', 20),
                                            ('USER3', 30),
                                            ('USER4', 40),
                                            ('USER5', 50);

DROP TABLE IF EXISTS goods CASCADE;
CREATE TABLE goods (id bigserial NOT NULL PRIMARY KEY, goods_name varchar(255) NOT NULL, price integer NOT NULL);
INSERT INTO goods (goods_name, price) VALUES    ('GOODS1', 100),
                                                ('GOODS2', 200),
                                                ('GOODS3', 300),
                                                ('GOODS4', 400),
                                                ('GOODS5', 500);

COMMIT;