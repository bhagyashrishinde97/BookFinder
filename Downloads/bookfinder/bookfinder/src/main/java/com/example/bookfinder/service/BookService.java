package com.example.bookfinder.service;

import com.example.bookfinder.dto.GoogleBookDto;
import com.example.bookfinder.entity.FavoriteBook;
import com.example.bookfinder.entity.ImageLinks;
import com.example.bookfinder.entity.VolumeInfo;
import com.example.bookfinder.repository.FavoriteBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BookService {

    private final FavoriteBookRepository repository;

    // Create WebClient instance
    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://www.googleapis.com/books/v1")
            .build();

    @SuppressWarnings("unchecked")
    public List<GoogleBookDto> searchBooks(String query, int limit) {
        Mono<Map> response = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/volumes")
                        .queryParam("q", query)
                        .queryParam("maxResults", limit)
                        .build())
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().isError()) {
                        return Mono.error(new RuntimeException("Google Books API error: " + clientResponse.statusCode()));
                    }
                    return clientResponse.bodyToMono(Map.class);
                });



        Map<String, Object> map = response.block();
        if (map == null || !map.containsKey("items")) return Collections.emptyList();

        List<Map<String, Object>> items = (List<Map<String, Object>>) map.get("items");
        List<GoogleBookDto> dtos = new ArrayList<>();

        for (Map<String, Object> item : items) {
            GoogleBookDto dto = new GoogleBookDto();
            dto.setId((String) item.get("id"));

            Map<String, Object> volumeInfo = (Map<String, Object>) item.get("volumeInfo");
            if (volumeInfo != null) {
                VolumeInfo vi = new VolumeInfo();
                vi.setTitle((String) volumeInfo.get("title"));
                vi.setAuthors((List<String>) volumeInfo.get("authors"));
                vi.setPublisher((String) volumeInfo.get("publisher"));
                vi.setPublishedDate((String) volumeInfo.get("publishedDate"));
                vi.setDescription((String) volumeInfo.get("description"));

                Map<String, Object> imageLinks = (Map<String, Object>) volumeInfo.get("imageLinks");
                if (imageLinks != null) {
                    ImageLinks il = new ImageLinks();
                    il.setSmallThumbnail((String) imageLinks.get("smallThumbnail"));
                    il.setThumbnail((String) imageLinks.get("thumbnail"));
                    vi.setImageLinks(il);
                }
                dto.setVolumeInfo(vi);
            }
            dtos.add(dto);
        }
        return dtos;
    }

    public FavoriteBook saveFavorite(GoogleBookDto dto) {
        Optional<FavoriteBook> existing = repository.findByGoogleId(dto.getId());
        if (existing.isPresent()) return existing.get();

        FavoriteBook book = new FavoriteBook();
        book.setGoogleId(dto.getId());

        if (dto.getVolumeInfo() != null) {
            book.setTitle(dto.getVolumeInfo().getTitle());
            book.setAuthors(dto.getVolumeInfo().getAuthors() != null
                    ? String.join(", ", dto.getVolumeInfo().getAuthors()) : "");
            book.setPublisher(dto.getVolumeInfo().getPublisher());
            book.setPublishedDate(dto.getVolumeInfo().getPublishedDate());
            book.setDescription(dto.getVolumeInfo().getDescription());
            if (dto.getVolumeInfo().getImageLinks() != null) {
                book.setThumbnail(dto.getVolumeInfo().getImageLinks().getThumbnail());
            }
        }

        return repository.save(book);
    }

    public List<FavoriteBook> getAllFavorites() {
        return repository.findAll();
    }
    @Transactional
    public void deleteFavorite(String googleId) {
        repository.deleteByGoogleId(googleId);
    }
}
