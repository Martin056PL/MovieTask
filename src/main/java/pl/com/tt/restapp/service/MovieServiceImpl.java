package pl.com.tt.restapp.service;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.tt.restapp.domain.Movie;
import pl.com.tt.restapp.dto.MovieDTO;
import pl.com.tt.restapp.repository.MovieRepository;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository repository;

    @Autowired
    public MovieServiceImpl(MovieRepository repository) {
        this.repository = repository;
    }

    @Override
    public Movie mappingToEntity(MovieDTO dto) throws InvocationTargetException, IllegalAccessException {
        Movie movie = new Movie();
        BeanUtilsBean bean = BeanUtilsBean.getInstance();
        bean.copyProperties(movie, dto);
        return movie;
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