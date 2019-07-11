package pl.com.tt.restapp.service;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.tt.restapp.domain.Movie;
import pl.com.tt.restapp.dto.MovieDTO;
import pl.com.tt.restapp.repository.MovieRepository;
import pl.com.tt.restapp.utils.Utils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository repository;
    private Utils utils;

    @Autowired
    public MovieServiceImpl(MovieRepository repository, Utils utils) {
        this.repository = repository;
        this.utils = utils;
    }

    @Override
    public Movie mappingToEntity(MovieDTO dto) throws InvocationTargetException, IllegalAccessException {
        return utils.mapperMovieDtoToMovieEntity(dto);
    }

    @Override
    public List<Movie> findAllMovies() {
        return repository.findAll();
    }

    @Override
    public Optional<Movie> findMovieById(Long id) {
        Movie movieFromDataBase = repository.findAllByMovieId(id);
        return Optional.ofNullable(movieFromDataBase);
    }

    @Override
    public Movie saveMovie(Movie movie) {
        return repository.save(movie);
    }

    @Override
    public void deleteMovieById(Long id) {
        repository.deleteById(id);
    }

}