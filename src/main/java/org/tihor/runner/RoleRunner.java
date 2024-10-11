package org.tihor.runner;

import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.tihor.entity.RoleEntity;
import org.tihor.repository.RoleRepository;

import java.util.List;

/**
 * The type Role runner.
 */
@Component
@Order(1)
public class RoleRunner implements ApplicationRunner {
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
        var list = List.of("SUPER_ADMIN");
        var entities = list.stream()
                .filter(name -> roleRepository.findByName(name).isEmpty())
                .map(name -> RoleEntity.builder().name(name).build())
                .toList();

        roleRepository.saveAll(entities);
    }
}
