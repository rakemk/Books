package com.book.Books.Dashboard.Service;

import com.book.Books.Dashboard.Dto.BookDto;
import com.book.Books.Dashboard.Repository.BookRepository;
import com.book.Books.Dashboard.Entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public BookDto addBook(BookDto bookDto) {
        Long nextId = getNextAvailableId();

        Book book = Book.builder()
                .id(nextId)
                .name(bookDto.getName())
                .type(bookDto.getType())
                .language(bookDto.getLanguage())
                .available(bookDto.isAvailable())
                .build();

        Book savedBook = bookRepository.save(book);
        return new BookDto(
                savedBook.getId(),
                savedBook.getName(),
                savedBook.getType(),
                savedBook.getLanguage(),
                savedBook.isAvailable()
        );
    }
    private Long getNextAvailableId() {
        List<Long> existingIds = bookRepository.findAll().stream()
                .map(Book::getId)
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


    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(book -> new BookDto(
                        book.getId(),
                        book.getName(),
                        book.getType(),
                        book.getLanguage(),
                        book.isAvailable()))  // Convert Book to BookDto
                .collect(Collectors.toList());  // Collect the result into a List
    }


    public BookDto updateBook(Long id, BookDto bookDTO) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setName(bookDTO.getName());
        book.setType(bookDTO.getType());
        book.setLanguage(bookDTO.getLanguage());
        book.setAvailable(bookDTO.isAvailable());
        return convertToDTO(bookRepository.save(book));
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    private BookDto convertToDTO(Book book) {
        return new BookDto(book.getId(), book.getName(), book.getType(), book.getLanguage(), book.isAvailable());
    }
}
