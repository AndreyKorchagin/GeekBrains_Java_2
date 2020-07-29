BEGIN;

DROP TABLE IF EXISTS buyers CASCADE;
CREATE TABLE buyers (id bigserial PRIMARY KEY,
                     name varchar(255));
INSERT INTO buyers (name) VALUES    ('USER1'),
                                    ('USER2'),
                                    ('USER3');

DROP TABLE IF EXISTS goods CASCADE;
CREATE TABLE goods (id bigserial PRIMARY KEY,
                    name varchar(255),
                    price integer);
INSERT INTO goods (name, price) VALUES      ('GOODS1', 100),
                                            ('GOODS2', 200),
                                            ('GOODS3', 300);

DROP TABLE IF EXISTS buyer_goods CASCADE;
CREATE TABLE buyer_goods (  id bigserial PRIMARY KEY,
                            id_buyer bigserial,
                            id_goods bigserial,
                            price integer,
                            FOREIGN KEY (id_buyer) REFERENCES buyers(id),
                            FOREIGN KEY (id_goods) REFERENCES goods(id));
INSERT INTO buyer_goods (id_buyer, id_goods, price) VALUES  (1, 1, 110), (1, 2, 120),
                                                            (2, 1, 210), (2, 2, 220), (2, 3, 230),
                                                            (3, 1, 310), (3, 2, 320), (3, 3, 330);

--DROP TABLE IF EXISTS buyer_goods CASCADE;
--CREATE TABLE buyer_goods (  id bigserial PRIMARY KEY,
--                            id_buyer bigserial,
--                            id_goods bigserial,
--                            price integer,
--                            FOREIGN KEY (id_buyer) REFERENCES buyers(id),
--                            FOREIGN KEY (id_goods) REFERENCES goods(id));
--INSERT INTO buyer_goods (id_buyer, id_goods, price) VALUES  (1, 1, 110), (1, 2, 120),
--                                                            (2, 1, 210), (2, 2, 220), (2, 3, 230),
--                                                            (3, 1, 310), (3, 2, 320), (3, 3, 330);

COMMIT;
