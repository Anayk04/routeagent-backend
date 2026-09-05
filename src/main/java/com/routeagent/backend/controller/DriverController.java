package com.routeagent.backend.controller;

import com.routeagent.backend.model.Driver;
import com.routeagent.backend.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @GetMapping
    public ResponseEntity<List<Driver>> getDrivers(@RequestParam(required = false) String status) {
        if (status != null && !status.isBlank()) {
            return ResponseEntity.ok(driverService.getDriversByStatus(status));
        }
        return ResponseEntity.ok(driverService.getAllDrivers());
    }

    @PostMapping
    public ResponseEntity<Driver> createDriver(@RequestBody Driver driver) {
        return ResponseEntity.ok(driverService.createDriver(driver));
    }
}
