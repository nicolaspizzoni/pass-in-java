package rocketseat.com.passin.services;

import java.text.Normalizer;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rocketseat.com.passin.domain.attendee.Attendee;
import rocketseat.com.passin.domain.event.Event;
import rocketseat.com.passin.domain.event.exceptions.EventNotFound;
import rocketseat.com.passin.dto.event.EventIdDTO;
import rocketseat.com.passin.dto.event.EventRequestDTO;
import rocketseat.com.passin.dto.event.EventResponseDTO;
import rocketseat.com.passin.repositories.EventRepository;

@Service
// RequiredArgsConstructor gera o construtor para a classe
@RequiredArgsConstructor
public class EventService {
    // instancia do event repository para manipular a tabela de eventos
    private final EventRepository eventRepository;
    private final AttendeeService attendeeService;

    public EventResponseDTO getEventDetail(String eventId) {
        // utiliza a instancia do repository de evento (que manipula as entidades)
        // para achar um cadastro e atribuir a variavel do tipo Event
        Event event = this.eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFound("Event not found with ID: " + eventId));
        List<Attendee> attendeeList = this.attendeeService.getAllAttendeesFromEvent(eventId);

        return new EventResponseDTO(event, attendeeList.size());
    }

    public EventIdDTO createEvent(EventRequestDTO eventDTO) {
        Event newEvent = new Event();
        newEvent.setTitle(eventDTO.title());
        newEvent.setDetails(eventDTO.details());
        newEvent.setMaximumAttendees(eventDTO.maximumAttendees());
        newEvent.setSlug(this.createSlug(eventDTO.title()));

        //Persistir entidade no BD
        this.eventRepository.save(newEvent);

        //retornar o id do novo evento criado
        return new EventIdDTO(newEvent.getId());
    }

    private String createSlug(String text) {
        // Decomposição canônica, separar letras de acentuação São = Sa~o
        // Depois aplicar regex e remover apenas acentos
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        return normalized.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]", "")
                    .replaceAll("[^\\w\\s]", "")
                    .replaceAll("\\s+", "-")
                    .toLowerCase();
    }

    //Não necessário o constructor por conta do RequiredArgsConstructor
    // public EventService(EventRepository repository){
    // this.eventRepository = repository;
    // }
}
