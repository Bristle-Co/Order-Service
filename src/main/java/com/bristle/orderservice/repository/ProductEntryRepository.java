package com.bristle.orderservice.repository;

import com.bristle.orderservice.model.ProductEntryEntity;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductEntryRepository extends JpaRepository<ProductEntryEntity, String> {
    @Query(value = "DELETE FROM " + ProductEntryEntity.TABLE_NAME + " WHERE " +
            ProductEntryEntity.COLM_ORDER_ID_FK + " = ?1", nativeQuery = true)
    @Modifying
    void deleteProductEntryEntitiesByOrderIdFk(Integer orderIdFk);

    @Query(value = "SELECT " + ProductEntryEntity.COLM_PRODUCT_ENTRY_ID + " FROM " + ProductEntryEntity.TABLE_NAME +
            " WHERE " + ProductEntryEntity.COLM_ORDER_ID_FK + " = ?1", nativeQuery = true)
    List<String> getProductEntriesIdsByOrderId(Integer orderIdFk);

    @Query(value = "REPLACE INTO " + ProductEntryEntity.TABLE_NAME + " (" +
            ProductEntryEntity.COLM_PRODUCT_ENTRY_ID + ", " +
            ProductEntryEntity.COLM_PRODUCT_TICKET_ID + ", " +
            ProductEntryEntity.COLM_ORDER_ID_FK + ", " +
            ProductEntryEntity.COLM_PRICE + ", " +
            ProductEntryEntity.COLM_MODEL + ", " +
            ProductEntryEntity.COLM_QUANTITY + ") VALUES (?1, ?2, ?3, ?4, ?5, ?6)",
            nativeQuery = true)
    @Modifying
    void replaceProductEntryEntitiesById(String productEntryId,
                                         String productionTicketId,
                                         Integer orderIdFk,
                                         Integer price,
                                         String model,
                                         Integer quantity);

    @Query(value = "DELETE FROM " + ProductEntryEntity.TABLE_NAME + " WHERE " +
            ProductEntryEntity.COLM_PRODUCT_ENTRY_ID + " IN (?1)", nativeQuery = true)
    @Modifying
    void deleteProductEntryEntitiesById(List<String> productEntryIds);
}
