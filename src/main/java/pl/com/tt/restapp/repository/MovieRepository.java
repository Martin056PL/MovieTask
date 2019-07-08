package pl.com.tt.restapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.com.tt.restapp.domain.Movie;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {

    List<Movie> findAll();
    Movie findAllByMovieId(Long id);

}