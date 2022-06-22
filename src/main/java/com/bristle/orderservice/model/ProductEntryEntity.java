package com.bristle.orderservice.model;

import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static com.bristle.orderservice.model.OrderEntity.COLM_ORDER_ID;

// This table has many-to-one relationship with the orders table
// One order could have many product entries(規格)
@Entity(name = "product_entries")
public class ProductEntryEntity {

    public static final String TABLE_NAME = "product_entries";

    public static final String COLM_QUANTITY = "quantity";
    public static final String COLM_PRICE= "price";
    public static final String COLM_MODEL = "model";
    public static final String COLM_PRODUCT_TICKET_ID= "product_ticket_id";

    @EmbeddedId
    private ProductEntryPK productEntryPK;

    @Column(name = COLM_QUANTITY)
    private int quantity;

    @Column(name = COLM_PRICE)
    private int price;

    // If this field is null that means this product entry
    // has not been made into a product ticket
    @Column(name = COLM_PRODUCT_TICKET_ID, nullable = true)
    private String productTicket_id;

    public ProductEntryEntity() {
    }

    public ProductEntryPK getProductEntryPK() {
        return productEntryPK;
    }

    public void setProductEntryPK(ProductEntryPK productEntryPK) {
        this.productEntryPK = productEntryPK;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProductTicket_id() {
        return productTicket_id;
    }

    public void setProductTicket_id(String productTicket_id) {
        this.productTicket_id = productTicket_id;
    }
}
