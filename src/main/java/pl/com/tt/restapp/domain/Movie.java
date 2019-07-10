package pl.com.tt.restapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "movies")
public class Movie implements Serializable {

    private static final long serialVersionUID = 3683778473783051508L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long movieId;

    private String title;

    @Column(name = "data_premiere")
    private LocalDate datePremiere;

    private String type;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "movies_actors", joinColumns = { @JoinColumn(name = "movie_id") },
            inverseJoinColumns = { @JoinColumn(name = "actor_id") })
    private List<Actor> actors;


}
