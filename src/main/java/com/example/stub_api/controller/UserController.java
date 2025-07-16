package com.example.stub_api.controller;

import com.example.stub_api.model.User;
import com.example.stub_api.service.DataBaseWorker;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final DataBaseWorker dataBaseWorker;

    public UserController(DataBaseWorker dataBaseWorker) {
        this.dataBaseWorker = dataBaseWorker;
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestParam String login) {
        User user = dataBaseWorker.getUserByLogin(login);
        if (user == null) {
            return ResponseEntity.status(500).body(null);
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/user")
    public ResponseEntity<String> createUser(@Valid @RequestBody User user) {
        int rows = dataBaseWorker.insertUser(user);
        if (rows > 0) {
            return ResponseEntity.ok("Пользователь успешно добавлен");
        } else {
            return ResponseEntity.status(500).body("Ошибка при добавлении пользователя");
        }
    }
}
