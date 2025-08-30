package br.com.api.user.DTO;


import br.com.api.user.Enum.Role;
import br.com.api.user.Models.User;

public record UserDTO(
        Long id,
        String nome,
        String email,
        Role role
) {
    public UserDTO(User user) {
        this(user.getId(), user.getNome(), user.getEmail(), user.getRole());
    }
}