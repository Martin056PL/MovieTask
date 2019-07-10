package pl.com.tt.restapp.service;

import pl.com.tt.restapp.domain.Movie;
import pl.com.tt.restapp.dto.MovieDTO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

public interface MovieService {

    Movie mappingToEntity(MovieDTO dto) throws InvocationTargetException, IllegalAccessException;

    List<Movie> findAllMovies();

    Optional<Movie> findMovieById(Long id);

    Movie saveMovie(Movie movie);

    void deleteMovieById(Long id);
}
