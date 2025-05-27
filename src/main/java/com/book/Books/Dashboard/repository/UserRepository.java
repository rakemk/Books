package com.book.Books.Dashboard.repository;

import com.book.Books.Dashboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByUsername(String username);
}
