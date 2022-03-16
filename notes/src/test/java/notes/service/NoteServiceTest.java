package notes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import notes.model.Nota;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class NoteServiceTest {

    @Autowired
    NoteService noteService;

    private Nota nota1;
    private Nota nota2;

    @BeforeEach
    public void inicioTest() {
        Nota _nota1 = new Nota("Madrid", "20");
        nota1 = noteService.guardar(_nota1);
        Nota _nota2 = new Nota("Sevilla", "30");
        nota2 = noteService.guardar(_nota2);
    }

    @AfterEach
    public void finalTest() {
        noteService.borrarTodasNotas();
    }

    @Test
    void testGuardarNotaReturnNota() throws Exception {
        
        assertEquals("20", nota1.getTemp());
        assertEquals("Madrid", nota1.getText());
    }

    @Test
    void testRecuperarNotasReturnListaNotas() throws Exception {

        List<Nota> notasEsperadas = Arrays.asList(nota1, nota2);
        
        List<Nota> actualNotas = noteService.recuperarTodasNotas();
        
        assertEquals(notasEsperadas.size(), actualNotas.size());
        assertEquals(notasEsperadas.get(0).getText(), actualNotas.get(0).getText());
        assertEquals(notasEsperadas.get(0).getTemp(), actualNotas.get(0).getTemp());
        assertEquals(notasEsperadas.get(1).getText(), actualNotas.get(1).getText());
        assertEquals(notasEsperadas.get(1).getTemp(), actualNotas.get(1).getTemp());
    }

    @Test
    void testRecuperarNotaReturnNota() throws Exception {
        
        Nota actualNota = noteService.recuperarNota((long) 1);
        
        assertEquals("Madrid", actualNota.getText());
        assertEquals("20", actualNota.getTemp());
    }

}
