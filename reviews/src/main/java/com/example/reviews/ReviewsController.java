package com.example.reviews;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ReviewsController {
    private final Map<Long, List<Review>> reviews = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    /**
     * Returns all existing reviews to a book in the bookstore
     *
     * @param bookId  The ID of the book
     * @param request request HTTP Request data sent by the browser
     * @return An ResponseEntity containing metadata combined with all existing reviews about the book
     */
    @GetMapping("/reviews/{bookId}")
    public ResponseEntity<Map<String, Object>> getReviews(@PathVariable("bookId") long bookId, HttpServletRequest request) {
        Map<String, Object> response = new LinkedHashMap<>();

        // Metadata
        response.put("Request", request.getMethod() + " /reviews/{bookId}");
        response.put("Content-Type", "application/json");
        response.put("Status Code", HttpStatus.OK.value()); // Status Code: 200

        if (reviews.containsKey(bookId)) {
            response.put("Payload", reviews.get(bookId));
        } else {
            response.put("Error", "Book not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}