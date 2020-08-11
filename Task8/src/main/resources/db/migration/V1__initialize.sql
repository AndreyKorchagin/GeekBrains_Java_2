CREATE TABLE books (id bigserial NOT NULL PRIMARY KEY, title varchar(255) NOT NULL , description varchar(5000), price integer NOT NULL, publish_year int );
INSERT INTO books (title, description, price, publish_year) VALUES  ('BOOK1', 'description1', 10, 2001),
                                                                    ('BOOK2', 'description2', 20, 2002),
                                                                    ('BOOK3', 'description3', 30, 2003),
                                                                    ('BOOK4', 'description4', 40, 2004),
                                                                    ('BOOK5', 'description5', 50, 2005),
                                                                    ('BOOK6', 'description6', 60, 2006),
                                                                    ('BOOK7', 'description7', 70, 2007),
                                                                    ('BOOK8', 'description8', 80, 2008),
                                                                    ('BOOK9', 'description9', 90, 2009),
                                                                    ('BOOK10', 'description10', 100, 2010),
                                                                    ('BOOK11', 'description11', 110, 2011);
