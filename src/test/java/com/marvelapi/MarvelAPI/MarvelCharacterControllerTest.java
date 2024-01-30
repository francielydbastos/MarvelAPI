package com.marvelapi.MarvelAPI;

import com.marvelapi.MarvelAPI.controller.MarvelCharacterController;
import com.marvelapi.MarvelAPI.exception.CharacterNotFoundException;
import com.marvelapi.MarvelAPI.response.CharacterResponse;
import com.marvelapi.MarvelAPI.response.ThumbnailResponse;
import com.marvelapi.MarvelAPI.service.MarvelCharacterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@WebMvcTest(MarvelCharacterController.class)
public class MarvelCharacterControllerTest {
    @MockBean
    MarvelCharacterService marvelCharacterService;

    @Autowired
    MockMvc mvc;

    @Test
    void testGetCharacters() throws Exception {

        List<Long> characterIds = Arrays.asList(1L, 2L, 3L);
        when(marvelCharacterService.getAllCharacters()).thenReturn(characterIds);

        RequestBuilder request = get("/characters");
        mvc.perform(request).andExpect(status().isOk()).andReturn();
    }

    @Test
    void testGetCharacterByIdWithCorrectId() throws Exception {

        CharacterResponse characterResponse = new CharacterResponse(1L, "Char Name", "A very cool character", new ThumbnailResponse("path", "jpg"));
        when(marvelCharacterService.getCharacterById(1L)).thenReturn(characterResponse);

        RequestBuilder request = get("/characters/1");
        mvc.perform(request).andExpect(status().isOk()).andReturn();
    }

    @Test
    void testGetCharacterByIdWithIncorrectId() throws Exception {

        when(marvelCharacterService.getCharacterById(1L)).thenThrow(CharacterNotFoundException.class);

        RequestBuilder request = get("/characters/1");
        mvc.perform(request).andExpect(status().isNotFound()).andReturn();
    }
}
