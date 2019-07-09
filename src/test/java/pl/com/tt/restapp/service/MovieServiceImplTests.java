package pl.com.tt.restapp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import pl.com.tt.restapp.domain.Movie;
import pl.com.tt.restapp.repository.MovieRepository;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceImplTests {

    @Mock
    MovieRepository repository;

    @Mock
    Movie movie;

    @InjectMocks
    MovieServiceImpl service;

    private static final long ID = 1;

    @Test
    public void findByIdTest() {
        service.findMovieById(ID);
        Mockito.verify(repository).findAllByMovieId(ID);
    }

    @Test
    public void find_all_movies() {
        service.findAllMovies();
        Mockito.verify(repository).findAll();
    }

    @Test
    public void should_verify_saving_movies(){
        service.saveMovie(movie);
        Mockito.verify(repository).save(movie);
    }

    @Test
    public void should_verify_deleting_movies(){
        service.deleteMovieById(ID);
        Mockito.verify(repository).deleteById(ID);
    }

}
