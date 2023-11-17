package ManueleSeretti.GestioneEventi.services;

import ManueleSeretti.GestioneEventi.Entities.Event;
import ManueleSeretti.GestioneEventi.Entities.Partecipation;
import ManueleSeretti.GestioneEventi.Entities.User;
import ManueleSeretti.GestioneEventi.exceptions.NotFoundException;
import ManueleSeretti.GestioneEventi.payload.NewPartecipationDTO;
import ManueleSeretti.GestioneEventi.repositories.EventRepository;
import ManueleSeretti.GestioneEventi.repositories.PartecipationRepository;
import ManueleSeretti.GestioneEventi.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PartecipationService {
    @Autowired
    private PartecipationRepository partecipationRepository;
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private EventRepository eventRepository;

    public Partecipation save(NewPartecipationDTO partecipationDTO) throws IOException {

        Partecipation p = new Partecipation();
        Event e = eventRepository.findById(partecipationDTO.eventId()).orElseThrow(() -> new NotFoundException(partecipationDTO.eventId()));
        User u = usersRepository.findById(partecipationDTO.userId()).orElseThrow(() -> new NotFoundException(partecipationDTO.userId()));

        p.setUser(u);
        p.setEvent(e);

        return partecipationRepository.save(p);
    }
}
