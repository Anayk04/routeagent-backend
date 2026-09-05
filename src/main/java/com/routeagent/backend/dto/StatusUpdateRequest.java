package com.routeagent.backend.dto;

import com.routeagent.backend.model.ShipmentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StatusUpdateRequest {
    @NotNull
    private ShipmentStatus status;
}
