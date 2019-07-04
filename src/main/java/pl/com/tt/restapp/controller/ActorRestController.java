package pl.com.tt.restapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import pl.com.tt.restapp.domain.Actor;
import pl.com.tt.restapp.service.ActorService;

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

    private ActorService service;

    @Autowired
    public ActorRestController(ActorService service) {
        this.service = service;
    }

    @GetMapping("get-all-actors")
    public List<Actor> getAllMovies() {
        return service.findAllActors();
    }

    @GetMapping("get-actor-by-id/{id}")
    public ResponseEntity<?> getAllMoviesById(@PathVariable Long id) {
        Optional<Actor> actorFromDataBase = service.findActorById(id);
        return actorFromDataBase.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("add-actor")
    public ResponseEntity<Actor> saveActor(@Valid @RequestBody Actor actor) throws URISyntaxException {
        Actor result = service.saveActor(actor);
        return ResponseEntity.created(new URI("add-movie" + result.getActorId()))
                .body(result);

    }

    @PutMapping("update-actor/{id}")
    public ResponseEntity<Actor> updateMovie(@PathVariable Long id, @Valid @RequestBody Actor actor){
        Optional<Actor> movieFromDatabase = service.findActorById(id);
        if(movieFromDatabase.isPresent()){
            actor.setActorId(id);
            Actor result = service.saveActor(actor);
            return ResponseEntity.ok().body(result);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("delete-actor-by-id/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        Optional<Actor> movieFromDatabase = service.findActorById(id);
        if(movieFromDatabase.isPresent()){
            service.deleteActorById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }
}

