package rocketseat.com.passin.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @RequestMapping mapeia quais end points esse controller sera responsavel
@RestController
@RequestMapping("/events")
public class EventController {
    // ResponseEntity<String> indica que será um metodo de resposta da entidade <Tipo de retorno>
    @GetMapping
    public ResponseEntity<String> getTeste() {
        return ResponseEntity.ok("Sucesso!");
    }
}
