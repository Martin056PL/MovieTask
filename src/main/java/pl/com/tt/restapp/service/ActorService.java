package pl.com.tt.restapp.service;

import org.springframework.http.ResponseEntity;
import pl.com.tt.restapp.domain.Actor;
import pl.com.tt.restapp.domain.Movie;

import java.util.List;
import java.util.Optional;

public interface ActorService {

    List<Actor> findAllActors();

    Optional<Actor> findActorById(Long id);

    ResponseEntity<Actor> updateActorForProperMovie(Movie movieFromDatabase, Long actorId, Actor actorJSON);

    ResponseEntity<Actor> updateActor(Actor actor, Long actorId);

    ResponseEntity<Movie> saveActorToProperMovie(Movie movieFromDatabase, Actor actorJSON);

    void deleteActorById(Long id);

}
