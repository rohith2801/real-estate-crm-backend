package org.tihor.repository;

import org.springframework.data.repository.CrudRepository;
import org.tihor.entity.PropertyEntity;
import org.tihor.entity.PropertyLendingPartnerEntity;

/**
 * The interface Property lending partner repository.
 */
public interface PropertyLendingPartnerRepository extends CrudRepository<PropertyLendingPartnerEntity, Long> {
    void deleteAllByPropertyEntity(PropertyEntity propertyEntity);
}
