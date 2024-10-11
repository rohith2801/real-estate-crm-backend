package org.tihor.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * The type Role response.
 */
@Getter
@Setter
@Builder
public class RoleResponse implements Serializable {
    /**
     * The Id.
     */
    private Long id;

    /**
     * The Name.
     */
    private String name;
}
