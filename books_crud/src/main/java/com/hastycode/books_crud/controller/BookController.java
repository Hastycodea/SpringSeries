package com.hastycode.books_crud.controller;

import com.hastycode.books_crud.model.Book;
import com.hastycode.books_crud.service.BookService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping("/home")
    public String home() {
        return "Welcome Home!";
    }

    @GetMapping("/books")
    public List<Book> getBooks() {
        return service.getBooks();
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {
        return service.addBook(book);
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable long id, @RequestBody Book book) {
        return service.updateBook(id);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable long id) {
        service.deleteBook(id);
    }
}
