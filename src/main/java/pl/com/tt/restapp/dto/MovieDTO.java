package pl.com.tt.restapp.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import pl.com.tt.restapp.domain.Actor;

import java.time.LocalDate;
import java.util.List;

public class MovieDTO {

    private Long movieId;

    private String title;

    private LocalDate datePremiere;

    private String type;

    private List<Actor> actors;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDatePremiere() {
        return datePremiere;
    }

    public void setDatePremiere(LocalDate datePremiere) {
        this.datePremiere = datePremiere;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }
}
