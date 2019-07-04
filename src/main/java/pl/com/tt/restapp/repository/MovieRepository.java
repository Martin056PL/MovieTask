package pl.com.tt.restapp.repository;

import pl.com.tt.restapp.domain.Actor;
import pl.com.tt.restapp.domain.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {

    List<Movie> findAll();
    Movie findAllByMovieId(Long id);
    Movie findAllByTitle(String title);

}