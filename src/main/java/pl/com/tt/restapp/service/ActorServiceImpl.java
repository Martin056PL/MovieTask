package pl.com.tt.restapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.tt.restapp.domain.Actor;
import pl.com.tt.restapp.repository.ActorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService {

    private ActorRepository repository;

    @Autowired
    public ActorServiceImpl(ActorRepository repository) {
        this.repository = repository;
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
    public Actor saveActor(Actor actor) {
        return repository.save(actor);
    }

    @Override
    public void deleteActorById(Long id) {
        repository.deleteById(id);
    }


}
