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
import pl.com.tt.restapp.dto.MovieDTO;
import pl.com.tt.restapp.service.MovieServiceImpl;

import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MovieRestControllerTests {

    @Mock
    Movie movie;

    @Mock
    MovieDTO movieDTO;

    @Mock
    MovieServiceImpl movieService;

    @InjectMocks
    MovieRestController movieController;

    private static final Long ID = 23L;

    //get
    @Test
    public void should_status_code_bo_ok_when_controller_returns_not_empty_movie_list() {
        Assert.assertEquals(HttpStatus.OK, movieController.getAllMovies().getStatusCode());
    }

    @Test
    public void should_movie_list_has_the_same_size_as_movie_list_returned_from_controller() {
        when(movieService.findAllMovies()).thenReturn(Collections.singletonList(movie));
        Assert.assertEquals(Collections.singleton(movie).size(), movieController.getAllMovies().getBody().size());
    }

    @Test
    public void should_movie_list_be_as_empty_as_movie_list_returned_from_controller() {
        when(movieService.findAllMovies()).thenReturn(Collections.emptyList());
        Assert.assertEquals(Collections.emptyList(), movieController.getAllMovies().getBody());
    }

    @Test
    public void should_status_code_be_ok_when_controller_returns_movie_which_exist_by_movie_id() {
        when(movieService.findMovieById(ID)).thenReturn(Optional.of(movie));
        Assert.assertEquals(HttpStatus.OK, movieController.getAllMoviesById(ID).getStatusCode());
    }

    @Test
    public void should_body_response_be_equal_when_controller_returns_movie() {
        when(movieService.findMovieById(ID)).thenReturn(Optional.of(movie));
        Assert.assertEquals(movie, movieController.getAllMoviesById(ID).getBody());
    }

    @Test
    public void should_body_every_single_data_be_as_equal_as_movie_returned_by_controller() {
        List<Actor> listOfActors = movie.getActors();
        String title = movie.getTitle();
        String type = movie.getType();
        LocalDate datePremier = movie.getDatePremiere();

        when(movieService.findMovieById(ID)).thenReturn(Optional.of(movie));

        Assert.assertEquals(listOfActors, Objects.requireNonNull(movieController.getAllMoviesById(ID).getBody()).getActors());
        Assert.assertEquals(title, Objects.requireNonNull(movieController.getAllMoviesById(ID).getBody()).getTitle());
        Assert.assertEquals(type, Objects.requireNonNull(movieController.getAllMoviesById(ID).getBody()).getType());
        Assert.assertEquals(datePremier, Objects.requireNonNull(movieController.getAllMoviesById(ID).getBody()).getDatePremiere());
    }

    @Test
    public void should_return_status_code_not_found_when_controller_returns_movie_which_does_not_exist_by_movie_id() {
        when(movieService.findMovieById(ID)).thenReturn(Optional.empty());
        Assert.assertEquals(HttpStatus.NOT_FOUND, movieController.getAllMoviesById(ID).getStatusCode());
    }

    //post

    @Test
    public void should_return_status_created_when_controller_adds_new_movie() throws URISyntaxException, InvocationTargetException, IllegalAccessException {
        when(movieService.mappingToEntity(movieDTO)).thenReturn(movie);
        when(movieService.saveMovie(movie)).thenReturn(movie);
        Assert.assertEquals(HttpStatus.CREATED, movieController.saveMovie(movieDTO).getStatusCode());
    }

    //put
    @Test
    public void should_return_status_cod_ok_when_controller_will_find_movie_with_proper_id() throws InvocationTargetException, IllegalAccessException {
        when(movieService.mappingToEntity(movieDTO)).thenReturn(movie);
        when(movieService.findMovieById(ID)).thenReturn(Optional.of(movie));
        Assert.assertEquals(HttpStatus.OK, movieController.updateMovie(ID, movieDTO).getStatusCode());
    }

    @Test
    public void should_return_status_cod_bad_request_when_controller_will_not_find_movie_with_proper_id() throws InvocationTargetException, IllegalAccessException {
        when(movieService.mappingToEntity(movieDTO)).thenReturn(movie);
        when(movieService.findMovieById(ID)).thenReturn(Optional.empty());
        Assert.assertEquals(HttpStatus.BAD_REQUEST, movieController.updateMovie(ID, movieDTO).getStatusCode());
    }

    //delete
    @Test
    public void should_return_status_code_ok_when_delete_movie_which_exist_by_id() {
        when(movieService.findMovieById(ID)).thenReturn(Optional.of(movie));
        Assert.assertEquals(HttpStatus.OK, movieController.deleteMovie(ID).getStatusCode());
    }

    @Test
    public void should_return_status_code_bad_request_when_delete_movie_which_do_not_exist_by_id() {
        when(movieService.findMovieById(ID)).thenReturn(Optional.empty());
        Assert.assertEquals(HttpStatus.BAD_REQUEST, movieController.deleteMovie(ID).getStatusCode());
    }


}
