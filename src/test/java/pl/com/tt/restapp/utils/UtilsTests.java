package pl.com.tt.restapp.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import pl.com.tt.restapp.domain.Movie;
import pl.com.tt.restapp.dto.MovieDTO;

import java.time.LocalDate;
import java.util.Collections;

@RunWith(MockitoJUnitRunner.class)
public class UtilsTests {

    @Test
    public void should_transfer_data_from_movieDTO_to_movie() {
        MovieDTO movieDTO = new MovieDTO();
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
