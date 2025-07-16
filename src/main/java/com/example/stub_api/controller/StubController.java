package com.example.stub_api.controller;

import com.example.stub_api.model.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api")
public class
StubController {

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        String response = "{\"login\":\"Login1\",\"status\":\"ok\"}";
        return ResponseEntity.ok(response);
    }

    @PostMapping("/auth")
    public ResponseEntity<User> postAuth(@Valid @RequestBody User userRequest) {
        try {
            Thread.sleep(2000); // 2-секундная задержка
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        String login = userRequest.getLogin();
        String password = userRequest.getPassword();

        String currentTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));

        User response = new User(login, password, null, currentTime);

        return ResponseEntity.ok(response);
    }
}
