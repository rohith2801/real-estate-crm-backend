package org.tihor.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tihor.entity.UserEntity;
import org.tihor.entity.PropertyEntity;
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
@Transactional
@RequiredArgsConstructor
public class PropertyService {
    /**
     * The Property repository.
     */
    private final PropertyRepository propertyRepository;

    /**
     * The Property lending partner repository.
     */
    private final PropertyLendingPartnerRepository propertyLendingPartnerRepository;

    /**
     * The Lending partner service.
     */
    private final LendingPartnerService lendingPartnerService;

    /**
     * The Property mapper.
     */
    private final PropertyMapper propertyMapper;

    /**
     * The Property lending partner mapper.
     */
    private final PropertyLendingPartnerMapper propertyLendingPartnerMapper;

    /**
     * Add properties to customer list.
     *
     * @param customerEntity the customer entity
     * @param propertyList   the property list
     * @return the list
     */
    public List<PropertyResponse> addPropertiesToCustomer(
            final UserEntity customerEntity,
            final List<PropertyRequest> propertyList
    ) {
        propertyList.forEach(propertyRequest -> {
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

    /**
     * Delete properties.
     *
     * @param entities the entities
     */
    public void deleteProperties(final List<PropertyEntity> entities) {
        entities.forEach(propertyLendingPartnerRepository::deleteAllByPropertyEntity);
        propertyRepository.deleteAll(entities);
    }

    /**
     * Update properties to customer list.
     *
     * @param customerEntity the customer entity
     * @param propertyList   the property list
     * @return the list
     */
    public List<PropertyResponse> updatePropertiesToCustomer(
            final UserEntity customerEntity,
            final List<PropertyRequest> propertyList
    ) {
        deleteProperties(customerEntity.getPropertyEntities());
        return addPropertiesToCustomer(customerEntity, propertyList);
    }
}
