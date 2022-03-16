package notes.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import notes.model.Nota;
import notes.model.Temperatura;
import notes.service.NoteService;
import notes.service.TimeService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    TimeService timeService;

    @MockBean
    NoteService noteService;


    @Test
    void crearNota_returnCreated() throws Exception {
        Nota nota1 = new Nota("Madrid", "20");

        Mockito.when(noteService.guardar(any(Nota.class))).thenReturn(nota1);
        Mockito.when(timeService.getByCity("Madrid")).thenReturn(new Temperatura("Madrid", "20"));

        mockMvc.perform(post("/notes/")
                .content(objectMapper.writeValueAsString(nota1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.text", is("Madrid")))
                .andExpect(jsonPath("$.temp", is("20")));
        verify(noteService, times(1)).guardar(any(Nota.class));
        verify(timeService, times(1)).getByCity("Madrid");

    }

    @Test
    void testRecuperarNotas() throws Exception {
        List<Nota> notas = Arrays.asList(
                new Nota("Madrid", "20"),
                new Nota("Sevilla", "30"));
        Mockito.when(noteService.recuperarTodasNotas()).thenReturn(notas);

        mockMvc.perform(get("/notes/"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].text", is("Madrid")))
            .andExpect(jsonPath("$[0].temp", is("20")))
            .andExpect(jsonPath("$[1].text", is("Sevilla")))
            .andExpect(jsonPath("$[1].temp", is("30")));

        verify(noteService, times(1)).recuperarTodasNotas();

    }

    @Test
    void testRecuperarNota() throws Exception {
        Nota nota = new Nota("Madrid", "20");

        Mockito.when(noteService.recuperarNota((long) 1)).thenReturn(nota);
        mockMvc.perform(get("/notes/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.text", is("Madrid")))
            .andExpect(jsonPath("$.temp", is("20")));

        verify(noteService, times(1)).recuperarNota((long) 1);
    }

    @Test
    void testActualizarNota() throws Exception {
        Nota notaOriginal = new Nota("Madrid", "20");
        Nota notaModificada = new Nota("Sevilla", "21");

        Mockito.when(noteService.recuperarNota((long) 1)).thenReturn(notaOriginal);

        Mockito.when(noteService.actualizarNota(any(Nota.class), any(Long.class))).thenReturn(notaModificada);
        
        mockMvc.perform(put("/notes/1")
            .content(objectMapper.writeValueAsString(notaModificada))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.text", is("Sevilla")))
            .andExpect(jsonPath("$.temp", is("21")));

        verify(noteService, times(1)).actualizarNota(any(Nota.class), any(Long.class));
    }

    @Test
    void testBorrarNotas() throws Exception {
        doNothing().when(noteService).borrarTodasNotas();
        mockMvc.perform(delete("/notes")).andExpect(status().isOk());
        verify(noteService, times(1)).borrarTodasNotas();
    }

    @Test
    void testBorrarNota() throws Exception {
        doNothing().when(noteService).borrarNota(anyLong());
        
        mockMvc.perform(delete("/notes/1")).andExpect(status().isOk());
        verify(noteService, times(1)).borrarNota((long) 1);
    }

}
