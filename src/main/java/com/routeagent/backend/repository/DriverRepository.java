package com.routeagent.backend.repository;

import com.routeagent.backend.model.Driver;
import com.routeagent.backend.model.DriverStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface DriverRepository extends JpaRepository<Driver, UUID> {
    List<Driver> findByStatus(DriverStatus status);
}
