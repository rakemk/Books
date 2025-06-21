package com.book.Books.Dashboard.service;

import com.book.Books.Dashboard.dto.CatalogDto;
import com.book.Books.Dashboard.entity.Catalog;
import com.book.Books.Dashboard.entity.User;
import com.book.Books.Dashboard.repository.CatalogRepository;
import com.book.Books.Dashboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CatalogService {
    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private UserRepository userRepository;

    public CatalogDto addCatalog(CatalogDto catalogDto) {
        Optional<User> optionalUser = userRepository.findByUsername(catalogDto.getUsername());

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found with username: " + catalogDto.getUsername());
        }

        User user = optionalUser.get();

        Long nextCatalogId = getNextAvailableCatalogId();

        Catalog catalog = Catalog.builder()
                .id(nextCatalogId)
                .username(user.getUsername())
                .amount(catalogDto.getAmount())
                .purchaseDate(LocalDateTime.now())
                .dueDate(LocalDate.now().plusDays(15))
                .build();

        Catalog saved = catalogRepository.save(catalog);

        return new CatalogDto(
                saved.getId(),
                saved.getUsername(),
                saved.getAmount(),
                saved.getPurchaseDate(),
                saved.getDueDate()
        );
    }

    private Long getNextAvailableCatalogId() {
        List<Long> ids = catalogRepository.findAll().stream().map(Catalog::getId).sorted().toList();
        long expected = 1;
        for (Long id : ids) {
            if (!id.equals(expected)) break;
            expected++;
        }
        return expected;
    }
}
