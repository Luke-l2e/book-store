package com.example.books;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Restservice controller for books
 */
@RestController
public class BooksController {
    private final List<Book> books = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    /**
     * Returns all available books in the bookstore
     *
     * @param request HTTP Request data sent by the browser
     * @return An ResponseEntity containing metadata combined with all available books
     */
    @GetMapping("/books")
    public ResponseEntity<Map<String, Object>> getBooks(HttpServletRequest request) {
        Map<String, Object> response = new LinkedHashMap<>();

        // Metadata
        response.put("Request", request.getMethod() + " /books");
        response.put("Content-Type", "application/json");
        response.put("Status Code", HttpStatus.OK.value()); // Status Code: 200
        response.put("Payload", books);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Adds a book to the bookstore
     *
     * @param author  The author of the book
     * @param title   The title of the book
     * @param request HTTP Request data sent by the browser
     * @return An ResponseEntity containing metadata combined with the newly added book
     */
    @GetMapping("/add")
    public ResponseEntity<Map<String, Object>> addBook(@RequestParam String author, @RequestParam String title, HttpServletRequest request) {
        Book book = new Book(counter.incrementAndGet(), author, title);
        books.add(book);
        Map<String, Object> response = new LinkedHashMap<>();

        // Metadata
        response.put("Request", request.getMethod() + " /add");
        response.put("Content-Type", "application/json");
        response.put("Status Code", HttpStatus.OK.value()); // Status Code: 200
        response.put("Payload", book);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}