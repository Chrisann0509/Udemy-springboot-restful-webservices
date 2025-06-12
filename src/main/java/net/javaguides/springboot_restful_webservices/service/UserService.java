package net.javaguides.springboot_restful_webservices.service;

import net.javaguides.springboot_restful_webservices.dto.UserDTO;
import net.javaguides.springboot_restful_webservices.entity.User;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO user);
    UserDTO getUserById(Long userId);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(UserDTO user);
    void deleteUser(Long userId);
}
