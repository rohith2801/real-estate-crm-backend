package org.tihor.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.tihor.entity.CustomerEntity;

import java.util.List;
import java.util.Optional;

/**
 * The interface Customer repository.
 */
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long>, JpaSpecificationExecutor<CustomerEntity> {
    /**
     * Find by is deleted list.
     *
     * @param isDeleted the is deleted
     * @return the list
     */
    List<CustomerEntity> findByIsDeleted(Boolean isDeleted);

    /**
     * Find by id and is deleted optional.
     *
     * @param id        the id
     * @param isDeleted the is deleted
     * @return the optional
     */
    Optional<CustomerEntity> findByIdAndIsDeleted(Long id, Boolean isDeleted);
}
