package notes.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import notes.domain.NotesRepository;
import notes.exception.NotaNotFoundException;
import notes.model.Nota;

@Service
public class NoteService implements INoteService {
    Logger logger = LoggerFactory.getLogger(NoteService.class);

    @Autowired
    private NotesRepository repository;

    NoteService(NotesRepository repository) {
    this.repository = repository;
  }

  public Nota guardar(Nota nota) {
    Nota _nota = repository.save(nota);

    return _nota;
  }

  public List<Nota> recuperarTodasNotas() {
    
    return repository.findAll();
  }

  public Nota recuperarNota(Long id) {

      return repository.findById(id).orElseThrow(() -> new NotaNotFoundException(id));
  }

  public Nota actualizarNota(Nota nota, Long id) {

    return repository.findById(id)
    .map(notaActualizada -> {
        notaActualizada.setText(nota.getText());
        return repository.save(notaActualizada);
    })
    .orElseThrow(() -> new NotaNotFoundException(id));
  }

  public void borrarTodasNotas() {

    repository.deleteAll();
  }

  public void borrarNota(Long id) {
      
    repository.deleteById(id);
  }
  


}
