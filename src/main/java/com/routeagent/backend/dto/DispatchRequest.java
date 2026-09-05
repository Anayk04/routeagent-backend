package com.routeagent.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.UUID;

@Data
public class DispatchRequest {
    @NotNull
    private UUID shipmentId;
    @NotNull
    private UUID driverId;
    @NotNull
    private UUID vehicleId;
    
    private String reasoning;
}
