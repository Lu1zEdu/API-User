package br.com.api.user.DTO;


import br.com.api.user.Enum.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateUserDTO(
        @NotBlank(message = "O nome não pode estar em branco.")
        @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres.")
        String nome,

        @NotBlank(message = "O email não pode estar em branco.")
        @Email(message = "O formato do email é inválido.")
        String email,

        @NotNull(message = "A role não pode ser nula.")
        Role role
) {
}