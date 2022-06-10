package com.bristle.orderservice.model;

import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

// This class is used for database definition in relational database
// The protobuf generated class "Customer" shoud map to this class
// then stored into MySQL/MariaDB

// Note that only the  customer_id(客戶代號) which is the primary key
// has to be non-null

@Entity(name = "orders")
public class OrderEntity {

    // Table name
    public static final String TABLE_NAME = "orders";

    // Column names, reusable from outside of class
    // COLM for column
    public static final String COLM_ORDER_ID = "order_id";
    public static final String COLM_CUSTOMER_ORDER_ID = "customer_order_id";
    public static final String COLM_CUSTOMER_ID= "customer_id";
    public static final String COLM_DUE_DATE = "due_date";
    public static final String COLM_NOTE = "note";
    public static final String COLM_FINISHED_ISSUING_TICKETS_AT= "finished_issuing_tickets_at";


    @Id
    @NonNull
    @Column(name = COLM_ORDER_ID)
    String orderID;

    @Column(name = COLM_CUSTOMER_ORDER_ID)
    String customerOderId;

    @Column(name = COLM_CUSTOMER_ID)
    String customerId;

    @Column(name = COLM_DUE_DATE)
    Date dueDate;

    @Column(name = COLM_NOTE)
    String note;

    @Column(name = COLM_FINISHED_ISSUING_TICKETS_AT)
    LocalDateTime finishedIssuingTicketsAt;

}

