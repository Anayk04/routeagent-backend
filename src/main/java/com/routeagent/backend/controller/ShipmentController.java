package com.routeagent.backend.controller;

import com.routeagent.backend.dto.StatusUpdateRequest;
import com.routeagent.backend.model.Shipment;
import com.routeagent.backend.service.ShipmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/shipments")
@RequiredArgsConstructor
public class ShipmentController {

    private final ShipmentService shipmentService;

    @GetMapping
    public ResponseEntity<List<Shipment>> getShipments(@RequestParam(required = false) String status) {
        if (status != null && !status.isBlank()) {
            return ResponseEntity.ok(shipmentService.getShipmentsByStatus(status));
        }
        return ResponseEntity.ok(shipmentService.getAllShipments());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Shipment> updateStatus(
            @PathVariable UUID id,
            @Valid @RequestBody StatusUpdateRequest request
    ) {
        return ResponseEntity.ok(shipmentService.updateShipmentStatus(id, request.getStatus().name()));
    }

    @PostMapping
    public ResponseEntity<Shipment> createShipment(@RequestBody Shipment shipment) {
        return ResponseEntity.ok(shipmentService.createShipment(shipment));
    }
}
