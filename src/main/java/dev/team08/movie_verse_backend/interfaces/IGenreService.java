package dev.team08.movie_verse_backend.interfaces;

import dev.team08.movie_verse_backend.dto.request.GenreRequest;
import dev.team08.movie_verse_backend.entity.Genre;

import java.util.List;

public interface IGenreService {
    List<GenreRequest> getAllGenres();
}
