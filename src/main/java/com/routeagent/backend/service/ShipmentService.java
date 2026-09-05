package com.routeagent.backend.service;

import com.routeagent.backend.model.DriverStatus;
import com.routeagent.backend.model.Shipment;
import com.routeagent.backend.model.ShipmentStatus;
import com.routeagent.backend.model.VehicleStatus;
import com.routeagent.backend.repository.DriverRepository;
import com.routeagent.backend.repository.ShipmentRepository;
import com.routeagent.backend.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;

    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }

    public List<Shipment> getShipmentsByStatus(String status) {
        if (status == null || status.isBlank()) {
            return getAllShipments();
        }
        ShipmentStatus shipmentStatus = ShipmentStatus.valueOf(status.toUpperCase().trim());
        return shipmentRepository.findByStatus(shipmentStatus);
    }

    public Shipment getShipmentById(UUID id) {
        return shipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shipment not found with id: " + id));
    }

    public Shipment createShipment(Shipment shipment) {
        if (shipment.getStatus() == null) {
            shipment.setStatus(ShipmentStatus.PENDING);
        }
        return shipmentRepository.save(shipment);
    }

    public Shipment updateShipmentStatus(UUID id, String status) {
        Shipment shipment = getShipmentById(id);
        ShipmentStatus newStatus = ShipmentStatus.valueOf(status.toUpperCase().trim());
        shipment.setStatus(newStatus);

        if (newStatus == ShipmentStatus.DELIVERED) {
            shipment.setDeliveredAt(LocalDateTime.now());
            if (shipment.getDriver() != null) {
                shipment.getDriver().setStatus(DriverStatus.AVAILABLE);
                driverRepository.save(shipment.getDriver());
            }
            if (shipment.getVehicle() != null) {
                shipment.getVehicle().setStatus(VehicleStatus.IDLE);
                vehicleRepository.save(shipment.getVehicle());
            }
        }

        return shipmentRepository.save(shipment);
    }
}
