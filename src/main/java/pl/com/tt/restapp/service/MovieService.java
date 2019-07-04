package pl.com.tt.restapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.tt.restapp.domain.Movie;
import pl.com.tt.restapp.repository.MovieRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private MovieRepository repository;

    @Autowired
    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public List<Movie> findAllMovies(){
        return repository.findAll();
    }

    public Optional<Movie> findMovieById(Long id) {
        Movie movieFromDataBase = repository.findAllByMovieId(id);
        return Optional.ofNullable(movieFromDataBase);

    }

    public Movie saveMovie(Movie movie) {
        return repository.save(movie);
    }

    public void deleteMovieById(Long id) {
        repository.deleteById(id);
    }



}