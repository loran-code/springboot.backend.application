-- DROP TABLE IF EXISTS customer;
-- CREATE TABLE customer(id serial PRIMARY KEY, first_name VARCHAR(50), last_name VARCHAR(50), phone VARCHAR(15), email VARCHAR(50), street VARCHAR(50), city VARCHAR(50), zip VARCHAR(50), created date, modified date);
--
-- INSERT INTO customer(first_name, last_name, phone, email, street, city, zip, created, modified) VALUES ('Loran', 'Akker', '06-12345678', 'loran@mail.com', 'Sesamstraat', 'Den Haag', '2574AV', date(), date());
--


-- INSERT INTO users (username, password, enabled) VALUES ('user', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE);
-- INSERT INTO users (username, password, enabled) VALUES ('admin', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE);
-- INSERT INTO users (username, password, enabled) VALUES ('peter', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE);
--
-- INSERT INTO authorities (username, authority) VALUES ('user', 'ROLE_USER');
-- INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_USER');
-- INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN');
-- INSERT INTO authorities (username, authority) VALUES ('peter', 'ROLE_USER');
-- INSERT INTO authorities (username, authority) VALUES ('peter', 'ROLE_ADMIN');
