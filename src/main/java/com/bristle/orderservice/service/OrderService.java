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
        OrderEntity i = m_orderConverter.protoToEntity(orderProto);
        log.info("print: "+i.toString());
        // id column is generated, make sure it is null when inserted
        // else hiberante does a select statement first and our one to many relationship
        // screws it up
        i.setOrderID(null);
        OrderEntity orderEntity = new OrderEntity(null, null, null, null, null, null, null);
        ProductEntryEntity productEntryEntity = new ProductEntryEntity("model",null, null, null, i);
        i.setProductEntries(Arrays.asList(productEntryEntity));
        m_orderRepository.save(i);
        return orderProto;
    }


}
