package com.hastycode.fasah.controller;

import com.hastycode.fasah.Service.BookService;
import com.hastycode.fasah.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String getBooks(Model model) {
        List<Book> books = bookService.getTopNonFictionBooks();
        model.addAttribute("books", books);
        return "books";
    }
}
