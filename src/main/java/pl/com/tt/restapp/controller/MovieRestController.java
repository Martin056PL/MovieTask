package pl.com.tt.restapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import pl.com.tt.restapp.domain.Movie;
import pl.com.tt.restapp.dto.MovieDTO;
import pl.com.tt.restapp.service.MovieService;
import pl.com.tt.restapp.service.MovieServiceImpl;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


@CrossOrigin (origins = "http://localhost:3000")
@RestController
@RequestScope
@RequestMapping("/rest")
public class MovieRestController {

    private MovieService movieService;

    @Autowired
    public MovieRestController(MovieServiceImpl movieService) {
        this.movieService = movieService;
    }

    @GetMapping("movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return new ResponseEntity<>(movieService.findAllMovies(), HttpStatus.OK);
    }

    @GetMapping("movies/{id}")
    public ResponseEntity<Movie> getAllMoviesById(@PathVariable Long id) {
        Optional<Movie> movieFromDataBase = movieService.findMovieById(id);
        return movieFromDataBase.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("movies")
    public ResponseEntity<Movie> saveMovie(@Valid @RequestBody MovieDTO movieDTO) throws URISyntaxException, InvocationTargetException, IllegalAccessException {
        Movie movieTransformedFromDTO = movieService.mappingMovieDtoToEntity(movieDTO);
        Movie result = movieService.saveMovie(movieTransformedFromDTO);
        return ResponseEntity.created(new URI("add-movie" + result.getMovieId()))
                .body(result);

    }

    @PutMapping("movies/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @Valid @RequestBody MovieDTO movieDTO) throws InvocationTargetException, IllegalAccessException {
        Optional<Movie> movieFromDatabase = movieService.findMovieById(id);
        Movie movieTransformedFromDTO = movieService.mappingMovieDtoToEntity(movieDTO);
        if (movieFromDatabase.isPresent()) {
            movieTransformedFromDTO.setMovieId(id);
            Movie result = movieService.saveMovie(movieTransformedFromDTO);
            return ResponseEntity.ok().body(result);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("movies/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        Optional<Movie> movieFromDatabase = movieService.findMovieById(id);
        if (movieFromDatabase.isPresent()) {
            movieService.deleteMovieById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}

