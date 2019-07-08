package pl.com.tt.restapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import pl.com.tt.restapp.domain.Actor;
import pl.com.tt.restapp.domain.Movie;
import pl.com.tt.restapp.service.ActorService;
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
public class ActorRestController {

    private ActorService actorService;
    private MovieService movieService;

    @Autowired
    public ActorRestController(ActorService actorService, MovieService movieService) {
        this.actorService = actorService;
        this.movieService = movieService;
    }

    @GetMapping("actors")
    public List<Actor> getAllMovies() {
        return actorService.findAllActors();
    }

    @GetMapping("actors/{id}")
    public ResponseEntity<?> getAllMoviesById(@PathVariable Long id) {
        Optional<Actor> actorFromDataBase = actorService.findActorById(id);
        return actorFromDataBase.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("movies/{movieId}/actors")
    public ResponseEntity<?> getAllActorsFromMovieByIdMovie(@PathVariable Long movieId) {
        Optional<Movie> movieFromDataBase = movieService.findMovieById(movieId);
        return movieFromDataBase.map(response -> ResponseEntity.ok().body(response.getActors()))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("movies/{movieId}/actors/{actorId}")
    public ResponseEntity<?> getProperActorBaseOnActorIdFromMovieByIdMovie(@PathVariable Long movieId, @PathVariable Long actorId) {
        Optional<Movie> movieFromDataBase = movieService.findMovieById(movieId);
        return movieFromDataBase.map(response -> ResponseEntity.ok().body(response.getActors().stream().filter(actor -> actor.getActorId().equals(actorId))))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("movies/{movieId}/actors")
    public ResponseEntity<Movie> saveActorToProperMovie(@PathVariable Long movieId, @RequestBody Actor actor) throws URISyntaxException {
        Optional<Movie> movieFromDataBase = movieService.findMovieById(movieId);
        if (movieFromDataBase.isPresent()) {
            Movie movie = movieFromDataBase.get();
            movie.getActors().add(actor);
            Movie result = movieService.saveMovie(movie);
            return ResponseEntity.created(new URI("add-movie" + result))
                    .body(result);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("actors")
    public ResponseEntity<Actor> saveActor(@Valid @RequestBody Actor actor) throws URISyntaxException {
        Actor result = actorService.saveActor(actor);
        return ResponseEntity.created(new URI("add-actor" + result.getActorId()))
                .body(result);

    }

    @PutMapping("movies/{movieId}/actors/{actorId}")
    public ResponseEntity<Actor> updateActorInProperMovieByMovieId(@PathVariable Long movieId, @PathVariable Long actorId, @Valid @RequestBody Actor actorJSON) throws URISyntaxException {
        Optional<Movie> movieFromDatabase = movieService.findMovieById(movieId);
        if (movieFromDatabase.isPresent()) {
            List<Actor> actorsList = movieFromDatabase.get().getActors();
            Optional<Actor> optionalActorFromDataBaseBaseOnId = actorsList.stream().filter(actor -> actor.getActorId().equals(actorId)).findAny();
            if (optionalActorFromDataBaseBaseOnId.isPresent()) {
                Actor actor = optionalActorFromDataBaseBaseOnId.get();
                Actor result = actorService.saveActor(actorJSON);
                return ResponseEntity.ok().body(result);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("movie/{id}/actors")
    public ResponseEntity<Actor> updateMovie(@PathVariable Long id, @Valid @RequestBody Actor actor) {
        Optional<Actor> movieFromDatabase = actorService.findActorById(id);
        if (movieFromDatabase.isPresent()) {
            actor.setActorId(id);
            Actor result = actorService.saveActor(actor);
            return ResponseEntity.ok().body(result);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    /*@DeleteMapping("movies/{movieId}/actors/{actorId}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long movieId, @PathVariable Long actorId) {
        Optional<Movie> optionalMovieFromDatabase = movieService.findMovieById(movieId);
        if (optionalMovieFromDatabase.isPresent()) {
            Movie movieFromDataBase = optionalMovieFromDatabase.get();
            List<Actor> listOfActorsInMovie = movieFromDataBase.getActors();
            Optional<Actor> optionalActorFromDataBase = listOfActorsInMovie.stream().filter(searchedActor -> searchedActor.getActorId().equals(actorId)).findAny();
            if (optionalActorFromDataBase.isPresent()) {
                actorService.deleteActorById(actorId);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }*/


    @DeleteMapping("movies/{movieId}/actors/{actorId}")
    public ResponseEntity<?> deleteMovie2(@PathVariable Long movieId, @PathVariable Long actorId) {
        Optional<Movie> optionalMovieFromDatabase = movieService.findMovieById(movieId);
        if (optionalMovieFromDatabase.isPresent()) {
            // Optional<Actor> actor  = actorService.findActorById(actorId);
            Movie movie = optionalMovieFromDatabase.get();
            actorService.deleteActorById(actorId);
            movieService.saveMovie(movie);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }

    }
}

