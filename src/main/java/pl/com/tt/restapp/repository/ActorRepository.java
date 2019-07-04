package pl.com.tt.restapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.com.tt.restapp.domain.Actor;

import java.util.List;

@Repository
public interface ActorRepository extends CrudRepository<Actor, Long> {
    
    List<Actor> findAll();
    
    Actor findAllByActorId(Long id);
}
