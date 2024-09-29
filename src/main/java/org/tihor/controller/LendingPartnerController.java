package org.tihor.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tihor.model.Response;
import org.tihor.service.LendingPartnerService;

/**
 * The type Lending partner controller.
 */
@RestController
@RequestMapping("/api/v1/lending-partner")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LendingPartnerController {
    /**
     * The Lending partner service.
     */
    private final LendingPartnerService lendingPartnerService;

    /**
     * Gets lending partners.
     *
     * @return the lending partners
     */
    @GetMapping
    public ResponseEntity<Response> getLendingPartners() {
        return ResponseEntity.ok(Response.withData(lendingPartnerService.getLendingPartners()));
    }
}
