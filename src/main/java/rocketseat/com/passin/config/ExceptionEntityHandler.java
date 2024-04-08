package rocketseat.com.passin.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import rocketseat.com.passin.domain.event.exceptions.EventNotFound;

//ControllerAdvice classe que captura excessão lançada pelos controllers
@ControllerAdvice
public class ExceptionEntityHandler {
    //informa que é um metodo que lida com a excessão EventNotFound.class
    @ExceptionHandler(EventNotFound.class)
    public ResponseEntity handleEventNotFound(EventNotFound exception) {
        return ResponseEntity.notFound().build();
    }
}
