package rocketseat.com.passin.domain.event;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// @Entity Informa ao Jakarta que a classe representa uma entidade
// @Getter Lombok cria os getters de cada chave
@Entity
@Table(name = "events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    // declarar atributos que referenciam a tabela events
    // GeneratedValue informa ser um valor que é gerado automatico com a estrategia IDENTITY
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String details;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(nullable = false, name = "maximum_attendees")
    private Integer maximumAttendees;

    // Exemplo get
    // public String getTitle() {
    //     return this.title;
    // }

    // Exemplo setter
    // public void setTitle(String newTitle) {
    //     this.title = newTitle;
    // }

    // Exemplo AllArgsConstructor
    // instancia a classe para receber parametros
    // public Event(String title, String details ...) {}

    // Exemplo NoArgsConstructor
    // instancia a classe para NÃO receber parametros
    // public Event() {}
}
