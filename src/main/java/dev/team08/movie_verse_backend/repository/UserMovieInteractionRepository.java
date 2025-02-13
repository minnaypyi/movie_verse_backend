package dev.team08.movie_verse_backend.repository;

import dev.team08.movie_verse_backend.entity.UserMovieInteraction;
import dev.team08.movie_verse_backend.entity.ids.UserMovieInteractionId;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserMovieInteractionRepository extends JpaRepository<UserMovieInteraction, UserMovieInteractionId> {

    Optional<UserMovieInteraction> findByUser_IdAndTmdbMovieId(UUID userId, Integer tmdbMovieId);

    List<UserMovieInteraction> findByUser_Id(UUID userId);

	List<UserMovieInteraction> findAllByUser_Id(UUID userId);
    
    
}
