package com.book.Books.Dashboard.Controller;

import com.book.Books.Dashboard.Dto.BookDto;
import com.book.Books.Dashboard.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin(value = "*")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public BookDto addBook(@RequestBody BookDto bookDto){
        return bookService.addBook(bookDto);
    }

    @GetMapping
    public List<BookDto> getAllBooks(){
        return bookService.getAllBooks();
    }

    @PutMapping("/{id}")
    public BookDto updateBook(@PathVariable Long id,@RequestBody BookDto bookDto){
        return bookService.updateBook(id, bookDto);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
    }
}
