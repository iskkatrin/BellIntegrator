package com.example.stub_api.controller;

import com.example.stub_api.model.AuthRequest;
import com.example.stub_api.model.AuthResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StubController {

    private final List<String> memoryLeak = new ArrayList<>();

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        String response = "{\"login\":\"Login1\",\"status\":\"ok\"}";
        return ResponseEntity.ok(response);
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> postAuth(@Valid @RequestBody AuthRequest authRequest) {
        try {
            Thread.sleep(2000); // 2-секундная задержка
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        String login = authRequest.getLogin();
        String password = authRequest.getPassword();

        String currentTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));

        AuthResponse response = new AuthResponse(login, password, currentTime);

        return ResponseEntity.ok(response);
    }
}
