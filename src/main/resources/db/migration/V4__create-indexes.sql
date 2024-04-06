-- Otimizar as consultas feitas ao banco de dados e estabelecer regras para
-- manter consistencia dos dados seguindo as regras de negócio
CREATE UNIQUE INDEX events_slug_key ON events(slug);
CREATE UNIQUE INDEX attendees_event_id_email_key ON attendees(event_id, email);
CREATE UNIQUE INDEX check_ins_attendee_id_key ON check_ins(attendee_id);