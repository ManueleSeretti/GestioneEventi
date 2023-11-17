package ManueleSeretti.GestioneEventi.controllers;

import ManueleSeretti.GestioneEventi.Entities.Event;
import ManueleSeretti.GestioneEventi.exceptions.BadRequestException;
import ManueleSeretti.GestioneEventi.payload.NewEventDTO;
import ManueleSeretti.GestioneEventi.services.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventsService eventService;

    @GetMapping("/{id}")
    Event findEventById(@PathVariable long id) {
        return eventService.findById(id);
    }

    @GetMapping
    Page<Event> getAllEvents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size) {
        return eventService.getEvents(page, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    Event save(@RequestBody @Validated NewEventDTO eventDTO, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return eventService.save(eventDTO);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void findEventByIdAndDelete(@PathVariable long id) {
        eventService.findByIdAndDelete(id);
    }

    @PostMapping("/upload/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String uploadExample(@RequestParam("image") MultipartFile body, @PathVariable long id) throws IOException {
        System.out.println(body.getSize());
        System.out.println(body.getContentType());
        return eventService.uploadPicture(body, id);
    }
}
