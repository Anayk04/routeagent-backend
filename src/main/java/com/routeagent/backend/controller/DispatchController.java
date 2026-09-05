package com.routeagent.backend.controller;

import com.routeagent.backend.dto.DispatchRequest;
import com.routeagent.backend.model.DispatchLog;
import com.routeagent.backend.service.DispatchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DispatchController {

    private final DispatchService dispatchService;

    @PostMapping("/dispatch")
    public ResponseEntity<DispatchLog> dispatch(@Valid @RequestBody DispatchRequest request) {
        return ResponseEntity.ok(dispatchService.dispatch(request));
    }

    @GetMapping("/dispatch-logs")
    public ResponseEntity<List<DispatchLog>> getDispatchLogs() {
        return ResponseEntity.ok(dispatchService.getLogs());
    }
}
