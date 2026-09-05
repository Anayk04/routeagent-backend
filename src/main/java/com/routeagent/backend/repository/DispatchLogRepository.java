package com.routeagent.backend.repository;

import com.routeagent.backend.model.DispatchLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DispatchLogRepository extends JpaRepository<DispatchLog, UUID> {
}
