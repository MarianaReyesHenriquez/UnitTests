package notes.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import notes.model.Nota;

public interface NotesRepository extends JpaRepository<Nota, Long> {
    
}
