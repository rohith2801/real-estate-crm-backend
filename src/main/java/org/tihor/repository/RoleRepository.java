package org.tihor.repository;

import org.springframework.data.repository.CrudRepository;
import org.tihor.entity.RoleEntity;

import java.util.Optional;

/**
 * The interface Role repository.
 */
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
    /**
     * Find by name optional.
     *
     * @param name the name
     * @return the optional
     */
    Optional<RoleEntity> findByName(String name);
}
