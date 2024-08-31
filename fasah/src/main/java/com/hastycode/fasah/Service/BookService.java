package com.hastycode.fasah.Service;

import com.hastycode.fasah.model.Book;
import com.hastycode.fasah.model.BookResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class BookService {

    private String apiKey = "AIzaSyB7EEKjUDFnceWsJIbNOySlBL_EfQ-QR5M";
    private static final String GOOGLE_BOOKS_API = "https://www.googleapis.com/books/v1/volumes";

    public List<Book> getTopNonFictionBooks() {
        RestTemplate restTemplate = new RestTemplate();

        String url = UriComponentsBuilder.fromHttpUrl(GOOGLE_BOOKS_API)
                .queryParam("q", "non-fiction")
                .queryParam("maxResults", 6)
                .queryParam("orderBy", "relevance")
                .queryParam("printType", "books")
                .queryParam("key", apiKey)
                .toUriString();

        BookResponse response = restTemplate.getForObject(url, BookResponse.class);

        return response != null ? response.getItems() : List.of();
    }
}
