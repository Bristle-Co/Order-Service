package com.bristle.orderservice.converter;

import com.bristle.orderservice.model.OrderEntity;
import com.bristle.orderservice.model.ProductEntryEntity;
import com.bristle.proto.order.ProductEntry;
import org.springframework.stereotype.Component;

@Component
public class ProductEntryEntityConverter {

    public ProductEntryEntity protoToEntity(ProductEntry productEntryProto, OrderEntity orderEntity) {
        // whenever we load proto back to entity to be save to db we need to make sure
        // each item in ProductEntryEntityList references the OrderEntity object
        // this way Hibernate knows what the foreign key is

        return new ProductEntryEntity(
                // product entry id is never ever null, assigne in api gateway controller
                productEntryProto.getProductEntryId(),
                productEntryProto.getModel().equals("") ? null : productEntryProto.getModel(),
                // protobuf3 numeric type default value is 0
                // but there might be times where we actually want to 0 for special cases and store it in db
                // thus we define -2,147,483,648 ( 0x80000000 ) to be null
                productEntryProto.getQuantity() == Integer.MIN_VALUE ? null : productEntryProto.getQuantity(),
                productEntryProto.getPrice() == Integer.MIN_VALUE ? null : productEntryProto.getPrice(),
                productEntryProto.getProductTicketId() == Integer.MIN_VALUE ? null : productEntryProto.getProductTicketId(),
                orderEntity
        );
    }

    public ProductEntry entityToProto(ProductEntryEntity productEntryEntity, OrderEntity orderEntity) {

        return ProductEntry.newBuilder()
                // product entry id is never ever null
                .setProductEntryId(productEntryEntity.getProductEntryId())
                .setModel(productEntryEntity.getModel() == null ? "" : productEntryEntity.getModel())
                .setQuantity(productEntryEntity.getQuantity() == null ? Integer.MIN_VALUE : productEntryEntity.getQuantity())
                .setPrice(productEntryEntity.getPrice() == null ? Integer.MIN_VALUE : productEntryEntity.getPrice())
                .setProductTicketId(productEntryEntity.getProductTicketId() == null ? Integer.MIN_VALUE : productEntryEntity.getProductTicketId())
                // orderId is always non null when get data from and converting into proto
                .setOrderId(orderEntity.getOrderId())
                .build();
    }

    // used for getUnassignedProductEntries endpoint
    public ProductEntry entityToProto(ProductEntryEntity productEntryEntity) {

        return ProductEntry.newBuilder()
                // product entry id is never ever null
                .setProductEntryId(productEntryEntity.getProductEntryId())
                .setModel(productEntryEntity.getModel() == null ? "" : productEntryEntity.getModel())
                .setQuantity(productEntryEntity.getQuantity() == null ? Integer.MIN_VALUE : productEntryEntity.getQuantity())
                .setPrice(productEntryEntity.getPrice() == null ? Integer.MIN_VALUE : productEntryEntity.getPrice())
                .setProductTicketId(productEntryEntity.getProductTicketId() == null ? Integer.MIN_VALUE : productEntryEntity.getProductTicketId())
                .setOrderId(productEntryEntity.getOrder().getOrderId())
                .setCustomerId(productEntryEntity.getOrder().getCustomerId())
                .build();
    }
}
