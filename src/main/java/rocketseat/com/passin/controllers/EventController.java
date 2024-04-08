package rocketseat.com.passin.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import rocketseat.com.passin.dto.attendee.AttendeesListResponseDTO;
import rocketseat.com.passin.dto.event.EventIdDTO;
import rocketseat.com.passin.dto.event.EventRequestDTO;
import rocketseat.com.passin.dto.event.EventResponseDTO;
import rocketseat.com.passin.services.AttendeeService;
import rocketseat.com.passin.services.EventService;

// @RequestMapping mapeia quais end points esse controller sera responsavel
@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    // ResponseEntity<String> indica que será um metodo de resposta da entidade <Tipo de retorno>
    // @GetMapping
    // public ResponseEntity<String> getTeste() {
    //     return ResponseEntity.ok("Sucesso!");
    // }

    private final EventService serviceEvent;
    private final AttendeeService serviceAttendee;

    //Receber id por query params
    @GetMapping("/{eventId}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable String eventId) {
        EventResponseDTO event = this.serviceEvent.getEventDetail(eventId);
        return ResponseEntity.ok(event);
    }

    @PostMapping
    public ResponseEntity<EventIdDTO> createEvent(@RequestBody EventRequestDTO body, UriComponentsBuilder uriComponentsBuilder) {
        EventIdDTO eventIdDTO = this.serviceEvent.createEvent(body);
        var uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(eventIdDTO.eventId()).toUri();

        return ResponseEntity.created(uri).body(eventIdDTO);
    }

    @GetMapping("/attendees/{id}")
    public ResponseEntity<AttendeesListResponseDTO> getEventAttendees(@PathVariable String id) {
        AttendeesListResponseDTO attendeesListResponse = this.serviceAttendee.getEventsAttendee(id);
        return ResponseEntity.ok(attendeesListResponse);
    }
}
