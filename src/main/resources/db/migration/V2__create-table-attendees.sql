CREATE TABLE attendees (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    event_id VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- uma chave de relaçao (attendees_event_id_fkey) que é uma foreign key 
    -- que referencia (event_id) relacionada a id da tabela de eventos
    -- ON DELETE RESTRICT bloqueia deletar o evento
    -- ON UPDATE CASCADE caso o id do evento seja atualizada afeta a tabela attendees
    CONSTRAINT attendees_event_id_fkey FOREIGN KEY (event_id) REFERENCES events (id) ON DELETE RESTRICT ON UPDATE CASCADE
);