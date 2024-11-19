package com.betrybe.museumfinder.solution;

import static org.mockito.ArgumentMatchers.any;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.model.Museum;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class MuseuTest {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private MuseumFakeDatabase database;

  @Test
  void testGetMuseum() throws Exception {
    Museum museum = new Museum();
    museum.setName("Museu");
    Mockito.when(database.getMuseum(1L))
        .thenReturn(Optional.of(museum));
    mockMvc.perform(MockMvcRequestBuilders.get("/museums/1"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    Mockito.verify(database).getMuseum(any());
  }

  @Test
  void testGetMuseumNotFound() throws Exception {
    Mockito.when(database.getMuseum(1L))
        .thenReturn(Optional.empty());
    mockMvc.perform(MockMvcRequestBuilders.get("/museums/1"))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    Mockito.verify(database).getMuseum(any());
  }
}
