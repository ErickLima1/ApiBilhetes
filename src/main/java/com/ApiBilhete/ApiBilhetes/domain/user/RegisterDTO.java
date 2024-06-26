package com.ApiBilhete.ApiBilhetes.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterDTO(@NotBlank(message = "Email é Obrigatório! ")
                          @Email(message = "Email deve ser válido!")  String email,
                          @NotBlank(message = "Senha é Obrigatório! ") String password, UserRole role) {
}
