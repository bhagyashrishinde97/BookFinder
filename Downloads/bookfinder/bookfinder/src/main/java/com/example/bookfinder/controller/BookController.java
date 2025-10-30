package com.example.bookfinder.controller;

import com.example.bookfinder.dto.GoogleBookDto;
import com.example.bookfinder.entity.FavoriteBook;
import com.example.bookfinder.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BookController {
    @Autowired
    private BookService service;

    @GetMapping("/search")
    public ResponseEntity<List<GoogleBookDto>> searchBooks(
            @RequestParam String q,
            @RequestParam(defaultValue = "10") int limit
    ) {
        return ResponseEntity.ok(service.searchBooks(q, limit));
    }

    @PostMapping("/favorites")
    public ResponseEntity<FavoriteBook> addFavorite(@RequestBody GoogleBookDto book) {
        return ResponseEntity.ok(service.saveFavorite(book));
    }

    @GetMapping("/favorites")
    public ResponseEntity<List<FavoriteBook>> getFavorites() {
        return ResponseEntity.ok(service.getAllFavorites());
    }

    @DeleteMapping("/favorites/{googleId}")
    public ResponseEntity<Void> removeFavorite(@PathVariable String googleId) {
        service.deleteFavorite(googleId);
        return ResponseEntity.noContent().build();
    }
}

