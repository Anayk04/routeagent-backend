package com.routeagent.backend.service;

import com.routeagent.backend.model.Driver;
import com.routeagent.backend.model.DriverStatus;
import com.routeagent.backend.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public List<Driver> getDriversByStatus(String status) {
        if (status == null || status.isBlank()) {
            return getAllDrivers();
        }
        DriverStatus driverStatus = DriverStatus.valueOf(status.toUpperCase().trim());
        return driverRepository.findByStatus(driverStatus);
    }

    public Driver getDriverById(UUID id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found with id: " + id));
    }

    public Driver createDriver(Driver driver) {
        if (driver.getStatus() == null) {
            driver.setStatus(DriverStatus.AVAILABLE);
        }
        return driverRepository.save(driver);
    }

    public Driver updateDriverStatus(UUID id, String status) {
        Driver driver = getDriverById(id);
        driver.setStatus(DriverStatus.valueOf(status.toUpperCase().trim()));
        return driverRepository.save(driver);
    }
}
