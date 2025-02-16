package dev.team08.movie_verse_backend.service;

import dev.team08.movie_verse_backend.dto.request.GenreRequest;
import dev.team08.movie_verse_backend.entity.Genre;
import dev.team08.movie_verse_backend.interfaces.IGenreService;
import dev.team08.movie_verse_backend.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService implements IGenreService {
    @Autowired
    private GenreRepository genreRepository;

    public List<GenreRequest> getAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        return genres.stream()
                .map(genre -> new GenreRequest(genre.getId(), genre.getName()))
                .collect(Collectors.toList());
    }
}
