package pl.com.tt.restapp.service;

import pl.com.tt.restapp.domain.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    List<Movie> findAllMovies();

    Optional<Movie> findMovieById(Long id);

    Movie saveMovie(Movie movie);

    void deleteMovieById(Long id);
}
