package pl.com.tt.restapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.com.tt.restapp.domain.Actor;
import pl.com.tt.restapp.domain.Movie;
import pl.com.tt.restapp.dto.ActorDTO;
import pl.com.tt.restapp.repository.ActorRepository;
import pl.com.tt.restapp.utils.Utils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService {

    private ActorRepository repository;
    private MovieService movieService;
    private Utils utils;

    @Autowired
    public ActorServiceImpl(ActorRepository repository, MovieService movieService, Utils utils) {
        this.repository = repository;
        this.movieService = movieService;
        this.utils = utils;
    }

    @Override
    public Actor mappingActorDtoToEntity(ActorDTO actorDTO) throws InvocationTargetException, IllegalAccessException {
        return (Actor) utils.mapperFromDtoTOEntity(actorDTO);
    }


    @Override
    public List<Actor> findAllActors() {
        return repository.findAll();
    }

    @Override
    public Optional<Actor> findActorById(Long id) {
        Actor movieFromDataBase = repository.findAllByActorId(id);
        return Optional.ofNullable(movieFromDataBase);
    }

    @Override
    public ResponseEntity<Movie> saveActorToProperMovie(Movie movieFromDatabase, Actor actorJSON) {
        movieFromDatabase.getActors().add(actorJSON);
        Movie result = movieService.saveMovie(movieFromDatabase);
        return ResponseEntity.ok().body(result);
    }

    @Override
    public ResponseEntity<Actor> updateActorForProperMovie(Movie movieFromDatabase, Long actorId, Actor actorJSON) {
        List<Actor> actorsList = movieFromDatabase.getActors();
        Optional<Actor> optionalActorFromDataBaseBaseOnId = actorsList.stream()
                .filter(actor -> actor.getActorId().equals(actorId)).findAny();
        if (optionalActorFromDataBaseBaseOnId.isPresent()) {
            Actor result = repository.save(actorJSON);
            return ResponseEntity.ok().body(result);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<Actor> updateActor(Actor actor, Long actorId) {
        actor.setActorId(actorId);
        Actor result = saveActor(actor);
        return ResponseEntity.ok().body(result);
    }

    @Override
    public ResponseEntity<Actor> deleteActorById(Movie movie, Long actorId) {
        deleteActor(actorId);
        movieService.saveMovie(movie);
        return ResponseEntity.ok().build();
    }

    @Override
    public Actor saveActor(Actor actor) {
        return repository.save(actor);
    }

    @Override
    public void deleteActor(Long id) {
        repository.deleteById(id);
    }
}



