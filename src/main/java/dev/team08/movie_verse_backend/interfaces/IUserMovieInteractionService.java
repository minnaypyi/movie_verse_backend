package dev.team08.movie_verse_backend.interfaces;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import dev.team08.movie_verse_backend.entity.UserMovieInteraction;
import dev.team08.movie_verse_backend.enums.LikeStatus;

public interface IUserMovieInteractionService {
    void logMovieView(UUID userId, Integer tmdbMovieId);
    void markMovieAsWatched(UUID userId, Integer tmdbMovieId);
    Optional<UserMovieInteraction> getUserMovieInteraction(UUID userId, Integer tmdbMovieId);
	void toggleFavorite(UUID userId, Integer tmdbMovieId);
	void toggleWatchlist(UUID userId, Integer tmdbMovieId);
	void likeOrDislikeMovie(UUID userId, Integer tmdbMovieId, LikeStatus likeStatus);
	void addOrUpdateReview(UUID userId, Integer tmdbMovieId, String reviewText, boolean isEdit);
	List<UserMovieInteraction> getAllUserMovieInteractionsByUser(UUID userID);
	List<Map<String, Object>> getUserInteractions(String token);
	List<Map<String, Object>> callPythonRecommendApi(List<Map<String, Object>> userInteractions);
	
}
