package com.bristle.orderservice.service;

import com.bristle.orderservice.converter.ProductEntryEntityConverter;
import com.bristle.orderservice.model.ProductEntryEntity;
import com.bristle.orderservice.repository.ProductEntryRepository;
import com.bristle.proto.order.ProductEntry;
import com.bristle.proto.order.ProductEntryFilterField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductEntryService {

    private final ProductEntryRepository m_productEntryRepository;

    private final ProductEntryEntityConverter m_productEntryConverter;

    Logger log = LoggerFactory.getLogger(ProductEntryService.class);

    public ProductEntryService(ProductEntryRepository m_productEntryRepository, ProductEntryEntityConverter m_productEntryConverter) {
        this.m_productEntryRepository = m_productEntryRepository;
        this.m_productEntryConverter = m_productEntryConverter;
    }

    @Transactional(readOnly = true)
    public List<ProductEntry> getProductEntries(ProductEntryFilterField field, String productEntryId) throws Exception {
        List<ProductEntry> rs;
        switch (field) {
            case PRODUCT_ENTRY_ID:
                Optional<ProductEntryEntity> productEntry = m_productEntryRepository.findById(productEntryId);
                rs = productEntry.map(productEntryEntity ->
                        Arrays.asList(m_productEntryConverter.entityToProto(productEntryEntity)))
                        .orElse(Collections.emptyList());
                break;
            case UNASSIGNED:
                rs = m_productEntryRepository.getUnAssignedProductEntries()
                        .stream()
                        .map(m_productEntryConverter::entityToProto)
                        .collect(Collectors.toList());
                break;

            default:
                throw new Exception("Unknown product entry filter field");

        }
        return rs;
    }

    @Transactional
    public ProductEntry patchProductionTicketInfoOfProductEntry(String productEntryId, String productTicketId) throws Exception {
        // productTicketId can be null
        m_productEntryRepository.updateProductTicketIdByProductEntryId(productEntryId, productTicketId);
        Optional<ProductEntryEntity> updatedProductEntry = m_productEntryRepository.findById(productEntryId);
        if (!updatedProductEntry.isPresent()) {
            throw new Exception("patchProductionTicketInfoOfProductEntry failure, can't find product entry with id " + productEntryId);
        } else {
            return m_productEntryConverter.entityToProto(updatedProductEntry.get());
        }
    }
}
