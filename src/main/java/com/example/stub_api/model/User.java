package com.example.stub_api.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @NotBlank(message = "Поле login не может быть пустым")
    private String login;

    @NotBlank(message = "Поле password не может быть пустым")
    private String password;

    private String date;
}