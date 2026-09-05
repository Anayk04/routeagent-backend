package com.routeagent.backend.dto;

import com.routeagent.backend.model.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    
    // Optional, mainly for registration to specify role
    private Role role;
}
