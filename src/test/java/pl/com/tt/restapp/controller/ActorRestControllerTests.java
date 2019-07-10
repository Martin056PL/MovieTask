package pl.com.tt.restapp.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.com.tt.restapp.domain.Actor;
import pl.com.tt.restapp.domain.Movie;
import pl.com.tt.restapp.service.ActorServiceImpl;
import pl.com.tt.restapp.service.MovieServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
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
    public void should_return_status_ok_when_controller_returns_all_actors() {
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
    public void should_return_status_ok_when_controller_returns_actor_by_movie_id() {
        when(movieService.findMovieById(movieID)).thenReturn(Optional.of(movie));
        Assert.assertEquals(HttpStatus.OK, controller.getAllActorsFromMovieByIdMovie(movieID).getStatusCode());
    }

    @Test
    public void should_return_status_bed_request_when_controller_does_not_returns_actor_by_movie_id() {
        when(movieService.findMovieById(movieID)).thenReturn(Optional.empty());
        Assert.assertEquals(HttpStatus.NOT_FOUND, controller.getAllActorsFromMovieByIdMovie(movieID).getStatusCode());
    }

    @Test
    public void should_return_status_ok_when_controller_returns_actor_by_movie_id_and_actor_id(){
        when(movieService.findMovieById(movieID)).thenReturn(Optional.of(movie));
        Assert.assertEquals(HttpStatus.OK,controller.getProperActorBaseOnActorIdFromMovieByIdMovie(movieID,actorID).getStatusCode());
    }

    @Test
    public void should_return_status_not_found_when_controller_does_not_return_actor_by_movie_id_and_actor_id(){
        when(movieService.findMovieById(movieID)).thenReturn(Optional.empty());
        Assert.assertEquals(HttpStatus.NOT_FOUND,controller.getProperActorBaseOnActorIdFromMovieByIdMovie(movieID,actorID).getStatusCode());
    }

    //post
    @Test
    public void should_return_status_ok_when_controller_add_actor_to_movie_by_movie_id() {
        when(movieService.findMovieById(movieID)).thenReturn(Optional.of(movie));
        when(actorService.saveActorToProperMovie(movie,actor)).thenReturn(ResponseEntity.ok().build());
        Assert.assertEquals(HttpStatus.OK,controller.saveActorToProperMovie(movieID,actor).getStatusCode());
    }

    @Test
    public void should_return_status_bad_request_when_controller_add_actor_to_movie_by_not_existing_movie_id() {
        when(movieService.findMovieById(movieID)).thenReturn(Optional.empty());
        when(actorService.saveActorToProperMovie(movie,actor)).thenReturn(ResponseEntity.badRequest().build());
        Assert.assertEquals(HttpStatus.BAD_REQUEST,controller.saveActorToProperMovie(movieID,actor).getStatusCode());
    }

    //put
    @Test
    public void should_return_status_ok_when_controller_update_actor_by_movie_id_and_actor_id() {
        when(movieService.findMovieById(movieID)).thenReturn(Optional.of(movie));
        when(actorService.updateActorForProperMovie(movie, actorID, actor)).thenReturn(ResponseEntity.ok(actor));
        Assert.assertEquals(HttpStatus.OK, controller.updateActorInProperMovieByMovieId(movieID, actorID, actor).getStatusCode());
    }

    @Test
    public void should_return_status_ok_when_controller_update_actor_by_movie_id_and_actor_id1() {
        when(movieService.findMovieById(movieID)).thenReturn(Optional.empty());
        when(actorService.updateActorForProperMovie(movie, actorID, actor)).thenReturn(ResponseEntity.badRequest().build());
        Assert.assertEquals(HttpStatus.BAD_REQUEST, controller.updateActorInProperMovieByMovieId(movieID, actorID, actor).getStatusCode());
    }

    //delete
    @Test
    public void should_return_status_ok_when_controller_delete_actor_by_movie_id_which_exist_and_actor_id() {
        when(movieService.findMovieById(movieID)).thenReturn(Optional.of(movie));
        Assert.assertEquals(HttpStatus.OK, controller.deleteMovie(movieID, actorID).getStatusCode());
    }

    @Test
    public void should_return_status_ok_when_controller_delete_actor_by_movie_id_which_do_not_exist_and_actor_i() {
        when(movieService.findMovieById(movieID)).thenReturn(Optional.empty());
        Assert.assertEquals(HttpStatus.BAD_REQUEST, controller.deleteMovie(movieID, actorID).getStatusCode());
    }

}
