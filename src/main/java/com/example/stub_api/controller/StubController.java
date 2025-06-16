package com.example.stub_api.controller;

import com.example.stub_api.model.AuthResponse;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StubController {

    @GetMapping("/status")
    public String getStatus() {
        return "{\"login\":\"Login1\",\"status\":\"ok\"}";
    }

    @PostMapping("/auth")
    public AuthResponse postAuth(@RequestBody Map<String, String> requestData) {
        String login = requestData.get("login");
        String password = requestData.get("password");

        String currentTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));

        return new AuthResponse(login, password, currentTime);
    }
}
