package com.bristle.orderservice.repository;

import com.bristle.orderservice.model.ProductEntryEntity;
import com.bristle.orderservice.model.ProductEntryPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductEntryRepository extends JpaRepository<ProductEntryEntity, ProductEntryPK> {

}
