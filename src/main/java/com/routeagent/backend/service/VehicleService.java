package com.routeagent.backend.service;

import com.routeagent.backend.model.Vehicle;
import com.routeagent.backend.model.VehicleStatus;
import com.routeagent.backend.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public List<Vehicle> getVehiclesByStatus(String status) {
        if (status == null || status.isBlank()) {
            return getAllVehicles();
        }
        VehicleStatus vehicleStatus = VehicleStatus.valueOf(status.toUpperCase().trim());
        return vehicleRepository.findByStatus(vehicleStatus);
    }

    public Vehicle getVehicleById(UUID id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        if (vehicle.getStatus() == null) {
            vehicle.setStatus(VehicleStatus.IDLE);
        }
        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicleStatus(UUID id, String status) {
        Vehicle vehicle = getVehicleById(id);
        vehicle.setStatus(VehicleStatus.valueOf(status.toUpperCase().trim()));
        return vehicleRepository.save(vehicle);
    }
}
