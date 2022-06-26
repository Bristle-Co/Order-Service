package com.bristle.orderservice.repository;

import com.bristle.orderservice.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    @Query(value = "SELECT * FROM " + OrderEntity.TABLE_NAME +
            " ORDER BY " + OrderEntity.COLM_ORDER_ID +
            " DESC LIMIT ?1 OFFSET ?2 ;" , nativeQuery = true)
    List<OrderEntity> getOrdersByLimitAndOffset(int limit, int offset);

    //List<OrderEntity> getOrderEntitiesByColmCustomerIdAnd
}
