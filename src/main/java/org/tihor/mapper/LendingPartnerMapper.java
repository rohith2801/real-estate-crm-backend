package org.tihor.mapper;

import org.springframework.stereotype.Component;
import org.tihor.entity.LendingPartnerEntity;
import org.tihor.model.response.LendingPartnerResponse;

/**
 * The type Lending partner mapper.
 */
@Component
public class LendingPartnerMapper {
    /**
     * Map entity to response lending partner response.
     *
     * @param entity the entity
     * @return the lending partner response
     */
    public LendingPartnerResponse mapEntityToResponse(final LendingPartnerEntity entity) {
        return LendingPartnerResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .type(entity.getType())
                .address(entity.getAddress())
                .build();
    }
}
