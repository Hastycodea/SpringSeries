package com.hastycode.fasahBackend.controller;

import com.hastycode.fasahBackend.model.Book;
import com.hastycode.fasahBackend.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/home")
    public String home() {
        return "Welcome home";
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(service.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable long id) {
        Book book = service.getBookById(id);

        if(book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
        return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/book/{id}/image")
    public ResponseEntity<byte[]> getImageById(@PathVariable long id){
        Book book = service.getBookById(id);
        byte[] imageFile = book.getImageData();
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(book.getImageType()))
                .body(imageFile);
    }

    @PostMapping("/books")
    public ResponseEntity<?> addBook(@RequestPart Book book, @RequestPart MultipartFile imageFile) {
        try{
            Book book1 = service.addBook(book, imageFile);
            return new ResponseEntity<>(book, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<Book> updateBook(@RequestPart Book book, @RequestPart MultipartFile imageFile, @PathVariable long id) throws IOException {
        Book updatedBook = service.updateBook(book, imageFile, id);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("book/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        Book existingBook = service.getBookById(id);

        if(existingBook != null) {
            service.deleteBookById(id);
            return new ResponseEntity<>("Book Deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
    }



}
