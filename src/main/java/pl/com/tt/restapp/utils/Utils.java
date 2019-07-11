package pl.com.tt.restapp.utils;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.stereotype.Component;
import pl.com.tt.restapp.domain.Movie;
import pl.com.tt.restapp.dto.MovieDTO;

import java.lang.reflect.InvocationTargetException;

@Component
public class Utils {

    public Movie mapperMovieDtoToMovieEntity(MovieDTO dto) throws InvocationTargetException, IllegalAccessException {
        Movie movie = new Movie();
        BeanUtilsBean.getInstance().copyProperties(movie, dto);
        return movie;
    }
}
