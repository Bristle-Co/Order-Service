package com.bristle.orderservice.service;


import com.bristle.orderservice.converter.OrderEntityConverter;
import com.bristle.orderservice.model.OrderEntity;
import com.bristle.orderservice.model.ProductEntryEntity;
import com.bristle.orderservice.repository.OrderRepository;
import com.bristle.orderservice.repository.ProductEntryRepository;
import com.bristle.proto.order.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository m_orderRepository;

    private final ProductEntryRepository m_productEntryRepository;

    private final OrderEntityConverter m_orderConverter;

    Logger log = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    public OrderService(OrderRepository m_orderRepository,
                        OrderEntityConverter orderConverter,
                        ProductEntryRepository productEntryRepository) {
        this.m_orderRepository = m_orderRepository;
        this.m_orderConverter = orderConverter;
        this.m_productEntryRepository = productEntryRepository;
    }

    @Transactional
    public Order upsertOrder(Order orderProto) throws Exception {
        OrderEntity orderEntity = m_orderConverter.protoToEntity(orderProto);
        m_orderRepository.save(orderEntity);
        List<ProductEntryEntity> toBeUpserted = orderEntity.getProductEntries();
        m_productEntryRepository.saveAll(toBeUpserted);
        return orderProto;
    }


}
