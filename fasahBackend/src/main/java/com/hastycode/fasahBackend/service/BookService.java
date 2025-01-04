package com.hastycode.fasahBackend.service;

import com.hastycode.fasahBackend.model.Book;
import com.hastycode.fasahBackend.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repo;

    public List<Book> getAllBooks() {
        return repo.findAll();
    }

    public Book getBookById(long id) {
        return repo.findById(id).orElse(null);
    }

    public Book addBook(Book book, MultipartFile imageFile) throws IOException {
        book.setImageName(imageFile.getOriginalFilename());
        book.setImageType(imageFile.getContentType());
        book.setImageData(imageFile.getBytes());
        return repo.save(book);
    }

    public Book updateBook(Book book, MultipartFile imageFile, long id) throws IOException {
        Book existingBook = repo.findById(id).orElse(null);

        if (existingBook != null) {
            book.setImageName(imageFile.getOriginalFilename());
            book.setImageType(imageFile.getContentType());
            book.setImageData(imageFile.getBytes());
            return repo.save(book);
        }
        return null;
    }

    public void deleteBookById(long id) {
        repo.deleteById(id);
    }
}
