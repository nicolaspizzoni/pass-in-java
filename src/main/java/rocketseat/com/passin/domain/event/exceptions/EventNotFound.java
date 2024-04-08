package rocketseat.com.passin.domain.event.exceptions;

public class EventNotFound extends RuntimeException {
    //construtor
    public EventNotFound(String message) {
        //super chama o construtor da classe extendida RuntimeException e passa o aprametro para ela
        super(message);
    }
}
