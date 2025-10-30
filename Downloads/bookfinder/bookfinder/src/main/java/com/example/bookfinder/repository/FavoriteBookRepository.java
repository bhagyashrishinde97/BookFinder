package com.example.bookfinder.repository;

import com.example.bookfinder.entity.FavoriteBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoriteBookRepository  extends JpaRepository<FavoriteBook, Long> {
    Optional<FavoriteBook> findByGoogleId(String googleId);
        void deleteByGoogleId(String googleId);
    }

