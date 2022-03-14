package notes.service;

import java.util.List;

import notes.model.Nota;

public interface  INoteService {
    Nota guardar(Nota nota);
    List<Nota> recuperarTodasNotas();
    Nota recuperarNota(Long id);
    Nota actualizarNota(Nota nota, Long id);
    void borrarTodasNotas();
    void borrarNota(Long id);
}
