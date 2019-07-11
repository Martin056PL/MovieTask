package pl.com.tt.restapp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import pl.com.tt.restapp.domain.Movie;
import pl.com.tt.restapp.dto.MovieDTO;
import pl.com.tt.restapp.repository.MovieRepository;
import pl.com.tt.restapp.utils.Utils;

import java.lang.reflect.InvocationTargetException;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceImplTests {

    @Mock
    MovieRepository repository;

    @Mock
    Utils utils;

    @Mock
    Movie movie;

    @Mock
    MovieDTO movieDTO;

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
    public void should_verify_saving_movies() {
        service.saveMovie(movie);
        Mockito.verify(repository).save(movie);
    }

    @Test
    public void should_verify_deleting_movies() {
        service.deleteMovieById(ID);
        Mockito.verify(repository).deleteById(ID);
    }

    @Test
    public void should_map_from_DTO_to_Entity() throws InvocationTargetException, IllegalAccessException {
        service.mappingToEntity(movieDTO);
        Mockito.verify(utils).mapperMovieDtoToMovieEntity(movieDTO);
    }



}
