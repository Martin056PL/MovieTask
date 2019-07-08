package pl.com.tt.restapp.service;

import pl.com.tt.restapp.domain.Actor;

import java.util.List;
import java.util.Optional;

public interface ActorService {

    List<Actor> findAllActors();

    Optional<Actor> findActorById(Long id);

    Actor saveActor(Actor actor);

    void deleteActorById(Long id);

}
