package com.book.Books.Dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogDto {
    private Long id;
    private String username;
    private int amount;
    private LocalDateTime purchaseDate;
    private LocalDate dueDate;
}
