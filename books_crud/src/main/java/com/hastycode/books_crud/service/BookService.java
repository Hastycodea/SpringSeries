package com.hastycode.books_crud.service;

import com.hastycode.books_crud.model.Book;
import com.hastycode.books_crud.repo.BookRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepo repo;

    public List<Book> getBooks() {
        return repo.findAll();
    }

    public Book addBook(Book book) {
        return repo.save(book);
    }

    public Book updateBook(long id) {
        Book book = repo.findById(id).get();
        return repo.save(book);
    }

    public void deleteBook(long id) {
        repo.deleteById(id);
    }
}
