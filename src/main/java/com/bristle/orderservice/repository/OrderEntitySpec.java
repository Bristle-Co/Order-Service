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

    public static Specification<OrderEntity> likeCustomerId (String customerId){
        if (customerId == null) return null;

        // use LIKE here for convenience for my mom
        // minimize the number of fields that uses LIKE instead of "=" cuz it is 10 times more expensive
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("customerId"), "%"+customerId+"%"));
    }

    public static Specification<OrderEntity> dueDateBetween (Date from, Date to){
        if (from == null || to == null) return null;
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("dueDate"),from,to));
    }

    public static Specification<OrderEntity> issuedBetween (LocalDateTime from, LocalDateTime to){
        if (from == null || to == null) return null;
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("issuedAt"), from, to));
    }
}
