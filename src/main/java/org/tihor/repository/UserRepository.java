package org.tihor.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.tihor.entity.UserEntity;
import org.tihor.enums.UserType;

import java.util.List;
import java.util.Optional;

/**
 * The interface User repository.
 */
public interface UserRepository extends CrudRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    /**
     * Find by username and is deleted optional.
     *
     * @param username  the username
     * @param isDeleted the is deleted
     * @return the optional
     */
    Optional<UserEntity> findByUsernameAndIsDeleted(String username, Boolean isDeleted);

    /**
     * Find by id and is deleted optional.
     *
     * @param id        the id
     * @param isDeleted the is deleted
     * @return the optional
     */
    Optional<UserEntity> findByIdAndIsDeleted(Long id, Boolean isDeleted);

    /**
     * Find by user type and is deleted list.
     *
     * @param userType  the user type
     * @param isDeleted the is deleted
     * @return the list
     */
    List<UserEntity> findByUserTypeAndIsDeleted(UserType userType, Boolean isDeleted);
}
