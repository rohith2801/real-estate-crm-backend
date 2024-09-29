package org.tihor.model;

import java.util.List;

/**
 * The type Response.
 */
public record Response(Object data, List<String> errors) {
    /**
     * With data response.
     *
     * @param data the data
     * @return the response
     */
    public static Response withData(Object data) {
        return new Response(data, null);
    }

    /**
     * With errors response.
     *
     * @param errors the errors
     * @return the response
     */
    public static Response withErrors(List<String> errors) {
        return new Response(null, errors);
    }
}
