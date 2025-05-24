package com.book.Books.Dashboard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class
BookDto {
    @Id
    @Column(nullable = false)
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;

    @JsonProperty("language")
    private String language;

    @JsonProperty("available")
    private boolean available;

    @JsonProperty("quantity")
    private int quantity;
}
