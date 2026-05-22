package com.group3.SIS.modules.users.service;
import com.group3.SIS.modules.users.repository.usersRepository;
import com.group3.SIS.modules.users.entity.Role;
import com.group3.SIS.modules.users.entity.usersEntity;
import com.group3.SIS.modules.users.dto.ChangePasswordDTO;
import com.group3.SIS.modules.users.dto.CreateUserDTO;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final usersRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public usersEntity createUser(CreateUserDTO createUserDTO) {
        usersEntity user = new usersEntity();
        user.setUsername(createUserDTO.getUsername());
        user.setEmail(createUserDTO.getEmail());
        user.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        user.setFirstName(createUserDTO.getFirstName());
        user.setLastName(createUserDTO.getLastName());
        user.setRole(parseRole(createUserDTO.getRole()));
        user.setIsActive(true);
        return userRepository.save(user);
    }
    

    public void changePassword(ChangePasswordDTO changePasswordDTO) {
        // Fetch the user by ID
        usersEntity user = userRepository.findById(changePasswordDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the old password matches
        if (!passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Old password is incorrect");
        }

        // Update the password
        user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
        userRepository.save(user);
    }

    public usersEntity getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void disableUser(Long id) {
        usersEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setIsActive(false);
        userRepository.save(user);
    }

    private Role parseRole(String role) {
        if (role == null || role.isBlank()) {
            return Role.ROLE_STUDENT;
        }

        String normalizedRole = role.trim().toUpperCase();
        if (!normalizedRole.startsWith("ROLE_")) {
            normalizedRole = "ROLE_" + normalizedRole;
        }

        return Role.valueOf(normalizedRole);
    }

    public boolean isAdmin(usersEntity user) {
        return user.getRole() == Role.ROLE_ADMIN;
    }

    public boolean isStudent(usersEntity user) {
        return user.getRole() == Role.ROLE_STUDENT;
    }
    public boolean isFaculty(usersEntity user) {
        return user.getRole() == Role.ROLE_FACULTY;
    }
    public boolean isActive(usersEntity user) {
        return user.getIsActive();
    }
    public boolean isInactive(usersEntity user) {
        return !user.getIsActive();
    }
    public void DeleteUser(Long id) {
        usersEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}
