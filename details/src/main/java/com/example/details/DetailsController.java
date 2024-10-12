package com.example.details;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class DetailsController {
    private final Map<Long, Book> books = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    /**
     * Returns all available details to a book in the bookstore
     *
     * @param bookId  The ID of the book to be requested
     * @param request request HTTP Request data sent by the browser
     * @return An ResponseEntity containing metadata combined with all available details about hte book
     */
    @GetMapping("/details/{bookId}")
    public ResponseEntity<Map<String, Object>> getDetails(@PathVariable("bookId") long bookId, HttpServletRequest request) {
        Map<String, Object> response = new LinkedHashMap<>();

        // Metadata
        response.put("Request", request.getMethod() + " /details/{bookId}");
        response.put("Content-Type", "application/json");
        response.put("Status Code", HttpStatus.OK.value()); // Status Code: 200

        if (books.containsKey(bookId)) {
            response.put("Payload", books.get(bookId));
        } else {
            response.put("Error", "Book not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}