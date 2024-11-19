package com.betrybe.museumfinder.solution;

import com.betrybe.museumfinder.dto.CollectionTypeCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.betrybe.museumfinder.service.CollectionTypeService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CollectionControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CollectionTypeService collectionTypeService;

  @Test
  @DisplayName("Testa se retornar número de museus com determinados tipos de coleção")
  void testCollectionTypeControllerOk() throws Exception {
    String[] input = {"hist","imag"};
    CollectionTypeCount collectionMock = new CollectionTypeCount( input, 492);

    Mockito
        .when(collectionTypeService.countByCollectionTypes(any()))
        .thenReturn(collectionMock);

    ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.get("/collections/count/{typesList}", String.join(",", input)));
    actions.andExpect(status().isOk());
    Mockito.verify(collectionTypeService).countByCollectionTypes(any());
  }

  @Test
  @DisplayName("Testa se retorna NotFound")
  void testCollectionTypeControllerNotFound() throws Exception {
    String[] blank = {};
    String[] path = {"X"};

    CollectionTypeCount collectionMock = new CollectionTypeCount(blank, 0);

    Mockito
        .when(collectionTypeService.countByCollectionTypes(any()))
        .thenReturn(collectionMock);

    ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.get("/collections/count/{typesList}", String.join(",", path)));
    actions.andExpect(status().isNotFound());
    Mockito.verify(collectionTypeService).countByCollectionTypes(any());
  }
}
