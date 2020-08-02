BEGIN;

DROP TABLE IF EXISTS Users CASCADE;
CREATE TABLE Users (id bigserial NOT NULL PRIMARY KEY,
                    user_name varchar(255) NOT NULL);
INSERT INTO Users (user_name) VALUES    ('User1'),
                                        ('User2'),
                                        ('User3'),
                                        ('User4'),
                                        ('User5'),
                                        ('User6'),
                                        ('User7'),
                                        ('User8');

DROP TABLE IF EXISTS Lots CASCADE;
CREATE TABLE Lots ( id bigserial NOT NULL PRIMARY KEY,
                    lot_name varchar(255) NOT NULL,
                    current_rate integer NOT NULL,
                    last_user_id bigserial NOT NULL REFERENCES Users(id),
                    version integer);
INSERT INTO Lots (lot_name, current_rate, last_user_id, version) VALUES ('Lot1', 0, 1, 0),
                                                                        ('Lot2', 0, 2, 0),
                                                                        ('Lot3', 0, 3, 0),
                                                                        ('Lot4', 0, 4, 0);

COMMIT;
