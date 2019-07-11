package pl.com.tt.restapp.utils;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.stereotype.Component;
import pl.com.tt.restapp.domain.Actor;
import pl.com.tt.restapp.domain.Movie;
import pl.com.tt.restapp.dto.ActorDTO;
import pl.com.tt.restapp.dto.MovieDTO;

import java.lang.reflect.InvocationTargetException;

@Component
public class Utils {

    public Object mapperFromDtoTOEntity(Object o) throws InvocationTargetException, IllegalAccessException {
        if (o.getClass() == MovieDTO.class) {
            Movie movie = new Movie();
            BeanUtilsBean.getInstance().copyProperties(movie, o);
            return movie;
        } else if (o.getClass() == ActorDTO.class){
            Actor actor = new Actor();
            BeanUtilsBean.getInstance().copyProperties(actor, o);
            return actor;
        } else {
            throw new IllegalAccessException();
        }
    }
}
