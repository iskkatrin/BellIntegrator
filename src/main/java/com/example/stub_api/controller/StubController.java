package com.example.stub_api.controller;

import com.example.stub_api.model.AuthRequest;
import com.example.stub_api.model.AuthResponse;
import jakarta.validation.Valid;
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
    public String getStatus() {
        return "{\"login\":\"Login1\",\"status\":\"ok\"}";
    }

    @PostMapping("/auth")
    public AuthResponse postAuth(@Valid @RequestBody AuthRequest authRequest) {
        try {
            Thread.sleep(2000); // 2-секундная задержка
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        String login = authRequest.getLogin();
        String password = authRequest.getPassword();

        String currentTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));

        return new AuthResponse(login, password, currentTime);
    }

    @PostMapping("/leak")
    public String createLeak() {
        for (int i = 0; i < 100000; i++) {
            memoryLeak.add("Data " + i);
        }
        return "Memory leak";
    }
}
