package com.book.Books.Dashboard.repository;

import com.book.Books.Dashboard.entity.Catalog;
import com.book.Books.Dashboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog,Long> {
    //Optional<User> findByUsername(String username);
}
