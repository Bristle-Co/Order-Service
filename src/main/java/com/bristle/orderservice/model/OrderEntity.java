package com.bristle.orderservice.model;

import org.springframework.context.annotation.Primary;
import org.springframework.lang.NonNull;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.sql.Date;

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

    // This is simply a auto incrementing integer
    // When displaying we concatenate it with the prefix "BR"
    // ex: BR1, BR5, BR888
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = COLM_ORDER_ID)
    int orderID;

    // There is a unique order Id we get from customer in a order
    // however the format of this string is different from customer to customer
    // thus we can not use it as primary key
    @Column(name = COLM_CUSTOMER_ORDER_ID, nullable = true)
    String customerOderId;

    // Foreign key to customers table
    @Column(name = COLM_CUSTOMER_ID, nullable = true)
    String customerId;

    // This is the initial estimated due date
    @Column(name = COLM_DUE_DATE, nullable = true)
    Date dueDate;

    @Column(name = COLM_NOTE, nullable = true)
    String note;

    // If this field is null that means not all the production tickets
    // under this order has been assigned
    // we then can
    @Column(name = COLM_FINISHED_ISSUING_TICKETS_AT, nullable = true)
    LocalDateTime finishedIssuingTicketsAt;

    public OrderEntity() {
    }

    // This constructor is used for initial initialization of a order
    // order_id is auto increment so no need to pass it in
    // also a order ticket will not have production tickets assigned so no timestamp added
    public OrderEntity(String customerOderId, String customerId, Date dueDate, String note, LocalDateTime finishedIssuingTicketsAt) {
        this.customerOderId = customerOderId;
        this.customerId = customerId;
        this.dueDate = dueDate;
        this.note = note;
        this.finishedIssuingTicketsAt = finishedIssuingTicketsAt;
    }

    // Lombok could work well here but I don't wanna use it lol
    // It doesn't support new version of intellij
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getCustomerOderId() {
        return customerOderId;
    }

    public void setCustomerOderId(String customerOderId) {
        this.customerOderId = customerOderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getFinishedIssuingTicketsAt() {
        return finishedIssuingTicketsAt;
    }

    public void setFinishedIssuingTicketsAt(LocalDateTime finishedIssuingTicketsAt) {
        this.finishedIssuingTicketsAt = finishedIssuingTicketsAt;
    }
}

