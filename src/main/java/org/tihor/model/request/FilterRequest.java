package org.tihor.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tihor.enums.SearchOperationType;

import java.io.Serializable;

/**
 * The type Filter request.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilterRequest implements Serializable {
    /**
     * The Key.
     */
    private String key;

    /**
     * The Value.
     */
    private Object value;

    /**
     * The Operation type.
     */
    private SearchOperationType operationType;
}
