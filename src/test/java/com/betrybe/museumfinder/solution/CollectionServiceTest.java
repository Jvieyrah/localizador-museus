package com.betrybe.museumfinder.solution;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.service.CollectionTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class CollectionServiceTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MuseumFakeDatabase database;


  @Test
  public void testCountByCollectionTypesService() throws Exception {
    String[] input = {"Art","History"};
    when(database.countByCollectionType("Art")).thenReturn(5L);
    when(database.countByCollectionType("History")).thenReturn(3L);

    // Calling the endpoint to be tested
    mockMvc.perform(MockMvcRequestBuilders.get("/collections/count/{typesList}", String.join(",", input))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.collectionTypes").isArray())
//        .andExpect(jsonPath("$.collectionTypes").value(new String[]{"Art", "History"}))
        .andExpect(jsonPath("$.count").value(8));
  }
}
