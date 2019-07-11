package pl.com.tt.restapp.utils;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import pl.com.tt.restapp.domain.Actor;
import pl.com.tt.restapp.domain.Movie;
import pl.com.tt.restapp.dto.ActorDTO;
import pl.com.tt.restapp.dto.MovieDTO;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.Collections;

@RunWith(MockitoJUnitRunner.class)
public class UtilsTests {

    private Utils utils;

    @Before
    public void intMapper() {
        utils = new Utils();
    }

    @Test
    public void should_transfer_data_from_movieDTO_to_movie() throws InvocationTargetException, IllegalAccessException {
        MovieDTO movieDTO = new MovieDTO();

        movieDTO.setTitle("asd");
        movieDTO.setType("dsa");
        movieDTO.setActors(Collections.emptyList());
        movieDTO.setDatePremiere(LocalDate.parse("2000-12-12"));

        Movie movie = (Movie) utils.mapperFromDtoTOEntity(movieDTO);

        Assert.assertEquals(movieDTO.getTitle(), movie.getTitle());
        Assert.assertEquals(movieDTO.getType(), movie.getType());
        Assert.assertEquals(movieDTO.getActors(), movie.getActors());
        Assert.assertEquals(movieDTO.getDatePremiere(), movie.getDatePremiere());
    }

    @Test
    public void should_transfer_data_from_actorDTO_to_actor() throws InvocationTargetException, IllegalAccessException {
        ActorDTO actorDTO = new ActorDTO();

        actorDTO.setFirstName("Tomek");
        actorDTO.setLastName("Kowalski");
        actorDTO.setAge(12);

        Actor actor = (Actor) utils.mapperFromDtoTOEntity(actorDTO);

        Assert.assertEquals(actorDTO.getFirstName(), actor.getFirstName());
        Assert.assertEquals(actorDTO.getLastName(), actor.getLastName());
        Assert.assertEquals(actorDTO.getAge(), actor.getAge());
    }

    @Test(expected = IllegalAccessException.class)
    public void should_throw_Illegal_argument_exception() throws InvocationTargetException, IllegalAccessException {
        Object o = new Object();
        utils.mapperFromDtoTOEntity(o);
    }

}
