package ManueleSeretti.GestioneEventi.repositories;

import ManueleSeretti.GestioneEventi.Entities.Partecipation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartecipationRepository extends JpaRepository<Partecipation, Long> {
}
