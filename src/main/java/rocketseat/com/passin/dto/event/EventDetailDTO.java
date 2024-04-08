package rocketseat.com.passin.dto.event;

//Record objeto mais simplificado para transferencia ou representação de dados, não mutavel
public record EventDetailDTO(
    String id,
    String title,
    String details,
    String slug,
    Integer maximumAttendees,
    Integer attendeesAmount
) {

}
