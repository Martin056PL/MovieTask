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
    public ResponseEntity<List<Actor>> getAllActors() {
        return new ResponseEntity<>(actorService.findAllActors(), HttpStatus.OK);
    }

    @GetMapping("actors/{id}")
    public ResponseEntity<?> getActorByIdFromAllDatabase(@PathVariable Long id) {
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
    public ResponseEntity<Movie> saveActorToProperMovie(@PathVariable Long movieId, @RequestBody Actor actor) {
        Optional<Movie> movieFromDataBase = movieService.findMovieById(movieId);
        if (movieFromDataBase.isPresent()) {
            return actorService.saveActorToProperMovie(movieFromDataBase.get(), actor);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("movies/{movieId}/actors/{actorId}")
    public ResponseEntity<Actor> updateActorInProperMovieByMovieId(@PathVariable Long movieId, @PathVariable Long actorId, @Valid @RequestBody Actor actorJSON) {
        Optional<Movie> movieFromDatabase = movieService.findMovieById(movieId);
        if (movieFromDatabase.isPresent()) {
            return actorService.updateActorForProperMovie(movieFromDatabase.get(), actorId, actorJSON);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    /*@PutMapping("movie/{actorId}/actors")
    public ResponseEntity<Actor> updateMovie(@PathVariable Long actorId, @Valid @RequestBody Actor actor) {
        Optional<Actor> movieFromDatabase = actorService.findActorById(actorId);
        if (movieFromDatabase.isPresent()) {
            return actorService.updateActor(actor,actorId);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }*/

    @DeleteMapping("movies/{movieId}/actors/{actorId}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long movieId, @PathVariable Long actorId) {
        Optional<Movie> optionalMovieFromDatabase = movieService.findMovieById(movieId);
        if (optionalMovieFromDatabase.isPresent()) {
        actorService.deleteActor(actorId);
        return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }

    }
}

