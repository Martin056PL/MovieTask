package pl.com.tt.restapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.tt.restapp.domain.Actor;
import pl.com.tt.restapp.repository.ActorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ActorService {

    private ActorRepository repository;

    @Autowired
    public ActorService(ActorRepository repository) {
        this.repository = repository;
    }

    public List<Actor> findAllActors() {
        return repository.findAll();
    }

    public Optional<Actor> findActorById(Long id) {
        Actor movieFromDataBase = repository.findAllByActorId(id);
        return Optional.ofNullable(movieFromDataBase);

    }

    public Actor saveActor(Actor actor) {
        return repository.save(actor);
    }

    public void deleteActorById(Long id) {
        repository.deleteById(id);
    }


}
