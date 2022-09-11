CREATE TABLE IF NOT EXISTS orders
(
    order_id          int NOT NULL AUTO_INCREMENT,
    customer_id       varchar(255) DEFAULT NULL,
    customer_order_id varchar(255) DEFAULT NULL,
    delivered_at      datetime(6)  DEFAULT NULL,
    due_date          date         DEFAULT NULL,
    issued_at         datetime(6)  DEFAULT NULL,
    note              varchar(255) DEFAULT NULL,
    PRIMARY KEY (order_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


CREATE TABLE product_entries
(
    product_entry_id  varchar(255) NOT NULL,
    model             varchar(255) DEFAULT NULL,
    price             int          DEFAULT NULL,
    product_ticket_id int          DEFAULT NULL,
    quantity          int          DEFAULT NULL,
    order_id_fk       int          DEFAULT NULL,
    PRIMARY KEY (product_entry_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

