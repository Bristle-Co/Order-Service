package com.bristle.orderservice.model;

import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

// This table has many to one relationship with the orders table
// One order could have many models(規格)
@Entity(name = "ordered_product_models")
public class OrderedProductModelEntity {

    // Table name
    public static final String TABLE_NAME = "ordered_product_models";

    // Column names, reusable from outside of class
    // COLM for column
    public static final String COLM_MODEL_ID = "model_id";
    public static final String COLM_QUANTITY = "quantity";
    public static final String COLM_PRICE= "price";
    public static final String COLM_MODEL = "model";
    public static final String COLM_ORDER_ID = "order_id";

    @Id
    @NonNull
    @Column(name = COLM_MODEL_ID)
    int modelID;

    @Column(name = COLM_QUANTITY)
    int numOfItems;

    @Column(name = COLM_PRICE)
    int price;

    @Column(name = COLM_MODEL)
    @NonNull
    String model;

    @Column(name = COLM_ORDER_ID)
    @NonNull
    String orderId;

}
