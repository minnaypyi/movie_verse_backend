package dev.team08.movie_verse_backend.controller.api;

import dev.team08.movie_verse_backend.entity.UserMovieInteraction;
import dev.team08.movie_verse_backend.enums.LikeStatus;
import dev.team08.movie_verse_backend.interfaces.IUserMovieInteractionService;
import dev.team08.movie_verse_backend.interfaces.IUserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-interactions")
public class UserMovieInteractionApiController {

    private final IUserMovieInteractionService userMovieInteractionService;
    private final IUserService userService;

    public UserMovieInteractionApiController(IUserMovieInteractionService userMovieInteractionService, IUserService userService) {
        this.userMovieInteractionService = userMovieInteractionService;
        this.userService = userService;
    }

    @PostMapping("/view/{tmdb_movie_id}")
    public ResponseEntity<String> logMovieView(
            @RequestHeader("Authorization") String token,
            @PathVariable Integer tmdb_movie_id) {

        UUID userId = extractUserIdFromToken(token);
        userMovieInteractionService.logMovieView(userId, tmdb_movie_id);
        return ResponseEntity.ok("Movie view logged successfully.");
    }

    @PutMapping("/watched/{tmdb_movie_id}")
    public ResponseEntity<String> markMovieAsWatched(
            @RequestHeader("Authorization") String token,
            @PathVariable Integer tmdb_movie_id) {

        UUID userId = extractUserIdFromToken(token);
        userMovieInteractionService.markMovieAsWatched(userId, tmdb_movie_id);
        return ResponseEntity.ok("Movie marked as watched.");
    }
    
    @PostMapping("/like/{tmdb_movie_id}")
    public ResponseEntity<String> likeOrDislikeMovie(
            @RequestHeader("Authorization") String token,
            @PathVariable Integer tmdb_movie_id,
            @RequestParam LikeStatus likeStatus) {

        UUID userId = extractUserIdFromToken(token);
        userMovieInteractionService.likeOrDislikeMovie(userId, tmdb_movie_id, likeStatus);
        return ResponseEntity.ok("Movie interaction updated.");
    }

    @PostMapping("/favorite/{tmdb_movie_id}")
    public ResponseEntity<String> toggleFavorite(
            @RequestHeader("Authorization") String token,
            @PathVariable Integer tmdb_movie_id) {

        UUID userId = extractUserIdFromToken(token);
        userMovieInteractionService.toggleFavorite(userId, tmdb_movie_id);
        return ResponseEntity.ok("Favorite status updated.");
    }

    @PostMapping("/watchlist/{tmdb_movie_id}")
    public ResponseEntity<String> toggleWatchlist(
            @RequestHeader("Authorization") String token,
            @PathVariable Integer tmdb_movie_id) {

        UUID userId = extractUserIdFromToken(token);
        userMovieInteractionService.toggleWatchlist(userId, tmdb_movie_id);
        return ResponseEntity.ok("Watchlist status updated.");
    }

    @GetMapping("/{tmdb_movie_id}")
    public ResponseEntity<Optional<UserMovieInteraction>> getUserMovieInteraction(
            @RequestHeader("Authorization") String token,
            @PathVariable Integer tmdb_movie_id) {

        UUID userId = extractUserIdFromToken(token);
        Optional<UserMovieInteraction> interaction = userMovieInteractionService.getUserMovieInteraction(userId, tmdb_movie_id);
        return ResponseEntity.ok(interaction);
    }
    
    @GetMapping("/recommend")
    public Object getUserInteractions(@RequestHeader("Authorization") String token){
        try {
            // 获取用户交互数据
            List<Map<String, Object>> userInteractions = userMovieInteractionService.getUserInteractions(token);

            // 调用 Python 推荐服务
            List<Map<String, Object>> recommendations = userMovieInteractionService.callPythonRecommendApi(userInteractions);

            return ResponseEntity.ok(recommendations);
        } catch (Exception e) {
            // 捕获错误并返回 HTTP 500 响应
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonList(Map.of("error", e.getMessage())));
        }
    }

    private UUID extractUserIdFromToken(String token) {
        return userService.getUserFromToken(token).getId();
    }
}
