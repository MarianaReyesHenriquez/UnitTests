package notes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import notes.domain.NotesRepository;
import notes.model.Nota;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@EnableAutoConfiguration
@AutoConfigureMockMvc
public class NoteServiceTest {
    
    @Autowired
    NoteService noteService;
  
    @MockBean
    NotesRepository mockRepository;


    @Test
    void testGuardarNotaReturnsNota() throws Exception {
        Nota nota = new Nota("Madrid", "20");

        when(mockRepository.save(any(Nota.class))).thenReturn(nota);

        Nota actualNote = noteService.guardar(nota);

        assertEquals(actualNote.getTemp(), "20");
        assertEquals(actualNote.getText(), "Madrid");
    }

}
