CREATE TABLE books (id bigserial NOT NULL PRIMARY KEY, title varchar(255) NOT NULL , description varchar(5000), price integer NOT NULL, publish_year int, genre varchar(100));
INSERT INTO books (title, description, price, publish_year, genre) VALUES   ('BOOK1', 'description1', 10, 2001, 'FANTASY'),
                                                                            ('BOOK2', 'description2', 20, 2002, 'DETECTIVE'),
                                                                            ('BOOK3', 'description3', 30, 2003, 'ROMANCE'),
                                                                            ('BOOK4', 'description4', 40, 2004, 'THRILLER'),
                                                                            ('BOOK5', 'description5', 50, 2005, 'FANTASY'),
                                                                            ('BOOK6', 'description6', 60, 2006, 'DETECTIVE'),
                                                                            ('BOOK7', 'description7', 70, 2007, 'ROMANCE'),
                                                                            ('BOOK8', 'description8', 80, 2008, 'THRILLER'),
                                                                            ('BOOK9', 'description9', 90, 2009, 'FANTASY'),
                                                                            ('BOOK10', 'description10', 100, 2010, 'DETECTIVE'),
                                                                            ('BOOK11', 'description11', 110, 2011, 'ROMANCE');
