package pl.com.tt.restapp.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.com.tt.restapp.domain.Actor;
import pl.com.tt.restapp.domain.Movie;
import pl.com.tt.restapp.repository.ActorRepository;

import java.net.http.HttpClient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ActorServiceImplTests {

    @Mock
    ActorRepository repository;

    @Mock
    Actor actor;

    @Mock
    Movie movie;

    @Mock
    MovieServiceImpl movieService;

    @InjectMocks
    ActorServiceImpl service;

    private static final Long ID = 1L;

    @Test
    public void find_by_id_test() {
        service.findActorById(ID);
        Mockito.verify(repository).findAllByActorId(ID);
    }

    @Test
    public void find_all_actors() {
        service.findAllActors();
        Mockito.verify(repository).findAll();
    }

    @Test
    public void save_actor() {
        service.saveActor(actor);
        Mockito.verify(repository).save(actor);
    }

    @Test
    public void save_actor_to_proper_movie() {
        service.saveActorToProperMovie(movie, actor);
        Mockito.verify(movieService).saveMovie(movie);
    }

    @Test
    public void update_actor_for_proper_movie_which_exist() {
        when(movie.getActors()).thenReturn(someData());
        when(actor.getActorId()).thenReturn(1L);
        service.updateActorForProperMovie(movie, ID, actor);
        Mockito.verify(repository).save(actor);
    }

    @Test
    public void update_actor_for_proper_movie_which_does_not_exist() {
        when(movie.getActors()).thenReturn(Collections.emptyList());
        Assert.assertEquals(HttpStatus.BAD_REQUEST,service.updateActorForProperMovie(movie, ID, actor).getStatusCode());
    }

    @Test
    public void update_actor() {
        service.updateActor(actor, ID);
        Mockito.verify(repository).save(actor);
    }

    @Test
    public void delete_actor() {
        service.deleteActor(ID);
        Mockito.verify(repository).deleteById(ID);
    }

    @Test
    public void delete_actor_by_ID() {
        service.deleteActorById(movie, ID);
        Mockito.verify(repository).deleteById(ID);
        Mockito.verify(movieService).saveMovie(movie);
    }

    private List<Actor> someData(){
        List<Actor> list = new ArrayList<>();
        list.add(actor);
        return list;
    }

}
