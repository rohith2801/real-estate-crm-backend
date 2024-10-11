package org.tihor.runner;

import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.tihor.entity.RoleEntity;
import org.tihor.entity.UserEntity;
import org.tihor.enums.UserType;
import org.tihor.repository.RoleRepository;
import org.tihor.repository.UserRepository;

/**
 * The type Super admin user runner.
 */
@Component
@Order(2)
public class SuperAdminUserRunner implements ApplicationRunner {
    /**
     * The User repository.
     */
    @Resource
    private UserRepository userRepository;

    /**
     * The Role repository.
     */
    @Resource
    private RoleRepository roleRepository;

    /**
     * Run.
     *
     * @param args the args
     * @throws Exception the exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        var email = "superadmin@gmail.com";
        if (userRepository.findByUsernameAndIsDeleted(email, false).isPresent()) {
            return;
        }

        var entity = UserEntity.builder()
                .firstName("First")
                .lastName("Last")
                .street("Street")
                .pinCode("11111")
                .location("Location")
                .cellPhone("+49 9999999999")
                .emailId(email)
                .username(email)
                .password("SuperAdmin")
                .userType(UserType.INTERNAL)
                .roleEntity(roleRepository.findByName(RoleEntity.ROLE_SUPER_ADMIN).orElseThrow())
                .isDeleted(false)
                .build();

        userRepository.save(entity);
    }
}
