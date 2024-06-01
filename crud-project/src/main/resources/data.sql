INSERT INTO roles (name) VALUES ('ROLE_MANAGER');
INSERT INTO roles (name) VALUES ('ROLE_EMPLOYEE');

INSERT INTO `crud-data`.`users` (`username`, `email`, `password`) VALUES ('admin', 'admin@gmail.com', '$2a$10$rnYjixXFgIBNC5iveZVd7.vry/LvLdfTQM1MvBWLp//psZWTtIMMO');
INSERT INTO `crud-data`.`users` (`username`, `email`, `password`) VALUES ('malak', 'malak@gmail.com', '$2a$10$rnYjixXFgIBNC5iveZVd7.vry/LvLdfTQM1MvBWLp//psZWTtIMMO');
INSERT INTO `crud-data`.`users` (`username`, `email`, `password`) VALUES ('zaid', 'zaid@gmail.com', '$2a$10$rnYjixXFgIBNC5iveZVd7.vry/LvLdfTQM1MvBWLp//psZWTtIMMO');
INSERT INTO `crud-data`.`users` (`username`, `email`, `password`) VALUES ('sara', 'sara@gmail.com', '$2a$10$rnYjixXFgIBNC5iveZVd7.vry/LvLdfTQM1MvBWLp//psZWTtIMMO');
INSERT INTO `crud-data`.`users` (`username`, `email`, `password`) VALUES ('firas', 'firas@gmail.com', '$2a$10$rnYjixXFgIBNC5iveZVd7.vry/LvLdfTQM1MvBWLp//psZWTtIMMO');

INSERT INTO `crud-data`.`user_roles` (`role_id`, user_id) VALUES (1 , 1);
INSERT INTO `crud-data`.`user_roles` (`role_id`, user_id) VALUES (1 , 2);
INSERT INTO `crud-data`.`user_roles` (`role_id`, user_id) VALUES (2 , 3);
INSERT INTO `crud-data`.`user_roles` (`role_id`, user_id) VALUES (2 , 4);
INSERT INTO `crud-data`.`user_roles` (`role_id`, user_id) VALUES (2 , 5);

INSERT INTO `crud-data`.`warehouse` (`created_on`, `created_by`, `description`, `name`)
VALUES ('2024-05-10 22:20:24.557165', 'admin', 'New One', 'Toy Store');

INSERT INTO `crud-data`.`warehouse` (`created_on`, `created_by`, `description`, `name`)
VALUES ('2024-01-01 22:40:24.557165', 'admin', 'Books and Stationery', 'Book Haven');

INSERT INTO `crud-data`.`warehouse` (`created_on`, `created_by`, `description`, `name`)
VALUES ('2024-02-01 22:50:24.557165', 'malak', 'Groceries and Daily Needs', 'Grocery Store');

INSERT INTO `crud-data`.`warehouse` (`created_on`, `created_by`, `description`, `name`)
VALUES ('2024-02-15 23:00:24.557165', 'malak', 'Furniture and Home Decor (No items empty warehouse)', 'Home Comforts');

INSERT INTO `crud-data`.`warehouse` (`created_on`, `created_by`, `description`, `name`)
VALUES ('2024-4-16 23:10:24.557165', 'malak', 'Clothing and Accessories', 'Fashion Outlet');

INSERT INTO `crud-data`.`warehouse` (`created_on`, `created_by`, `description`, `name`)
VALUES ('2023-12-05 23:20:24.557165', 'malak', 'Sports and Outdoors', 'Sporting Goods');


INSERT INTO `crud-data`.`item` (`warehouse_id`, `description`, `name`, `quantity`)
VALUES ('1', 'Remote controlled car', 'RC Car', '15');

INSERT INTO `crud-data`.`item` (`warehouse_id`, `description`, `name`, `quantity`)
VALUES ('1', 'Building blocks set', 'Lego Set', '25');

INSERT INTO `crud-data`.`item` (`warehouse_id`, `description`, `name`, `quantity`)
VALUES ('1', 'Stuffed teddy bear', 'Teddy Bear', '30');

INSERT INTO `crud-data`.`item` (`warehouse_id`, `description`, `name`, `quantity`)
VALUES ('2', 'A popular fantasy novel', 'The Hobbit', '50');

INSERT INTO `crud-data`.`item` (`warehouse_id`, `description`, `name`, `quantity`)
VALUES ('3', 'Fresh Apples', 'Apples', '100');

INSERT INTO `crud-data`.`item` (`warehouse_id`, `description`, `name`, `quantity`)
VALUES ('3', 'Organic Milk', 'Milk', '75');

INSERT INTO `crud-data`.`item` (`warehouse_id`, `description`, `name`, `quantity`)
VALUES ('3', 'Whole Wheat Bread', 'Bread', '80');

INSERT INTO `crud-data`.`item` (`warehouse_id`, `description`, `name`, `quantity`)
VALUES ('3', 'Farm Fresh Eggs', 'Eggs', '120');

INSERT INTO `crud-data`.`item` (`warehouse_id`, `description`, `name`, `quantity`)
VALUES ('3', 'Crisp Lettuce', 'Lettuce', '60');

INSERT INTO `crud-data`.`item` (`warehouse_id`, `description`, `name`, `quantity`)
VALUES ('3', 'Ground Coffee', 'Coffee', '90');

INSERT INTO `crud-data`.`item` (`warehouse_id`, `description`, `name`, `quantity`)
VALUES ('3', 'Juicy Oranges', 'Oranges', '110');

INSERT INTO `crud-data`.`item` (`warehouse_id`, `description`, `name`, `quantity`)
VALUES ('5', 'Elegant Dress', 'Evening Gown', '20');

INSERT INTO `crud-data`.`item` (`warehouse_id`, `description`, `name`, `quantity`)
VALUES ('5', 'Stylish Sunglasses', 'Designer Shades', '30');

INSERT INTO `crud-data`.`item` (`warehouse_id`, `description`, `name`, `quantity`)
VALUES ('6', 'Basketball', 'Spalding Basketball', '25');

INSERT INTO `crud-data`.`item` (`warehouse_id`, `description`, `name`, `quantity`)
VALUES ('6', 'Yoga Mat', 'Premium Yoga Mat', '40');

INSERT INTO `crud-data`.`item` (`warehouse_id`, `description`, `name`, `quantity`)
VALUES ('6', 'Running Shoes', 'Nike Running Shoes', '35');

INSERT INTO `crud-data`.`item` (`warehouse_id`, `description`, `name`, `quantity`)
VALUES ('6', 'Tennis Racket', 'Wilson Tennis Racket', '30');

INSERT INTO `crud-data`.`document` (`status`, `created_on`, `item_id`, `warehouse_id`, `created_by`, `supply_document_name`, `supply_document_subject`)
VALUES ('0', '2024-06-01 09:30:00', '1', '1', 'zaid', 'Invoice_001', 'Purchase Order');

INSERT INTO `crud-data`.`document` (`status`, `created_on`, `item_id`, `warehouse_id`, `created_by`, `supply_document_name`, `supply_document_subject`)
VALUES ('0', '2024-05-15 14:45:00', '2', '1', 'sara', 'Receipt_002', 'Delivery Receipt');

INSERT INTO `crud-data`.`document` (`status`, `created_on`, `item_id`, `warehouse_id`, `created_by`, `supply_document_name`, `supply_document_subject`)
VALUES ('0', '2024-05-20 11:00:00', '3', '1', 'firas', 'Invoice_003', 'Purchase Order');

INSERT INTO `crud-data`.`document` (`status`, `created_on`, `item_id`, `warehouse_id`, `created_by`, `supply_document_name`, `supply_document_subject`)
VALUES ('0', '2024-04-02 16:20:00', '4', '2', 'zaid', 'Receipt_004', 'Delivery Receipt');

INSERT INTO `crud-data`.`document` (`status`, `created_on`, `item_id`, `warehouse_id`, `created_by`, `supply_document_name`, `supply_document_subject`)
VALUES ('2', '2024-06-01 09:30:00', '5', '3', 'sara', 'Invoice_005', 'Purchase Order');

INSERT INTO `crud-data`.`document` (`status`, `created_on`, `item_id`, `warehouse_id`, `created_by`, `supply_document_name`, `supply_document_subject`)
VALUES ('0', '2024-05-15 14:45:00', '6', '3', 'firas', 'Receipt_006', 'Delivery Receipt');

INSERT INTO `crud-data`.`document` (`status`, `created_on`, `item_id`, `warehouse_id`, `created_by`, `supply_document_name`, `supply_document_subject`)
VALUES ('2', '2024-05-20 11:00:00', '7', '3', 'zaid', 'Invoice_007', 'Purchase Order');

INSERT INTO `crud-data`.`document` (`status`, `created_on`, `item_id`, `warehouse_id`, `created_by`, `supply_document_name`, `supply_document_subject`)
VALUES ('2', '2024-04-02 16:20:00', '8', '3', 'sara', 'Receipt_008', 'Delivery Receipt');

INSERT INTO `crud-data`.`document` (`status`, `created_on`, `item_id`, `warehouse_id`, `created_by`, `supply_document_name`, `supply_document_subject`)
VALUES ('0', '2024-06-01 09:30:00', '9', '3', 'firas', 'Invoice_009', 'Purchase Order');

INSERT INTO `crud-data`.`document` (`status`, `created_on`, `item_id`, `warehouse_id`, `created_by`, `supply_document_name`, `supply_document_subject`)
VALUES ('2', '2024-05-15 14:45:00', '10', '5', 'zaid', 'Receipt_010', 'Delivery Receipt');

INSERT INTO `crud-data`.`document` (`status`, `created_on`, `item_id`, `warehouse_id`, `created_by`, `supply_document_name`, `supply_document_subject`)
VALUES ('0', '2024-05-20 11:00:00', '11', '5', 'sara', 'Invoice_011', 'Purchase Order');

INSERT INTO `crud-data`.`document` (`status`, `created_on`, `item_id`, `warehouse_id`, `created_by`, `supply_document_name`, `supply_document_subject`)
VALUES ('1', '2024-04-02 16:20:00', '12', '6', 'firas', 'Receipt_012', 'Delivery Receipt');

INSERT INTO `crud-data`.`document` (`status`, `created_on`, `item_id`, `warehouse_id`, `created_by`, `supply_document_name`, `supply_document_subject`)
VALUES ('1', '2024-06-01 09:30:00', '13', '6', 'zaid', 'Invoice_013', 'Purchase Order');

INSERT INTO `crud-data`.`document` (`status`, `created_on`, `item_id`, `warehouse_id`, `created_by`, `supply_document_name`, `supply_document_subject`)
VALUES ('0', '2024-05-15 14:45:00', '14', '6', 'sara', 'Receipt_014', 'Delivery Receipt');

INSERT INTO `crud-data`.`document` (`status`, `created_on`, `item_id`, `warehouse_id`, `created_by`, `supply_document_name`, `supply_document_subject`)
VALUES ('0', '2024-05-20 11:00:00', '15', '6', 'firas', 'Invoice_015', 'Purchase Order');

INSERT INTO `crud-data`.`document` (`status`, `created_on`, `item_id`, `warehouse_id`, `created_by`, `supply_document_name`, `supply_document_subject`)
VALUES ('0', '2024-04-02 16:20:00', '16', '6', 'zaid', 'Receipt_016', 'Delivery Receipt');

INSERT INTO `crud-data`.`document` (`status`, `created_on`, `item_id`, `warehouse_id`, `created_by`, `supply_document_name`, `supply_document_subject`)
VALUES ('0', '2024-06-01 09:30:00', '17', '6', 'sara', 'Invoice_017', 'Purchase Order');





