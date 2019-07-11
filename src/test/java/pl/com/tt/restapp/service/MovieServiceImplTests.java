package pl.com.tt.restapp.service;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import pl.com.tt.restapp.domain.Movie;
import pl.com.tt.restapp.dto.MovieDTO;
import pl.com.tt.restapp.repository.MovieRepository;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceImplTests {

    @Mock
    MovieRepository repository;

    @Mock
    BeanUtilsBean bean;

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

    /*@Test
    public void asd() throws InvocationTargetException, IllegalAccessException {
        service.mappingToEntity(movieDTO);
        //when(service.mappingToEntity(movieDTO)).thenReturn(movie);
        Assert.assertEquals(movie,service.mappingToEntity(movieDTO));
    }*/

    @Test
    public void should_transfer_data_from_movieDTO_to_movie() {
        ModelMapper mapper = new ModelMapper();

        movieDTO.setTitle("asd");
        movieDTO.setType("dsa");
        movieDTO.setActors(Collections.emptyList());
        movieDTO.setDatePremiere(LocalDate.parse("2000-12-12"));

        Movie movie = mapper.map(movieDTO, Movie.class);

        Assert.assertEquals(movieDTO.getTitle(),movie.getTitle());
        Assert.assertEquals(movieDTO.getType(),movie.getType());
        Assert.assertEquals(movieDTO.getActors(),movie.getActors());
        Assert.assertEquals(movieDTO.getDatePremiere(),movie.getDatePremiere());
    }

}
