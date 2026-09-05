package com.routeagent.backend.service;

import com.routeagent.backend.dto.DispatchRequest;
import com.routeagent.backend.model.*;
import com.routeagent.backend.repository.DispatchLogRepository;
import com.routeagent.backend.repository.DriverRepository;
import com.routeagent.backend.repository.ShipmentRepository;
import com.routeagent.backend.repository.VehicleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DispatchService {

    private final ShipmentRepository shipmentRepository;
    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;
    private final DispatchLogRepository dispatchLogRepository;

    @Transactional
    public DispatchLog dispatch(DispatchRequest request) {
        Shipment shipment = shipmentRepository.findById(request.getShipmentId())
                .orElseThrow(() -> new RuntimeException("Shipment not found with id: " + request.getShipmentId()));
        Driver driver = driverRepository.findById(request.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver not found with id: " + request.getDriverId()));
        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + request.getVehicleId()));

        if (shipment.getStatus() != ShipmentStatus.PENDING) {
            throw new RuntimeException("Shipment is not PENDING");
        }

        shipment.setDriver(driver);
        shipment.setVehicle(vehicle);
        shipment.setStatus(ShipmentStatus.ASSIGNED);
        shipment.setAssignedAt(LocalDateTime.now());

        driver.setStatus(DriverStatus.ON_DUTY);
        driver.setCurrentLocation(shipment.getOrigin());

        vehicle.setStatus(VehicleStatus.IN_USE);
        vehicle.setCurrentLocation(shipment.getOrigin());

        driverRepository.save(driver);
        vehicleRepository.save(vehicle);
        Shipment savedShipment = shipmentRepository.save(shipment);

        DispatchLog log = DispatchLog.builder()
                .shipment(savedShipment)
                .agentAction("DISPATCH")
                .reasoning(request.getReasoning())
                .build();

        return dispatchLogRepository.save(log);
    }

    public List<DispatchLog> getLogs() {
        return dispatchLogRepository.findAll();
    }
}
