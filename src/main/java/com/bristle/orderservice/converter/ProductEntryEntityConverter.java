package com.bristle.orderservice.converter;

import com.bristle.orderservice.model.OrderEntity;
import com.bristle.orderservice.model.ProductEntryEntity;
import com.bristle.proto.order.ProductEntry;
import org.springframework.stereotype.Component;

@Component
public class ProductEntryEntityConverter {

    public ProductEntryEntity protoToEntity(ProductEntry productEntryProto, OrderEntity orderEntity) {
        // whenever we load proto back to entity we need to make sure
        // each item in ProductEntryEntityList references the OrderEntity object
        // this way Hibernate knows what the foreign key is
        String productEntryId = productEntryProto.getProductEntryId();
        String model = productEntryProto.getModel();
        int quantity = productEntryProto.getQuantity();
        int price = productEntryProto.getPrice();
        String productionTicketId = productEntryProto.getProductTicketId();

        return new ProductEntryEntity(
                productEntryId,
                model.equals("") ? null : model,
                // protobuf3 numeric type default value is 0
                // but there might be times where we actually want to 0 for special cases and store it in db
                // thus we define -2,147,483,648 ( 0x80000000 ) to be null
                quantity == Integer.MIN_VALUE ? null : quantity,
                price == Integer.MIN_VALUE ? null : price,
                productionTicketId.equals("") ? null : productionTicketId,
                orderEntity
        );
    }

    public ProductEntry entityToProto(ProductEntryEntity productEntryEntity) {

        String model = productEntryEntity.getModel();
        Integer quantity = productEntryEntity.getQuantity();
        Integer price = productEntryEntity.getPrice();
        String productionTicketId = productEntryEntity.getProductTicket_id();
        return ProductEntry.newBuilder()
                .setModel(model == null ? "" : model)
                .setQuantity(quantity == null ? Integer.MIN_VALUE : quantity)
                .setPrice(price == null ? Integer.MIN_VALUE : price)
                .setProductTicketId(productionTicketId == null ? "" : productionTicketId)
                .build();
    }
}
