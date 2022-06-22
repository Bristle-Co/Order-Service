package com.bristle.orderservice.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

import static com.bristle.orderservice.model.OrderEntity.COLM_ORDER_ID;
import static com.bristle.orderservice.model.ProductEntryEntity.COLM_MODEL;

@Embeddable
public class ProductEntryPK implements Serializable {

    @Column(name = COLM_MODEL, nullable = false)
    private String model;

    // foreign key
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = COLM_ORDER_ID, referencedColumnName = COLM_ORDER_ID, nullable = false)
    private OrderEntity order;

    public ProductEntryPK() {
    }

    public ProductEntryPK(String model, OrderEntity order) {
        this.model = model;
        this.order = order;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntryPK that = (ProductEntryPK) o;
        return Objects.equals(model, that.model) && Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, order);
    }
}
