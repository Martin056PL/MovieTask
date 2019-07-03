package pl.com.tt.restapp.domain;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Getter @Setter @ToString
@EqualsAndHashCode

@Entity
@Table(name = "actors")
public class Actor implements Serializable {

    private static final long serialVersionUID = 6460140826650392604L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private Long actorId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private int age;
}
