package com.book.Books.Dashboard.service;

import com.book.Books.Dashboard.dto.UserDto;
import com.book.Books.Dashboard.entity.Catalog;
import com.book.Books.Dashboard.entity.User;
import com.book.Books.Dashboard.repository.CatalogRepository;
import com.book.Books.Dashboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDto addUser(UserDto userDto) {
        Long nextUserId = getNextAvailableUserId();

        User user = User.builder()
                .id(nextUserId)
                .name(userDto.getName())
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .build();

        User savedUser = userRepository.save(user);

        return new UserDto(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getUsername()
        );
    }

    private Long getNextAvailableUserId() {
        List<Long> ids = userRepository.findAll().stream().map(User::getId).sorted().toList();
        long expectedId = 1;
        for (Long id : ids) {
            if (!id.equals(expectedId)) break;
            expectedId++;
        }
        return expectedId;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(u -> new UserDto(u.getId(), u.getName(), u.getEmail(), u.getUsername()))
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getUsername());
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());

        User updated = userRepository.save(user);
        return new UserDto(updated.getId(), updated.getName(), updated.getEmail(), updated.getUsername());
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
