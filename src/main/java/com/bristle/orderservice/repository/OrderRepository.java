package com.bristle.orderservice.repository;

import com.bristle.orderservice.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer>,
        JpaSpecificationExecutor<OrderEntity> {

    void deleteOrderEntityByOrderId(Integer orderId);

}
