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
INSERT INTO role(role_name) VALUES (1);
INSERT INTO role(role_name) VALUES (2);
INSERT INTO role(role_name) VALUES (3);
INSERT INTO role(role_name) VALUES (4);


-- Car data input
INSERT INTO car(license_plate, created, modified, customer_id) VALUES ('ZH-343-P', NOW()::timestamp, NOW()::timestamp, 1);
INSERT INTO car(license_plate, created, modified, customer_id) VALUES ('TL-784-L', NOW()::timestamp, NOW()::timestamp, 1);
INSERT INTO car(license_plate, created, modified, customer_id) VALUES ('NX-920-R', NOW()::timestamp, NOW()::timestamp, 1);
INSERT INTO car(license_plate, created, modified, customer_id) VALUES ('ZL-547-J', NOW()::timestamp, NOW()::timestamp, 1);
INSERT INTO car(license_plate, created, modified, customer_id) VALUES ('SK-147-S', NOW()::timestamp, NOW()::timestamp, 1);


-- Car Components data input


-- Activity (labor) data input


-- Inventory data input


-- Invoice data input





-- INSERT INTO users (username, password, enabled) VALUES ('user', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE);
-- INSERT INTO users (username, password, enabled) VALUES ('admin', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE);
-- INSERT INTO users (username, password, enabled) VALUES ('backoffice', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE);
-- INSERT INTO users (username, password, enabled) VALUES ('frontoffice', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE);

-- INSERT INTO authorities (username, authority) VALUES ('user', 'ROLE_USER');
-- INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_USER');
-- INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN');
-- INSERT INTO authorities (username, authority) VALUES ('peter', 'ROLE_USER');
-- INSERT INTO authorities (username, authority) VALUES ('peter', 'ROLE_ADMIN');