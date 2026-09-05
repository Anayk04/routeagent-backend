package com.routeagent.backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String plate;

    @Enumerated(EnumType.STRING)
    private VehicleType type;

    private Double capacityKg;

    @Enumerated(EnumType.STRING)
    private VehicleStatus status;

    @Column(name = "current_location")
    private String currentLocation;
}
