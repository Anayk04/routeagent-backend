package com.routeagent.backend.repository;

import com.routeagent.backend.model.Shipment;
import com.routeagent.backend.model.ShipmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface ShipmentRepository extends JpaRepository<Shipment, UUID> {
    List<Shipment> findByStatus(ShipmentStatus status);
}
