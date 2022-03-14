package notes.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
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

    @Autowired
    TimeService timeService;

    @Autowired
    NoteService noteService;


    @Test
    void testObtenerTemperatura() {

        //Preparacion
        TimeService timeService = Mockito.mock(TimeService.class);
        Mockito.when(timeService.getByCity("Madrid")).thenReturn(new Temperatura("Madrid", "20"));

        //Ejecucion
        String temp = timeService.getByCity("Madrid").getTemp();

        //Comparacion
        assertEquals("20", temp);
    
    }


    @Test
    void crearNota() throws Exception {
        Nota nota1 = new Nota("Madrid", "20");

        NoteService noteService = Mockito.mock(NoteService.class);
        Mockito.when(noteService.guardar(any(Nota.class))).thenReturn(nota1);
        Mockito.when(timeService.getByCity("Madrid")).thenReturn(new Temperatura("Madrid", "20"));

        mockMvc.perform(post("/notes/")
            .content(objectMapper.writeValueAsString(nota1))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.text", is("Madrid")))        
            .andExpect(jsonPath("$.tempC", is("20")));
        verify(noteService, times(1)).guardar(any(Nota.class));
        verify(timeService, times(1)).getByCity("Madrid");


    }


    @Test
    void testAll() {

        

    }


    @Test
    void testRecuperarNota() {

    }


    @Test
    void testActualizarNota() {

    }


    @Test
    void testBorrarNotas() {

    }


    @Test
    void testBorrarNota() {

    }
    
}
