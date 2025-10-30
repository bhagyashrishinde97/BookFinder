package com.example.bookfinder.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "favorite_books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoriteBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String googleId;
    private String title;

    @Column(length = 2000)
    private String authors;

    private String publisher;
    private String publishedDate;

    @Column(length = 4000)
    private String description;

    private String thumbnail;
}
