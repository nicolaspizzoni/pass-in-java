package rocketseat.com.passin.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rocketseat.com.passin.domain.attendee.Attendee;
import rocketseat.com.passin.domain.checkin.CheckIn;
import rocketseat.com.passin.dto.attendee.AttendeeDetails;
import rocketseat.com.passin.dto.attendee.AttendeesListResponseDTO;
import rocketseat.com.passin.repositories.AttendeeRepository;
import rocketseat.com.passin.repositories.CheckInRepository;

@Service
@RequiredArgsConstructor
public class AttendeeService {
    //necessário o final para saber que são atributos required para o constructor que sera gerado
    private final AttendeeRepository attendeeRepository;
    private final CheckInRepository checkInRepository;

    public List<Attendee> getAllAttendeesFromEvent(String eventId) {
        List<Attendee> attendeesList = this.attendeeRepository.findByEventId(eventId);

        return attendeesList;
    }

    public AttendeesListResponseDTO getEventsAttendee(String eventId) {
        List<Attendee> attendeeList = this.getAllAttendeesFromEvent(eventId);

        //buscar checkin de cada um dos attendees
        // pegando o id da tabela attendee e checando na tabela checkin
        List<AttendeeDetails> attendeeDetailsList = attendeeList.stream().map(attendee -> {
            //passando o id de cada attendee ao repository checkin, que consulta a tabela pelo entity
            Optional<CheckIn> checkIn = this.checkInRepository.findByAttendeeId(attendee.getId());

            LocalDateTime checkedInAt = checkIn.isPresent() ? checkIn.get().getCreatedAt() : null;

            return new AttendeeDetails(attendee.getId(), attendee.getName(), attendee.getEmail(), attendee.getCreatedAt(), checkedInAt);
        }).toList();

        return new AttendeesListResponseDTO(attendeeDetailsList);
    }
}
