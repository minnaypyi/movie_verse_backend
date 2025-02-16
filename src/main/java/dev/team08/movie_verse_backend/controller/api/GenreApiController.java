package dev.team08.movie_verse_backend.controller.api;


import dev.team08.movie_verse_backend.dto.request.GenreRequest;
import dev.team08.movie_verse_backend.interfaces.IGenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreApiController {

    private final IGenreService genreService;

    public GenreApiController(IGenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/getallgenres")
    public ResponseEntity<List<GenreRequest>> getAllGenres() {
        return ResponseEntity.ok(genreService.getAllGenres());
    }
}
