package com.bristle.orderservice.repository;

import com.bristle.orderservice.model.ProductEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductEntryRepository extends JpaRepository<ProductEntryEntity, String> {
    @Query(value = "DELETE FROM "+ProductEntryEntity.TABLE_NAME+" WHERE "+
            ProductEntryEntity.COLM_ORDER_ID_FK+" = ?1", nativeQuery = true)
    @Modifying
    void deleteProductEntryEntitiesByOrderIdFk(Integer orderIdFk);
}
