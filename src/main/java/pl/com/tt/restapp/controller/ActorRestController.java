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

    @GetMapping("movies/{id}/actors")
    public ResponseEntity<?> getAllActorsFromMovieByIdMovie(@PathVariable Long id) {
        Optional<Movie> movieFromDataBase = movieService.findMovieById(id);
        return movieFromDataBase.map(response -> ResponseEntity.ok().body(response.getActors()))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("movies/{movieId}/actors/{actorId}")
    public ResponseEntity<?> getProperActorBaseOnActorIdFromMovieByIdMovie(@PathVariable Long movieId,@PathVariable Long actorId) {
        Optional<Movie> movieFromDataBase = movieService.findMovieById(movieId);
        return movieFromDataBase.map(response -> ResponseEntity.ok().body(response.getActors().stream().filter(actor -> actor.getActorId().equals(actorId))))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("actors")
    public ResponseEntity<Actor> saveActor(@Valid @RequestBody Actor actor) throws URISyntaxException {
        Actor result = actorService.saveActor(actor);
        return ResponseEntity.created(new URI("add-movie" + result.getActorId()))
                .body(result);

    }

    @PutMapping("actors/{id}")
    public ResponseEntity<Actor> updateMovie(@PathVariable Long id, @Valid @RequestBody Actor actor){
        Optional<Actor> movieFromDatabase = actorService.findActorById(id);
        if(movieFromDatabase.isPresent()){
            actor.setActorId(id);
            Actor result = actorService.saveActor(actor);
            return ResponseEntity.ok().body(result);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("actors/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        Optional<Actor> movieFromDatabase = actorService.findActorById(id);
        if(movieFromDatabase.isPresent()){
            actorService.deleteActorById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }
}

