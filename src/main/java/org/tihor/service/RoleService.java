package org.tihor.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.tihor.entity.RoleEntity;
import org.tihor.mapper.RoleMapper;
import org.tihor.repository.RoleRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Role service.
 */
@Service
@DependsOn("roleRunner")
@RequiredArgsConstructor
public class RoleService {
    /**
     * The Role repository.
     */
    private final RoleRepository roleRepository;

    /**
     * The Role mapper.
     */
    private final RoleMapper roleMapper;

    /**
     * The Is cache enabled.
     */
    @Value("${cache.role.enabled:false}")
    private boolean isCacheEnabled;

    /**
     * The Map.
     */
    private Map<Long, RoleEntity> map;

    /**
     * Sets .
     */
    @PostConstruct
    public void setup() {
        map = new HashMap<>();
        if (isCacheEnabled) {
            roleRepository.findAll().forEach(entity -> map.put(entity.getId(), entity));
        }
    }

    /**
     * Gets role.
     *
     * @param id the id
     * @return the role
     */
    public RoleEntity getRole(final Long id) {
        if (isCacheEnabled) {
            return map.get(id);
        }

        return roleRepository.findById(id).orElseThrow();
    }
}
