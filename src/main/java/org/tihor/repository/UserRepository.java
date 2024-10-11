package org.tihor.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.tihor.entity.UserEntity;
import org.tihor.enums.UserType;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    Optional<UserEntity> findByUsernameAndIsDeleted(String username, Boolean isDeleted);

    Optional<UserEntity> findByIdAndIsDeleted(Long id, Boolean isDeleted);

    List<UserEntity> findByUserTypeAndIsDeleted(UserType userType, Boolean isDeleted);
}
