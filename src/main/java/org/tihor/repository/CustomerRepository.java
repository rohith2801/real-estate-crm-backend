package org.tihor.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.tihor.entity.CustomerEntity;

/**
 * The interface Customer repository.
 */
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long>, JpaSpecificationExecutor<CustomerEntity> {
}
