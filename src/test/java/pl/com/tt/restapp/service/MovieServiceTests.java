package pl.com.tt.restapp.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import pl.com.tt.restapp.domain.Actor;
import pl.com.tt.restapp.domain.Movie;
import pl.com.tt.restapp.repository.MovieRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTests {

    private final List<Movie> db = initialMockData();

    @Mock
    MovieRepository repository;

    @InjectMocks
    MovieService service;// = new MovieService(repository);


    @Test
    public void findByIdTest() {
        service.findMovieById(1L);
        Mockito.verify(repository).findById(1L);
    }

    @Test
    public void should_return_default_repository() {
        //given
        given(repository.findAll()).willReturn(initialMockData());
        //when
        List<Movie> movies = service.findAllMovies();
        //then
        Assert.assertEquals(3, movies.size());
    }

    @Test
    public void should_add_new_movie_in_repository() {
        //given
        when(repository.findAll()).thenReturn(db);
        when(repository.save(any())).thenAnswer(invocationOnMock -> {
            Movie argument = invocationOnMock.getArgument(0);
            db.add(argument);
            return null;
        });

        List<Actor> actorsFromMovie1 = new ArrayList<>();
        actorsFromMovie1.add(new Actor(1L, "Marian", "Paździoch", 80));
        Movie movie = new Movie(4L, "Swiat wg Kiepskich", LocalDate.parse("1999-01-12"), "obyczaj", actorsFromMovie1);

        //when
        service.saveMovie(movie);

        List<Movie> movies = service.findAllMovies();

        //repository.save(movie);
        //then
        // Mockito.verify(repository).save(movie);
        Assert.assertEquals(4, movies.size());
    }


    private List<Movie> initialMockData() {
        List<Movie> movies = new ArrayList<>();
        List<Actor> actorsFromMovie1 = new ArrayList<>();
        List<Actor> actorsFromMovie2 = new ArrayList<>();
        List<Actor> actorsFromMovie3 = new ArrayList<>();

        actorsFromMovie1.add(new Actor(1L, "Piotr", "Fronczewski", 55));
        actorsFromMovie1.add(new Actor(2L, "Bozenka", "z Klanu", 18));

        actorsFromMovie2.add(new Actor(3L, "Maria ma", "syna Maria", 1000));
        actorsFromMovie2.add(new Actor(4L, "Myk myk", "elegancja Francja", 74));

        actorsFromMovie3.add(new Actor(5L, "Brad", "Pit", 20));
        actorsFromMovie3.add(new Actor(6L, "Dlaczego", "fioletowy", 30));

        /*movies.add(new Movie("Film 1", LocalDate.parse("2001-12-12"), "akcja", actorsFromMovie1));
        movies.add(new Movie("Film 2", LocalDate.parse("1999-01-11"), "horror", actorsFromMovie2));
        movies.add(new Movie("Film 3", LocalDate.parse("2010-08-05"), "komedia", actorsFromMovie3));*/

        return movies;
    }
}


