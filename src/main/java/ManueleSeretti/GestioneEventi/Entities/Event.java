package ManueleSeretti.GestioneEventi.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String description;
    private String location;
    private LocalDate date;
    private int posti;
    private String imgUrl;

    @OneToMany(mappedBy = "event")
    @JsonIgnore
    private Set<Partecipation> participants;
}
