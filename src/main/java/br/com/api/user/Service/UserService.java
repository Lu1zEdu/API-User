package br.com.api.user.Service;

import br.com.api.user.DTO.CreateUserDTO;
import br.com.api.user.DTO.UserDTO;
import br.com.api.user.Models.User;
import br.com.api.user.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserDTO createUser(CreateUserDTO createUserDTO) {
        User user = new User();
        user.setNome(createUserDTO.nome());
        user.setEmail(createUserDTO.email());
        user.setRole(createUserDTO.role());

        User savedUser = userRepository.save(user);
        return new UserDTO(savedUser);
    }

    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + id));
        return new UserDTO(user);
    }

    @Transactional
    public UserDTO updateUser(Long id, CreateUserDTO createUserDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + id));

        user.setNome(createUserDTO.nome());
        user.setEmail(createUserDTO.email());
        user.setRole(createUserDTO.role());

        User updatedUser = userRepository.save(user);
        return new UserDTO(updatedUser);
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado com o ID: " + id);
        }
        userRepository.deleteById(id);
    }
}
