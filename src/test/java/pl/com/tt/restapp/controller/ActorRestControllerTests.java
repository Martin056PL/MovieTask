package pl.com.tt.restapp.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import pl.com.tt.restapp.domain.Actor;
import pl.com.tt.restapp.domain.Movie;
import pl.com.tt.restapp.service.ActorServiceImpl;
import pl.com.tt.restapp.service.MovieServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ActorRestControllerTests {

    @Mock
    Actor actor;

    @Mock
    Movie movie;

    @Mock
    ActorServiceImpl actorService;

    @Mock
    MovieServiceImpl movieService;

    @InjectMocks
    ActorRestController controller;

    private static final Long actorID = 1L;
    private static final Long movieID = 1L;

    @Test
    public void asd(){
        Assert.assertEquals(HttpStatus.OK, controller.getAllActors().getStatusCode());
    }

    @Test
    public void should_movie_list_has_the_same_size_as_movie_list_returned_from_controller() {
        when(actorService.findAllActors()).thenReturn(Collections.singletonList(actor));
        Assert.assertEquals(Collections.singleton(actor).size(), controller.getAllActors().getBody().size());
    }

    @Test
    public void should_return_status_ok_when_controller_returns_actor_base_on_id() {
        when(actorService.findActorById(actorID)).thenReturn(Optional.of(actor));
        Assert.assertEquals(HttpStatus.OK, controller.getActorByIdFromAllDatabase(actorID).getStatusCode());
    }

    @Test
    public void should_return_status_not_found_when_controller_does_not_return_actor_base_on_id() {
        when(actorService.findActorById(actorID)).thenReturn(Optional.empty());
        Assert.assertEquals(HttpStatus.NOT_FOUND, controller.getActorByIdFromAllDatabase(actorID).getStatusCode());
    }

    @Test
    public void should_body_response_be_equal_when_controller_returns_actor_by_id() {
        when(actorService.findActorById(actorID)).thenReturn(Optional.of(actor));
        Assert.assertEquals(actor, controller.getActorByIdFromAllDatabase(actorID).getBody());
    }

    @Test
    public void should_body_response_be_equal_when_controller_returns_actor_by_movie_id() {
        when(movieService.findMovieById(movieID)).thenReturn(Optional.of(movie));
        List<Actor> actorsList = movie.getActors();
        Assert.assertEquals(actorsList, controller.getAllActorsFromMovieByIdMovie(movieID).getBody());
    }

    @Test
    public void should_return_status_ok_when_when_controller_returns_actor_by_movie_id() {
        when(movieService.findMovieById(movieID)).thenReturn(Optional.of(movie));
        Assert.assertEquals(HttpStatus.OK, controller.getAllActorsFromMovieByIdMovie(movieID).getStatusCode());
    }

    @Test
    public void should_return_status_bed_request_when_controller_does_not_returns_actor_by_movie_id() {
        when(movieService.findMovieById(movieID)).thenReturn(Optional.empty());
        Assert.assertEquals(HttpStatus.NOT_FOUND, controller.getAllActorsFromMovieByIdMovie(movieID).getStatusCode());
    }

    @Test
    public void asdd(){
        when(movieService.findMovieById(movieID)).thenReturn(Optional.of(movie));
        when(actorService.findActorById(actorID)).thenReturn(Optional.empty());
        Assert.assertEquals(HttpStatus.OK,controller.deleteMovie(movieID,actorID).getStatusCode());
    }

    @Test
    public void asdd2(){
        when(movieService.findMovieById(movieID)).thenReturn(Optional.of(movie));
        when(actorService.findActorById(actorID)).thenReturn(Optional.of(actor));
        Assert.assertEquals(HttpStatus.BAD_REQUEST,controller.deleteMovie(movieID,actorID).getStatusCode());
    }

}
