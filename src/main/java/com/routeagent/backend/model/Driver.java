package com.routeagent.backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "drivers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String phone;

    @Enumerated(EnumType.STRING)
    private DriverStatus status;

    @Column(name = "current_location")
    private String currentLocation;
}
