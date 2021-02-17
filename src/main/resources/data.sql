-- DROP TABLE IF EXISTS customer;
-- CREATE TABLE customer(id serial PRIMARY KEY, first_name VARCHAR(50), last_name VARCHAR(50), phone VARCHAR(15), email VARCHAR(50), street VARCHAR(50), city VARCHAR(50), zip VARCHAR(50), created date, modified timestamp);

-- Customer data input
INSERT INTO customer(first_name, last_name, phone, email, street, city, zip, created, modified) VALUES ('John', 'Deere', '06-12345678', 'j.deere@mail.com', 'Akkerstraat', 'Pijnacker', '1234AB', NOW()::timestamp::timestamp, NOW()::timestamp::timestamp);
INSERT INTO customer(first_name, last_name, phone, email, street, city, zip, created, modified) VALUES ('Bruce', 'Wayne', '06-15335679', 'b.wayne@mail.com', 'Vleermuisstraat', 'Steenbergen', '2312BC', NOW()::timestamp::timestamp, NOW()::timestamp::timestamp);
INSERT INTO customer(first_name, last_name, phone, email, street, city, zip, created, modified) VALUES ('Peter', 'Parker', '06-52335671', 'p.parker@mail.com', 'Spinnenstraat', 'Spinolaberg', '4021KQ', NOW()::timestamp::timestamp, NOW()::timestamp::timestamp);
INSERT INTO customer(first_name, last_name, phone, email, street, city, zip, created, modified) VALUES ('Bruce', 'Banner', '06-75231675', 'b.banner@mail.com', 'Groenstraat', 'Groenendaal', '2231CQ', NOW()::timestamp::timestamp,NOW()::timestamp::timestamp);
INSERT INTO customer(first_name, last_name, phone, email, street, city, zip, created, modified) VALUES ('Tony', 'Stark', '06-23335634', 't.stark@mail.com', 'Ijzerstraat', 'Ijzeren', '3532LR', NOW()::timestamp, NOW()::timestamp);


-- Employee data input
INSERT INTO employee(first_name, last_name, user_name, password, login_tatus, created, modified) VALUES ('Jan', 'Peters', 'jan', 'password','offline', NOW()::timestamp, NOW()::timestamp);
INSERT INTO employee(first_name, last_name, user_name, password, login_tatus, created, modified) VALUES ('Frederik', 'de Vries', 'frederik', 'password','offline', NOW()::timestamp, NOW()::timestamp);
INSERT INTO employee(first_name, last_name, user_name, password, login_tatus, created, modified) VALUES ('Guus', 'Janssen', 'guus', 'password','offline', NOW()::timestamp, NOW()::timestamp);
INSERT INTO employee(first_name, last_name, user_name, password, login_tatus, created, modified) VALUES ('Harold', 'Wagemakers', 'harold', 'password','offline', NOW()::timestamp, NOW()::timestamp);
INSERT INTO employee(first_name, last_name, user_name, password, login_tatus, created, modified) VALUES ('Carolien', 'Muis', 'carolien', 'password','offline', NOW()::timestamp, NOW()::timestamp);

-- Role data input
INSERT INTO role(role_name, employee_id) VALUES (1, 1);
INSERT INTO role(role_name, employee_id) VALUES (2, 1);
INSERT INTO role(role_name, employee_id) VALUES (3, 1);
-- INSERT INTO role(id, role_name, employee_id) VALUES ()
-- Vehicle data input

-- Inventory data input



-- INSERT INTO users (username, password, enabled) VALUES ('user', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE);
-- INSERT INTO users (username, password, enabled) VALUES ('admin', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE);
-- INSERT INTO users (username, password, enabled) VALUES ('backoffice', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE);
-- INSERT INTO users (username, password, enabled) VALUES ('frontoffice', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE);
--
-- INSERT INTO authorities (username, authority) VALUES ('user', 'ROLE_USER');
-- INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_USER');
-- INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN');
-- INSERT INTO authorities (username, authority) VALUES ('peter', 'ROLE_USER');
-- INSERT INTO authorities (username, authority) VALUES ('peter', 'ROLE_ADMIN');
