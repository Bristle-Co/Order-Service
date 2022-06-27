package com.bristle.orderservice.repository;

import com.bristle.orderservice.model.OrderEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class OrderEntitySpec {

    public static Specification<OrderEntity> equalOrderId (Integer orderId){
        if (orderId == null) return null;
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("orderId"), orderId));
        }

    public static Specification<OrderEntity> equalCustomerOrderId (String customerOrderId){
        if (customerOrderId == null) return null;
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("customerOrderId"), customerOrderId));
    }

    public static Specification<OrderEntity> equalCustomerId (String customerId){
        if (customerId == null) return null;
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("customerId"), customerId));
    }

    public static Specification<OrderEntity> dueDateBetween (Date from, Date to){
        if (from == null || to == null) return null;
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("dueDate"),from,to));
    }

    public static Specification<OrderEntity> issuedAfter (LocalDateTime issuedAt){
        if (issuedAt == null) return null;
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("issuedAt"), issuedAt));
    }
}
