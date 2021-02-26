-- Role data input
INSERT INTO role(role_description) VALUES ('ROLE_USER');
INSERT INTO role(role_description) VALUES ('ROLE_ADMIN');
INSERT INTO role(role_description) VALUES ('ROLE_MECHANIC');
INSERT INTO role(role_description) VALUES ('ROLE_FRONTOFFICE');
INSERT INTO role(role_description) VALUES ('ROLE_BACKOFFICE');


-- Employee data input
INSERT INTO employee(email, user_name, password, created, modified) VALUES ( 'j.p@garage.com','jan', '$2a$10$QM5zzOovrQ4LHZf0xFi3TONHkpmKOjhxketSkmnzyBTBSNMu4R0nq', NOW(), NOW());
INSERT INTO employee(email, user_name, password, created, modified) VALUES ( 'f.v@garage.com','frederik', '$2a$10$QM5zzOovrQ4LHZf0xFi3TONHkpmKOjhxketSkmnzyBTBSNMu4R0nq', NOW(), NOW());
INSERT INTO employee(email, user_name, password, created, modified) VALUES ( 'g.j.@garage.com','guus', '$2a$10$QM5zzOovrQ4LHZf0xFi3TONHkpmKOjhxketSkmnzyBTBSNMu4R0nq', NOW(), NOW());
INSERT INTO employee(email, user_name, password, created, modified) VALUES ( 'h.w@garage.com','harold', '$2a$10$QM5zzOovrQ4LHZf0xFi3TONHkpmKOjhxketSkmnzyBTBSNMu4R0nq', NOW(), NOW());
INSERT INTO employee(email, user_name, password, created, modified) VALUES ('c.m@garage.com','carolien', '$2a$10$QM5zzOovrQ4LHZf0xFi3TONHkpmKOjhxketSkmnzyBTBSNMu4R0nq', NOW(), NOW());


-- Customer data input
INSERT INTO customer(first_name, last_name, phone, email, street, city, zip, created, modified) VALUES ('John', 'Deere', '06-12345678', 'j.deere@mail.com', 'Akkerstraat', 'Pijnacker', '1234AB', NOW(), NOW());
INSERT INTO customer(first_name, last_name, phone, email, street, city, zip, created, modified) VALUES ('Bruce', 'Wayne', '06-15335679', 'b.wayne@mail.com', 'Vleermuisstraat', 'Steenbergen', '2312BC', NOW(), NOW());
INSERT INTO customer(first_name, last_name, phone, email, street, city, zip, created, modified) VALUES ('Peter', 'Parker', '06-52335671', 'p.parker@mail.com', 'Spinnenstraat', 'Spinolaberg', '4021KQ', NOW(), NOW());
INSERT INTO customer(first_name, last_name, phone, email, street, city, zip, created, modified) VALUES ('Bruce', 'Banner', '06-75231675', 'b.banner@mail.com', 'Groenstraat', 'Groenendaal', '2231CQ', NOW(),NOW());
INSERT INTO customer(first_name, last_name, phone, email, street, city, zip, created, modified) VALUES ('Tony', 'Stark', '06-23335634', 't.stark@mail.com', 'Ijzerstraat', 'Ijzeren', '3532LR', NOW(), NOW());


-- Car data input
INSERT INTO car(license_plate, created, modified) VALUES ('ZH-343-P', NOW(), NOW());
INSERT INTO car(license_plate, created, modified) VALUES ('FB-354-R', NOW(), NOW());
INSERT INTO car(license_plate, created, modified) VALUES ('TL-784-L', NOW(), NOW());
INSERT INTO car(license_plate, created, modified) VALUES ('NX-920-R', NOW(), NOW());
INSERT INTO car(license_plate, created, modified) VALUES ('ZL-547-J', NOW(), NOW());
INSERT INTO car(license_plate, created, modified) VALUES ('SK-147-S', NOW(), NOW());
INSERT INTO car(license_plate, created, modified) VALUES ('AA-111-AA', NOW(), NOW());


-- Joined table car customer
INSERT INTO car_customer(car_id, customer_id) VALUES (1,1);
INSERT INTO car_customer(car_id, customer_id) VALUES (2,1);
INSERT INTO car_customer(car_id, customer_id) VALUES (3,2);
INSERT INTO car_customer(car_id, customer_id) VALUES (4,3);
INSERT INTO car_customer(car_id, customer_id) VALUES (5,4);
INSERT INTO car_customer(car_id, customer_id) VALUES (6,5);
INSERT INTO car_customer(car_id, customer_id) VALUES (7,5);


-- Activity data input
INSERT INTO activity(activity_number, description, price, created, modified) VALUES (1,'Car Inspection', 50.00, NOW(), NOW());
INSERT INTO activity(activity_number, description, price, created, modified) VALUES (2,'Tire(s) change', 40.00, NOW(), NOW());
INSERT INTO activity(activity_number, description, price, created, modified) VALUES (3,'Battery change', 20.00, NOW(), NOW());
INSERT INTO activity(activity_number, description, price, created, modified) VALUES (4,'Oil Filter change', 25.00, NOW(), NOW());
INSERT INTO activity(activity_number, description, price, created, modified) VALUES (5,'Fuel Pump change', 50.00, NOW(), NOW());


-- Car Components data input
INSERT INTO component(component_number, description, price, created, modified) VALUES (1, 'Tire', 50.00, NOW(), NOW());
INSERT INTO component(component_number, description, price, created, modified) VALUES (2, 'Battery', 100.00, NOW(), NOW());
INSERT INTO component(component_number, description, price, created, modified) VALUES (3, 'Oil Filter', 25.00, NOW(), NOW());
INSERT INTO component(component_number, description, price, created, modified) VALUES (4, 'Fuel Pump', 75.00, NOW(), NOW());
INSERT INTO component(component_number, description, price, created, modified) VALUES (5, 'Break Pads', 40.00, NOW(), NOW());


-- Inventory data input
INSERT INTO inventory(inventory_number, description, stock_amount, created, modified) VALUES ( 1, 'Tire', 10, NOW(), NOW());
INSERT INTO inventory(inventory_number, description, stock_amount, created, modified) VALUES ( 2, 'Battery', 10, NOW(), NOW());
INSERT INTO inventory(inventory_number, description, stock_amount, created, modified) VALUES ( 3, 'Oil Filter', 10, NOW(), NOW());
INSERT INTO inventory(inventory_number, description, stock_amount, created, modified) VALUES ( 4, 'Fuel Pump', 10, NOW(), NOW());
INSERT INTO inventory(inventory_number, description, stock_amount, created, modified) VALUES ( 5, 'Break Pads', 10, NOW(), NOW());


-- Work order data input
INSERT INTO workorder(work_order_number, car_id, status, appointment_date, invoice_number, created, modified) VALUES (101, 1, 'CUSTOMER_DECLINED','26-2-2021 12:00', 1001 ,NOW(), NOW());
INSERT INTO workorder(work_order_number, car_id, status, appointment_date, invoice_number, created, modified) VALUES (102, 2, 'INSPECTION','10-4-2021 12:00', 1002 ,NOW(), NOW());
INSERT INTO workorder(work_order_number, car_id, status, appointment_date, invoice_number, created, modified) VALUES (103, 3, 'IN_REPAIR','3-2-2021 12:00', 1003 ,NOW(), NOW());
INSERT INTO workorder(work_order_number, car_id, status, appointment_date, invoice_number, created, modified) VALUES (104, 4, 'INVOICED','2-2-2021 15:00', 1004 ,NOW(), NOW());
INSERT INTO workorder(work_order_number, car_id, status, appointment_date, invoice_number, created, modified) VALUES (105, 5, 'PAID','1-2-2021 09:00', 1005 ,NOW(), NOW());


-- work order incurred costs data input
-- INSERT into work_order_incurred_costs(workorder_id, type, activity_id, quantity, created, modified) VALUES (1, 'ACTIVITY', 1, 1, NOW(), NOW());
-- INSERT into work_order_incurred_costs(workorder_id, type, component_id, quantity, created, modified) VALUES (2, 'COMPONENT', 1, 4, NOW(), NOW());
INSERT INTO work_order_incurred_costs(workorder_id, type, quantity, created, modified) VALUES (1, 'ACTIVITY',5, NOW(), NOW());
INSERT INTO work_order_incurred_costs(workorder_id, type, quantity, created, modified) VALUES (2, 'COMPONENT', 5, NOW(), NOW());


-- Invoice data input

