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
@RequestMapping("/rest")
public class MovieRestController {

    private MovieService movieService;

    @Autowired
    public MovieRestController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("get-all-movies")
    public List<Movie> getAllMovies() {
        return movieService.findAllMovies();
    }

    @GetMapping("get-movie-by-id/{id}")
    public ResponseEntity<?> getAllMoviesById(@PathVariable Long id) {
        Optional<Movie> movieFromDataBase = movieService.findMovieById(id);
        return movieFromDataBase.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("get-movie-by-id/{id}/actors")
    public ResponseEntity<?> getActorsFromMovieByIdMovie(@PathVariable Long id) {
        Optional<Movie> movieFromDataBase = movieService.findMovieById(id);
        return movieFromDataBase.map(response -> ResponseEntity.ok().body(response.getActors()))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("add-movie")
    public ResponseEntity<Movie> saveMovie(@Valid @RequestBody Movie movie) throws URISyntaxException {
        Movie result = movieService.saveMovie(movie);
        return ResponseEntity.created(new URI("add-movie" + result.getMovieId()))
                .body(result);

    }

    @PutMapping("update-movie/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @Valid @RequestBody Movie movie){
        Optional<Movie> movieFromDatabase = movieService.findMovieById(id);
        if(movieFromDatabase.isPresent()){
            movie.setMovieId(id);
            Movie result = movieService.saveMovie(movie);
            return ResponseEntity.ok().body(result);
        }else{
           return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("delete-movie-by-id/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        Optional<Movie> movieFromDatabase = movieService.findMovieById(id);
        if(movieFromDatabase.isPresent()){
            movieService.deleteMovieById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }
}

