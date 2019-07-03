package pl.com.tt.restapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import pl.com.tt.restapp.domain.Movie;
import pl.com.tt.restapp.service.MovieService;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


@CrossOrigin
@RestController
@RequestScope
public class MovieRestController {

    private MovieService service;

    @Autowired
    public MovieRestController(MovieService service) {
        this.service = service;
    }

    @GetMapping("get-all-movies")
    public List<Movie> getAllMovies() {
        return service.findAllMovies();
    }

    @GetMapping("get-movie-by-id/{id}")
    public ResponseEntity<?> getAllMoviesById(@PathVariable Long id) {
        Optional<Movie> movieFromDataBase = service.findMovieById(id);
        return movieFromDataBase.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("add-movie")
    public ResponseEntity<Movie> saveMovie(@Valid @RequestBody Movie movie) throws URISyntaxException {
        Movie result = service.saveMovie(movie);
        return ResponseEntity.created(new URI("add-movie)" + result.getMovieId()))
                .body(result);

    }

    @PutMapping("update-movie/{id}")
    public ResponseEntity<Movie> updateMovie(@Valid @RequestBody Movie movie){
        Movie result = service.saveMovie(movie);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("delete-movie-by-id/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        service.deleteMovieById(id);
        return ResponseEntity.ok().build();
    }
}

