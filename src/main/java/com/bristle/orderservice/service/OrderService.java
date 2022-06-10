package com.bristle.orderservice.service;


import com.bristle.orderservice.model.OrderEntity;
import com.bristle.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository m_orderRepository;

    @Autowired
    public OrderService(OrderRepository m_orderRepository) {
        this.m_orderRepository = m_orderRepository;
    }

    @Transactional
    public void addOrder(OrderEntity orderEntity) throws SQLException {
        m_orderRepository.save(orderEntity);
    }

    @Transactional(readOnly = true)
    public List<OrderEntity> getOrdersByLimitAndOffset(int limit, int offset) throws SQLException {
        return m_orderRepository.getOrdersByLimitAndOffset(limit, offset);
    }
}
