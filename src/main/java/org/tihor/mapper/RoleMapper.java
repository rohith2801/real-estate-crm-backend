package org.tihor.mapper;

import org.springframework.stereotype.Component;
import org.tihor.entity.RoleEntity;
import org.tihor.model.response.RoleResponse;

/**
 * The type Role mapper.
 */
@Component
public class RoleMapper {
    /**
     * Map entity to response role response.
     *
     * @param entity the entity
     * @return the role response
     */
    public RoleResponse mapEntityToResponse(final RoleEntity entity) {
        return RoleResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
