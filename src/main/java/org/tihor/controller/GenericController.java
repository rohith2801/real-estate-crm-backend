package org.tihor.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tihor.model.Response;
import org.tihor.model.request.CustomerPropertyRequest;
import org.tihor.service.GenericService;

/**
 * The type Generic controller.
 */
@RestController
@RequestMapping("/api/v1/generic")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GenericController {
    /**
     * The Generic service.
     */
    private final GenericService genericService;

    /**
     * Create data response entity.
     *
     * @param request the request
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<Response> createData(@RequestBody @Valid final CustomerPropertyRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.withData(genericService.createData(request)));
    }

    /**
     * Update data response entity.
     *
     * @param id      the id
     * @param request the request
     * @return the response entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<Response> updateData(
            @PathVariable final Long id,
            @RequestBody @Valid final CustomerPropertyRequest request
    ) {
        return ResponseEntity.ok(Response.withData(genericService.updateData(id, request)));
    }
}
