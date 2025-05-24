package com.book.Books.Dashboard.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book_master")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Book {
    @Id
    @Column(name = "id",nullable = false)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @JsonProperty("name")
    private String name;

    @Column(name = "type", nullable = false)
    @JsonProperty("type")
    private String type;

    @Column(name = "language", nullable = false)
    @JsonProperty("language")
    private String language;

    @Column(name = "available", nullable = false)
    @JsonProperty("available")
    private boolean available;


    public Book(String name, String type, String language, boolean available) {
        this.name = name;
        this.type = type;
        this.language = language;
        this.available = available;
    }
}
