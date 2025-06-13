package net.javaguides.springboot_restful_webservices.service.impl;

import net.javaguides.springboot_restful_webservices.dto.UserDTO;
import net.javaguides.springboot_restful_webservices.entity.User;
import net.javaguides.springboot_restful_webservices.exception.ResourceNotFoundException;
import net.javaguides.springboot_restful_webservices.mapper.AutoUserMapper;
import net.javaguides.springboot_restful_webservices.mapper.UserMapper;
import net.javaguides.springboot_restful_webservices.repository.UserRepository;
import net.javaguides.springboot_restful_webservices.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        // Convert UserDTO into User JPA Entity
        // User user = UserMapper.mapToUser(userDTO);
        // User user = modelMapper.map(userDTO, User.class);

        User user = AutoUserMapper.MAPPER.mapToUser(userDTO);
        User savedUser = userRepository.save(user);

        // Convert User JPA entity to UserDTO
        // UserDTO savedUserDTO = UserMapper.mapToUserDTO(savedUser);
        // UserDTO savedUserDTO = modelMapper.map(savedUser, UserDTO.class);
        UserDTO savedUserDTO = AutoUserMapper.MAPPER.mapTOUserDTO(savedUser);
        return savedUserDTO;
    }

    @Override
    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        // return UserMapper.mapToUserDTO(user);
        //return modelMapper.map(user, UserDTO.class);
        return AutoUserMapper.MAPPER.mapTOUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        // return users.stream().map(UserMapper::mapToUserDTO).collect(Collectors.toList());
        // return users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
        return users.stream().map(AutoUserMapper.MAPPER::mapTOUserDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(UserDTO user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", user.getId())
        );
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        // return UserMapper.mapToUserDTO(updatedUser);
        // return modelMapper.map(updatedUser, UserDTO.class);
        return AutoUserMapper.MAPPER.mapTOUserDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );

        userRepository.deleteById(userId);
    }
}
