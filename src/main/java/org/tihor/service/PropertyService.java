package org.tihor.service;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tihor.entity.CustomerEntity;
import org.tihor.mapper.PropertyLendingPartnerMapper;
import org.tihor.mapper.PropertyMapper;
import org.tihor.model.request.PropertyRequest;
import org.tihor.model.response.PropertyResponse;
import org.tihor.repository.PropertyLendingPartnerRepository;
import org.tihor.repository.PropertyRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Property service.
 */
@Service
public class PropertyService {
    /**
     * The Property repository.
     */
    @Resource
    private PropertyRepository propertyRepository;

    /**
     * The Property lending partner repository.
     */
    @Resource
    private PropertyLendingPartnerRepository propertyLendingPartnerRepository;

    /**
     * The Lending partner service.
     */
    @Autowired
    private LendingPartnerService lendingPartnerService;

    /**
     * The Property mapper.
     */
    @Autowired
    private PropertyMapper propertyMapper;

    /**
     * The Property lending partner mapper.
     */
    @Autowired
    private PropertyLendingPartnerMapper propertyLendingPartnerMapper;

    /**
     * Add properties to customer list.
     *
     * @param customerEntity   the customer entity
     * @param propertyRequests the property requests
     * @return the list
     */
    public List<PropertyResponse> addPropertiesToCustomer(
            final CustomerEntity customerEntity,
            final List<PropertyRequest> propertyRequests
    ) {
        propertyRequests.forEach(propertyRequest -> {
            var entity = propertyMapper.mapRequestToEntity(propertyRequest, customerEntity);
            entity = propertyRepository.save(entity);

            var finalEntity = entity;
            var propertyLendingPartnerEntities = propertyRequest.getLendingPartners()
                    .stream()
                    .map(propertyLendingPartnerRequest -> propertyLendingPartnerMapper.mapRequestToEntity(propertyLendingPartnerRequest, finalEntity, lendingPartnerService.getEntity(propertyLendingPartnerRequest.getLendingPartnerId())))
                    .collect(Collectors.toList());

            propertyLendingPartnerRepository.saveAll(propertyLendingPartnerEntities);
        });

        return Collections.emptyList();
    }
}
