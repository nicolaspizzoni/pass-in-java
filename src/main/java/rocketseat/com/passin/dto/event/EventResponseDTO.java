package rocketseat.com.passin.dto.event;

import lombok.Getter;
import rocketseat.com.passin.domain.event.Event;

@Getter
public class EventResponseDTO {
    EventDetailDTO event;

    //construtor precisa ter mesmo nome da classe
    public EventResponseDTO(Event event, Integer numberOfAttendees) {
        //EventDetailDTO dto usado para preencher dados que serão retornados no get
        this.event = new EventDetailDTO(event.getId(), event.getTitle(), event.getDetails(), event.getSlug(), event.getMaximumAttendees(), numberOfAttendees);
    }
}
