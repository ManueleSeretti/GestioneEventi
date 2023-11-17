package ManueleSeretti.GestioneEventi.services;

import ManueleSeretti.GestioneEventi.Entities.Event;
import ManueleSeretti.GestioneEventi.exceptions.NotFoundException;
import ManueleSeretti.GestioneEventi.payload.NewEventDTO;
import ManueleSeretti.GestioneEventi.repositories.EventRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class EventsService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UsersService usersService;

    @Autowired
    private Cloudinary cloudinary;


    public Event save(NewEventDTO eventDTO) throws IOException {

        Event newEvent = new Event();
        newEvent.setTitle(eventDTO.title());
        newEvent.setDescription(eventDTO.description());
        newEvent.setDate(eventDTO.data());
        newEvent.setLocation(eventDTO.location());
        newEvent.setPosti(eventDTO.posti());

        return eventRepository.save(newEvent);
    }

    public Page<Event> getEvents(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return eventRepository.findAll(pageable);
    }

    public Event findById(long id) throws NotFoundException {
        return eventRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(long id) throws NotFoundException {
        Event found = this.findById(id);
        eventRepository.delete(found);
    }

    public String uploadPicture(MultipartFile file, long id) throws IOException {
        Event e = this.findById(id);
        String imgUrl = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        e.setImgUrl(imgUrl);
        eventRepository.save(e);
        return imgUrl;
    }

}
