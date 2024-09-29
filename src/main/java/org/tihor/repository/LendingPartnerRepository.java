package org.tihor.repository;

import org.springframework.data.repository.CrudRepository;
import org.tihor.entity.LendingPartnerEntity;
import org.tihor.enums.LendingPartnerType;

import java.util.Optional;

/**
 * The interface Lending partner repository.
 */
public interface LendingPartnerRepository extends CrudRepository<LendingPartnerEntity, Long> {
    /**
     * Find by name and type and address optional.
     *
     * @param name    the name
     * @param type    the type
     * @param address the address
     * @return the optional
     */
    Optional<LendingPartnerEntity> findByNameAndTypeAndAddress(String name, LendingPartnerType type, String address);
}
