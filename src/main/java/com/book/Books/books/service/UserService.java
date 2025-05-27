package com.book.Books.books.service;

import com.book.Books.books.dto.UserDto;
import com.book.Books.books.entity.User;
import com.book.Books.books.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDto addUser(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        Long nextId = getNextAvailableId();

        User user = User.builder()
                .id(nextId)
                .name(userDto.getName())
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .build();

        User savedUser = userRepository.save(user);

        return new UserDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUser.getUsername());
    }

    private Long getNextAvailableId() {
        List<Long> existingIds = userRepository.findAll().stream()
                .map(User::getId)
                .sorted()
                .toList();

        long expectedId = 1;
        for (Long id : existingIds) {
            if (!id.equals(expectedId)) {
                break; // Found a gap
            }
            expectedId++;
        }
        return expectedId;
    }


    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail(), user.getUsername()))
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getUsername());
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());

        return new UserDto(
                userRepository.save(user).getId(),
                user.getName(),
                user.getEmail(),
                user.getUsername()
        );
    }
}
