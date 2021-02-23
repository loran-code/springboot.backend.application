-- Role data input
INSERT INTO role(role_description) VALUES ('ROLE_USER');
INSERT INTO role(role_description) VALUES ('ROLE_ADMIN');
INSERT INTO role(role_description) VALUES ('ROLE_MECHANIC');
INSERT INTO role(role_description) VALUES ('ROLE_FRONTOFFICE');
INSERT INTO role(role_description) VALUES ('ROLE_BACKOFFICE');


-- Workorder status input
DROP TABLE IF EXISTS status;
CREATE TABLE status (status_description VARCHAR(50));
INSERT INTO status (status_description) VALUES ('APPOINTMENT_FOR_INSPECTION');
INSERT INTO status (status_description) VALUES ('INSPECTION');
INSERT INTO status (status_description) VALUES ('CUSTOMER_DECLINED');
INSERT INTO status (status_description) VALUES ('IN_REPAIR');
INSERT INTO status (status_description) VALUES ('INVOICED');
INSERT INTO status (status_description) VALUES ('PAID');


-- Employee data input
INSERT INTO employee(email, user_name, password, created, modified) VALUES ( 'j.p@garage.com','jan', '$2a$10$QM5zzOovrQ4LHZf0xFi3TONHkpmKOjhxketSkmnzyBTBSNMu4R0nq', NOW()::timestamp, NOW()::timestamp);
INSERT INTO employee(email, user_name, password, created, modified) VALUES ( 'f.v@garage.com','frederik', '$2a$10$QM5zzOovrQ4LHZf0xFi3TONHkpmKOjhxketSkmnzyBTBSNMu4R0nq', NOW()::timestamp, NOW()::timestamp);
INSERT INTO employee(email, user_name, password, created, modified) VALUES ( 'g.j.@garage.com','guus', '$2a$10$QM5zzOovrQ4LHZf0xFi3TONHkpmKOjhxketSkmnzyBTBSNMu4R0nq', NOW()::timestamp, NOW()::timestamp);
INSERT INTO employee(email, user_name, password, created, modified) VALUES ( 'h.w@garage.com','harold', '$2a$10$QM5zzOovrQ4LHZf0xFi3TONHkpmKOjhxketSkmnzyBTBSNMu4R0nq', NOW()::timestamp, NOW()::timestamp);
INSERT INTO employee(email, user_name, password, created, modified) VALUES ('c.m@garage.com','carolien', '$2a$10$QM5zzOovrQ4LHZf0xFi3TONHkpmKOjhxketSkmnzyBTBSNMu4R0nq', NOW()::timestamp, NOW()::timestamp);


-- Customer data input
INSERT INTO customer(first_name, last_name, phone, email, street, city, zip, created, modified) VALUES ('John', 'Deere', '06-12345678', 'j.deere@mail.com', 'Akkerstraat', 'Pijnacker', '1234AB', NOW()::timestamp::timestamp, NOW()::timestamp::timestamp);
INSERT INTO customer(first_name, last_name, phone, email, street, city, zip, created, modified) VALUES ('Bruce', 'Wayne', '06-15335679', 'b.wayne@mail.com', 'Vleermuisstraat', 'Steenbergen', '2312BC', NOW()::timestamp::timestamp, NOW()::timestamp::timestamp);
INSERT INTO customer(first_name, last_name, phone, email, street, city, zip, created, modified) VALUES ('Peter', 'Parker', '06-52335671', 'p.parker@mail.com', 'Spinnenstraat', 'Spinolaberg', '4021KQ', NOW()::timestamp::timestamp, NOW()::timestamp::timestamp);
INSERT INTO customer(first_name, last_name, phone, email, street, city, zip, created, modified) VALUES ('Bruce', 'Banner', '06-75231675', 'b.banner@mail.com', 'Groenstraat', 'Groenendaal', '2231CQ', NOW()::timestamp::timestamp,NOW()::timestamp::timestamp);
INSERT INTO customer(first_name, last_name, phone, email, street, city, zip, created, modified) VALUES ('Tony', 'Stark', '06-23335634', 't.stark@mail.com', 'Ijzerstraat', 'Ijzeren', '3532LR', NOW()::timestamp, NOW()::timestamp);


-- Car data input
INSERT INTO car(license_plate, created, modified) VALUES ('ZH-343-P', NOW()::timestamp, NOW()::timestamp);
INSERT INTO car(license_plate, created, modified) VALUES ('FB-354-R', NOW()::timestamp, NOW()::timestamp);
INSERT INTO car(license_plate, created, modified) VALUES ('TL-784-L', NOW()::timestamp, NOW()::timestamp);
INSERT INTO car(license_plate, created, modified) VALUES ('NX-920-R', NOW()::timestamp, NOW()::timestamp);
INSERT INTO car(license_plate, created, modified) VALUES ('ZL-547-J', NOW()::timestamp, NOW()::timestamp);
INSERT INTO car(license_plate, created, modified) VALUES ('SK-147-S', NOW()::timestamp, NOW()::timestamp);


-- Activity data input
INSERT INTO activity(activity_number, description, price, created, modified) VALUES (1,'Car Inspection', 50.00, NOW()::timestamp, NOW()::timestamp);
INSERT INTO activity(activity_number, description, price, created, modified) VALUES (2,'Tyre(s) change', 40.00, NOW()::timestamp, NOW()::timestamp);
INSERT INTO activity(activity_number, description, price, created, modified) VALUES (3,'Battery change', 20.00, NOW()::timestamp, NOW()::timestamp);
INSERT INTO activity(activity_number, description, price, created, modified) VALUES (4,'Oil Filter change', 25.00, NOW()::timestamp, NOW()::timestamp);
INSERT INTO activity(activity_number, description, price, created, modified) VALUES (5,'Fuel Pump change', 50.00, NOW()::timestamp, NOW()::timestamp);


-- Car Components data input
INSERT INTO component(component_number, description, price, created, modified) VALUES (1, 'Tyre', 50.00, NOW()::timestamp, NOW()::timestamp);
INSERT INTO component(component_number, description, price, created, modified) VALUES (2, 'Battery', 100.00, NOW()::timestamp, NOW()::timestamp);
INSERT INTO component(component_number, description, price, created, modified) VALUES (3, 'Oil Filter', 25.00, NOW()::timestamp, NOW()::timestamp);
INSERT INTO component(component_number, description, price, created, modified) VALUES (4, 'Fuel Pump', 75.00, NOW()::timestamp, NOW()::timestamp);
INSERT INTO component(component_number, description, price, created, modified) VALUES (5, 'Break Pads', 40.00, NOW()::timestamp, NOW()::timestamp);


-- Joined table car customer
INSERT INTO car_customer(customer_id,car_id) VALUES (1,1);
INSERT INTO car_customer(customer_id,car_id) VALUES (1,2);
INSERT INTO car_customer(customer_id,car_id) VALUES (2,3);
INSERT INTO car_customer(customer_id,car_id) VALUES (3,4);
INSERT INTO car_customer(customer_id,car_id) VALUES (4,5);
INSERT INTO car_customer(customer_id,car_id) VALUES (5,6);


-- Work order data input
INSERT INTO workorder(work_order_number, car_id, status, appointment_date, invoice_number, created, modified) VALUES (101, 1, 'CUSTOMER_DECLINED','26-2-2021 12:00', 101 ,NOW()::timestamp, NOW()::timestamp);
INSERT INTO workorder(work_order_number, car_id, status, appointment_date, invoice_number, created, modified) VALUES (102, 2, 'INSPECTION','10-4-2021 12:00', 102 ,NOW()::timestamp, NOW()::timestamp);
INSERT INTO workorder(work_order_number, car_id, status, appointment_date, invoice_number, created, modified) VALUES (103, 3, 'IN_REPAIR','3-2-2021 12:00', 103 ,NOW()::timestamp, NOW()::timestamp);
INSERT INTO workorder(work_order_number, car_id, status, appointment_date, invoice_number, created, modified) VALUES (104, 4, 'INVOICED','2-2-2021 15:00', 104 ,NOW()::timestamp, NOW()::timestamp);
INSERT INTO workorder(work_order_number, car_id, status, appointment_date, invoice_number, created, modified) VALUES (105, 5, 'PAID','1-2-2021 09:00', 105 ,NOW()::timestamp, NOW()::timestamp);


-- work order incurred costs data input
INSERT into work_order_incurred_costs(workorder_id, type, activity_id, quantity, created, modified) VALUES (1, 'ACTIVITY', 2, 1, NOW()::timestamp, NOW()::timestamp);
INSERT into work_order_incurred_costs(workorder_id, type, component_id, quantity, created, modified) VALUES (1, 'COMPONENT', 1, 4, NOW()::timestamp, NOW()::timestamp);


-- Inventory data input


-- Invoice data input

