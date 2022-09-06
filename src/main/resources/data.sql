INSERT INTO `orders` (`order_id`,`customer_id`,`customer_order_id`,`delivered_at`,`due_date`,`issued_at`,`note`) VALUES (1,'千得U-008','PO 1',NULL,'2022-01-01','2022-01-01 22:05:15.000000','note1');
INSERT INTO `orders` (`order_id`,`customer_id`,`customer_order_id`,`delivered_at`,`due_date`,`issued_at`,`note`) VALUES (2,'千厚U-008','PO 2',NULL,'2022-02-02','2022-02-02 22:04:15.000000','note2');
INSERT INTO `orders` (`order_id`,`customer_id`,`customer_order_id`,`delivered_at`,`due_date`,`issued_at`,`note`) VALUES (3,'良勁U-054','PO 3',NULL,'2022-03-03','2022-03-03 23:32:35.000000','note3');
INSERT INTO `orders` (`order_id`,`customer_id`,`customer_order_id`,`delivered_at`,`due_date`,`issued_at`,`note`) VALUES (4,'緯創U-017','PO 4',NULL,'2022-04-04','2022-04-04 23:32:37.000000','note4');
INSERT INTO `orders` (`order_id`,`customer_id`,`customer_order_id`,`delivered_at`,`due_date`,`issued_at`,`note`) VALUES (5,'達創U-017','PO 5',NULL,'2022-05-05','2022-05-05 23:32:38.000000','note5');
INSERT INTO `orders` (`order_id`,`customer_id`,`customer_order_id`,`delivered_at`,`due_date`,`issued_at`,`note`) VALUES (6,'盛源U-028','PO 6',NULL,'2022-06-06','2022-06-06 23:32:39.000000','note6');
INSERT INTO `orders` (`order_id`,`customer_id`,`customer_order_id`,`delivered_at`,`due_date`,`issued_at`,`note`) VALUES (7,'鴻盈U-009','PO 7',NULL,'2022-07-07','2022-07-07 23:32:40.000000','note7');

INSERT INTO `product_entries` (`product_entry_id`,`model`,`price`,`product_ticket_id`,`quantity`,`order_id_fk`) VALUES ('24fabd51-2eb8-40f7-9a4f-6e14201433f5','model 11',1000,NULL,1,1);
INSERT INTO `product_entries` (`product_entry_id`,`model`,`price`,`product_ticket_id`,`quantity`,`order_id_fk`) VALUES ('2b7ae587-6089-4fe4-8242-879afb508e8d','model 12',1000,NULL,1,1);
INSERT INTO `product_entries` (`product_entry_id`,`model`,`price`,`product_ticket_id`,`quantity`,`order_id_fk`) VALUES ('3a3b3d7e-4297-485a-a012-f4d8c4bd5bdc','model 3',2000,NULL,2,2);
INSERT INTO `product_entries` (`product_entry_id`,`model`,`price`,`product_ticket_id`,`quantity`,`order_id_fk`) VALUES ('3e8e1456-1666-4f9e-8f55-8766f2212359','model 4',2000,NULL,2,2);
INSERT INTO `product_entries` (`product_entry_id`,`model`,`price`,`product_ticket_id`,`quantity`,`order_id_fk`) VALUES ('4cfa15c9-f066-400d-92ea-7eff69825d67','model 5',3000,NULL,3,3);
INSERT INTO `product_entries` (`product_entry_id`,`model`,`price`,`product_ticket_id`,`quantity`,`order_id_fk`) VALUES ('4f950d54-591d-49be-bb7a-a48950cbfa77','model 6',3000,NULL,3,3);
INSERT INTO `product_entries` (`product_entry_id`,`model`,`price`,`product_ticket_id`,`quantity`,`order_id_fk`) VALUES ('5475c400-3740-4936-8bb1-dcc6ba3ae33e','model 7',4000,NULL,4,4);
INSERT INTO `product_entries` (`product_entry_id`,`model`,`price`,`product_ticket_id`,`quantity`,`order_id_fk`) VALUES ('5b0b8ed9-5765-4f91-a420-32d6cb2bb906','model 8',4000,NULL,4,4);
INSERT INTO `product_entries` (`product_entry_id`,`model`,`price`,`product_ticket_id`,`quantity`,`order_id_fk`) VALUES ('6e865335-f864-4261-ae6d-0ed186e4714c','model 9',5000,NULL,5,5);
INSERT INTO `product_entries` (`product_entry_id`,`model`,`price`,`product_ticket_id`,`quantity`,`order_id_fk`) VALUES ('b2e50e28-2b6a-4ba9-8377-5cefce40395c','model 10',5000,NULL,5,5);
INSERT INTO `product_entries` (`product_entry_id`,`model`,`price`,`product_ticket_id`,`quantity`,`order_id_fk`) VALUES ('bef7fbb4-4962-4732-98ef-1f723074bba5','model 11',6000,NULL,6,6);
INSERT INTO `product_entries` (`product_entry_id`,`model`,`price`,`product_ticket_id`,`quantity`,`order_id_fk`) VALUES ('cdf50e10-05b6-4bee-820f-99a665f8a661','model 12',6000,NULL,6,6);
INSERT INTO `product_entries` (`product_entry_id`,`model`,`price`,`product_ticket_id`,`quantity`,`order_id_fk`) VALUES ('ee4b8e06-7bf9-4c71-a233-ee8afb27f6ee','model 13',7000,NULL,7,7);
INSERT INTO `product_entries` (`product_entry_id`,`model`,`price`,`product_ticket_id`,`quantity`,`order_id_fk`) VALUES ('ad00d30b-b2db-47c2-bed4-f1777a877095','model 14',7000,NULL,7,7);
