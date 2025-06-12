package net.javaguides.springboot_restful_webservices.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.javaguides.springboot_restful_webservices.dto.UserDTO;
import net.javaguides.springboot_restful_webservices.entity.User;
import net.javaguides.springboot_restful_webservices.repository.UserRepository;
import net.javaguides.springboot_restful_webservices.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        //Convert UserDTO into User JPA Entity
        User user = new User(userDTO.getId(), userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail());

        User savedUser = userRepository.save(user);

        //Convert User JPA entity to UserDTO
        UserDTO savedUserDTO = new UserDTO(savedUser.getId(), savedUser.getFirstName(), savedUser.getLastName(), savedUser.getEmail());
        return savedUserDTO;
    }

    @Override
    public User getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        return updatedUser;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
