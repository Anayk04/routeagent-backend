package com.routeagent.backend.controller;

import com.routeagent.backend.model.*;
import com.routeagent.backend.repository.DriverRepository;
import com.routeagent.backend.repository.ShipmentRepository;
import com.routeagent.backend.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SeedController {

    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;
    private final ShipmentRepository shipmentRepository;

    @GetMapping("/seed")
    public ResponseEntity<Map<String, Object>> seedData() {
        // 1. Seed 3 Drivers with distinct current locations
        Driver d1 = Driver.builder()
                .name("Rajesh Kumar")
                .phone("+91 98765 43210")
                .status(DriverStatus.AVAILABLE)
                .currentLocation("Mumbai")
                .build();

        Driver d2 = Driver.builder()
                .name("Amit Sharma")
                .phone("+91 98765 43211")
                .status(DriverStatus.AVAILABLE)
                .currentLocation("Delhi")
                .build();

        Driver d3 = Driver.builder()
                .name("Suresh Raina")
                .phone("+91 98765 43212")
                .status(DriverStatus.AVAILABLE)
                .currentLocation("Chennai")
                .build();

        List<Driver> savedDrivers = driverRepository.saveAll(List.of(d1, d2, d3));

        // 2. Seed 3 Vehicles with distinct current locations
        Vehicle v1 = Vehicle.builder()
                .plate("MH-01-AB-1234")
                .type(VehicleType.TRUCK)
                .capacityKg(5000.0)
                .status(VehicleStatus.IDLE)
                .currentLocation("Mumbai")
                .build();

        Vehicle v2 = Vehicle.builder()
                .plate("DL-01-CD-5678")
                .type(VehicleType.VAN)
                .capacityKg(1500.0)
                .status(VehicleStatus.IDLE)
                .currentLocation("Delhi")
                .build();

        Vehicle v3 = Vehicle.builder()
                .plate("TN-01-EF-9012")
                .type(VehicleType.BIKE)
                .capacityKg(80.0)
                .status(VehicleStatus.IDLE)
                .currentLocation("Chennai")
                .build();

        List<Vehicle> savedVehicles = vehicleRepository.saveAll(List.of(v1, v2, v3));

        // 3. Seed 3 Pending Shipments with distinct origin/destination pairs
        Shipment s1 = Shipment.builder()
                .origin("Mumbai")
                .destination("Pune")
                .weightKg(1200.0)
                .status(ShipmentStatus.PENDING)
                .build();

        Shipment s2 = Shipment.builder()
                .origin("Delhi")
                .destination("Agra")
                .weightKg(3500.0)
                .status(ShipmentStatus.PENDING)
                .build();

        Shipment s3 = Shipment.builder()
                .origin("Chennai")
                .destination("Bangalore")
                .weightKg(45.0)
                .status(ShipmentStatus.PENDING)
                .build();

        List<Shipment> savedShipments = shipmentRepository.saveAll(List.of(s1, s2, s3));

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Sample logistics data seeded successfully");
        response.put("driversCount", savedDrivers.size());
        response.put("vehiclesCount", savedVehicles.size());
        response.put("shipmentsCount", savedShipments.size());
        response.put("drivers", savedDrivers);
        response.put("vehicles", savedVehicles);
        response.put("shipments", savedShipments);

        return ResponseEntity.ok(response);
    }
}
