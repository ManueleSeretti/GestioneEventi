package ManueleSeretti.GestioneEventi.repositories;

import ManueleSeretti.GestioneEventi.Entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
