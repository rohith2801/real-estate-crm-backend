package org.tihor.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.tihor.enums.LendingPartnerType;

import java.io.Serializable;

/**
 * The type Lending partner response.
 */
@Getter
@Setter
@Builder
public class LendingPartnerResponse implements Serializable {
    /**
     * The Id.
     */
    private Long id;

    /**
     * The Name.
     */
    private String name;

    /**
     * The Type.
     */
    private LendingPartnerType type;

    /**
     * The Address.
     */
    private String address;
}
