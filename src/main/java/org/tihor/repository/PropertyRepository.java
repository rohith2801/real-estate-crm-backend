package org.tihor.repository;

import org.springframework.data.repository.CrudRepository;
import org.tihor.entity.PropertyEntity;

/**
 * The interface Property repository.
 */
public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {
}
