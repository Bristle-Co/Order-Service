package com.bristle.orderservice.repository;

import com.bristle.orderservice.model.OrderEntity;
import io.grpc.netty.shaded.io.netty.util.concurrent.OrderedEventExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer>,
        JpaSpecificationExecutor<OrderEntity> {

    @Query(value = "SELECT * FROM "+OrderEntity.TABLE_NAME+" WHERE "+OrderEntity.COLM_ORDER_ID+" = ?1",
            nativeQuery = true)
    OrderEntity findOrderEntityByOrderId(Integer orderId);
}
