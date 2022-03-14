package notes.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import notes.model.Nota;
import notes.model.Temperatura;
import notes.service.INoteService;
import notes.service.ITimeService;

@RestController
public class NoteController {
    Logger logger = LoggerFactory.getLogger(NoteController.class);

    NoteController() {}


    @Autowired
    private ITimeService timeService;

    @Autowired
    private INoteService noteService;


    @PostMapping("/notes")
    public Nota crearNota(@RequestBody Nota nota){
        logger.info("he entrado por POST/notes");
        
        //Se obtiene Temperatura de Madrid
        Temperatura temp = timeService.getByCity("Madrid");
        return noteService.guardar(new Nota(nota.getText(), temp.getTemp()));
    }

    //Todas las notas
    @GetMapping("/notes")
    List<Nota> all() {
        logger.info("he entrado por GET/notes");

        return noteService.recuperarTodasNotas();
    }

    //Una nota específica
    @GetMapping("/notes/{id}")
    Nota recuperarNota(@PathVariable Long id) {
        logger.info("he entrado por GET/notes/{id}");
    
        return noteService.recuperarNota(id);
    }

    @PutMapping("/notes/{id}")
    Nota actualizarNota(@RequestBody Nota crearNota, @PathVariable Long id) {
        logger.info("he entrado por PUT/notes/{id}");
        
        return noteService.actualizarNota(crearNota, id);
    }

    //Borrar todas las notas
    @DeleteMapping("/notes")
    void borrarNotas() {
        logger.info("he entrado por DELETE/notes");
        noteService.borrarTodasNotas();
    }

    //Borrar una específica
    @DeleteMapping("/notes/{id}")
    void borrarNota(@PathVariable Long id) {
        logger.info("he entrado por DELETE/notes/{id}");
        noteService.borrarNota(id);
    }

}
