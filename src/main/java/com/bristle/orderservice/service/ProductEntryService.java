package com.bristle.orderservice.service;

import com.bristle.orderservice.converter.OrderEntityConverter;
import com.bristle.orderservice.converter.ProductEntryEntityConverter;
import com.bristle.orderservice.model.ProductEntryEntity;
import com.bristle.orderservice.repository.ProductEntryRepository;
import com.bristle.proto.order.ProductEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<ProductEntry> getUnAssignedProductEntries() {
        List<ProductEntryEntity> rs = m_productEntryRepository.getUnAssignedProductEntries();
        return rs.stream().map(m_productEntryConverter::entityToProto).collect(Collectors.toList());
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
