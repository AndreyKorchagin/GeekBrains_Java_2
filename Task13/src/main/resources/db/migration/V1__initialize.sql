CREATE TABLE users (
  id                    bigserial,
  username              VARCHAR(30) NOT NULL,
  password              VARCHAR(80) NOT NULL,
  email                 VARCHAR(50) UNIQUE,
  PRIMARY KEY (id)
);

CREATE TABLE roles (
  id                    serial,
  name                  VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE users_roles (
  user_id               BIGINT NOT NULL,
  role_id               INT NOT NULL,
  PRIMARY KEY (user_id, role_id),
  foreign key (user_id) REFERENCES users (id),
  foreign key (role_id) REFERENCES roles (id)
);

insert INTo roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN'), ('DELETE_USERS_PERMISSION');

insert INTO users (username, password, email)
values
('Bob', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com'),
('John Johnson', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com'),
('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com');

insert INTo users_roles (user_id, role_id) values (1, 1), (1, 3);

CREATE TABLE books (id bigserial PRIMARY KEY, title VARCHAR(255), description VARCHAR(5000), genre VARCHAR(255), price NUMERIC(8, 2), publish_year INT);
insert INTo books (title, description, genre, price, publish_year) values
('Harry Potter: Philosopher''s Stone', 'Description 1', 'FANTASY', 300.0, 2000),
('Harry Potter: Chamber of Secrets', 'Description 2', 'DETECTIVE', 400.0, 2001),
('Harry Potter: Prisoner of Azkaban', 'Description 3', 'FANTASY', 500.0, 2002),
('Harry Potter: Goblet of Fire', 'Description 4', 'DETECTIVE', 700.0, 2007),
('Harry Potter: Order of the Phoenix', 'Description 5', 'FANTASY', 440.0, 2004),
('Harry Potter: Half-Blood Price', 'Description 6', 'DETECTIVE', 650.0, 2007),
('Harry Potter: Deathly Hallows', 'Description 6', 'DETECTIVE', 650.0, 2007),
('Lockwood & Co.', 'Description 7', 'FANTASY', 200.0, 2006),
('Neverwhere', 'Description 7', 'FANTASY', 200.0, 2000),
('Mistborn', 'Description 7', 'FANTASY', 200.0, 2000),
('Ambers Chronicles', 'Description 17', 'FANTASY', 200.0, 1989),
('Lord Of The Ring: The Fellowship of the Ring', 'Description 8', 'FANTASY', 1200.0, 2006),
('Lord Of The Ring: The Two Towers', 'Description 9', 'FANTASY', 900.0, 2004),
('Lord Of The Ring: The Return of the King', 'Description 10', 'FANTASY', 600.0, 2001),
('Hobbit', 'Description 11', 'FICTION', 500.0, 2001);

drop table if exists orders cascade;
CREATE TABLE orders (   id bigserial PRIMARY KEY ,
                        user_id BIGINT NOT NULL REFERENCES users(id),
                        price NUMERIC(8, 2) NOT NULL
--                         CONSTRAINT fk_user_id foreign key (user_id) REFERENCES users (id));
                    );

drop table if exists orders_items cascade;
CREATE TABLE orders_items ( id bigserial PRIMARY KEY,
                            order_id BIGINT NOT NULL REFERENCES orders(id),
                            book_id BIGINT NOT NULL REFERENCES books(id),
                            quantity INT,
                            price NUMERIC (8, 2),
                            status VARCHAR (50)
--                             CONSTRAINT fk_book_id foreign key (book_id) REFERENCES books (id),
--                             CONSTRAINT fk_order_id foreign key (order_id) REFERENCES orders (id)
                            );