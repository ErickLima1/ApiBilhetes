package com.ApiBilhete.ApiBilhetes.domain.user;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(@NotBlank(message = "Email é Obrigatório! ") String email,
                               @NotBlank(message = "Senha é Obrigatório! ") String password) {

}
