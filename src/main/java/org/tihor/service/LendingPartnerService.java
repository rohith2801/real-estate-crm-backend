package org.tihor.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.tihor.entity.LendingPartnerEntity;
import org.tihor.mapper.LendingPartnerMapper;
import org.tihor.model.response.LendingPartnerResponse;
import org.tihor.repository.LendingPartnerRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

/**
 * The type Lending partner service.
 */
@Service
@DependsOn("lendingPartnerRunner")
@RequiredArgsConstructor
public class LendingPartnerService {
    /**
     * The Lending partner repository.
     */
    private final LendingPartnerRepository lendingPartnerRepository;

    /**
     * The Lending partner mapper.
     */
    private final LendingPartnerMapper lendingPartnerMapper;

    /**
     * The Is cache enabled.
     */
    @Value("${cache.lending-partner.enabled:false}")
    private boolean isCacheEnabled;

    /**
     * The Lending partner entity map.
     */
    private Map<Long, LendingPartnerEntity> lendingPartnerEntityMap;

    /**
     * Post construct.
     */
    @PostConstruct
    public void postConstruct() {
        lendingPartnerEntityMap = new HashMap<>();
        if (isCacheEnabled) {
            lendingPartnerRepository.findAll()
                    .forEach(entity -> lendingPartnerEntityMap.put(entity.getId(), entity));
        }
    }

    /**
     * Gets lending partners.
     *
     * @return the lending partners
     */
    public List<LendingPartnerResponse> getLendingPartners() {
        if (isCacheEnabled) {
            return lendingPartnerEntityMap.values()
                    .stream()
                    .map(lendingPartnerMapper::mapEntityToResponse)
                    .toList();
        }

        return StreamSupport.stream(lendingPartnerRepository.findAll().spliterator(), false)
                .map(lendingPartnerMapper::mapEntityToResponse)
                .toList();
    }

    /**
     * Gets entity.
     *
     * @param id the id
     * @return the entity
     */
    public LendingPartnerEntity getEntity(final Long id) {
        if (isCacheEnabled) {
            return lendingPartnerEntityMap.get(id);
        }

        return lendingPartnerRepository.findById(id).orElseThrow();
    }
}
