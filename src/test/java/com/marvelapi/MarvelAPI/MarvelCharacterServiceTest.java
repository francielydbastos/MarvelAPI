package com.marvelapi.MarvelAPI;

import com.marvelapi.MarvelAPI.client.MarvelClient;
import com.marvelapi.MarvelAPI.exception.CharacterNotFoundException;
import com.marvelapi.MarvelAPI.response.CharacterResponse;
import com.marvelapi.MarvelAPI.response.MarvelApiCharacterResponse;
import com.marvelapi.MarvelAPI.response.MarvelApiDataResponse;
import com.marvelapi.MarvelAPI.response.ThumbnailResponse;
import com.marvelapi.MarvelAPI.service.MarvelCharacterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MarvelCharacterServiceTest {
    @InjectMocks
    private MarvelCharacterService marvelCharacterService;
    @Mock
    MarvelClient marvelClient;

    @Test
    void testGetAllCharacters() throws ExecutionException, InterruptedException {

        MarvelApiCharacterResponse marvelApiCharacterResponse = new MarvelApiCharacterResponse(new MarvelApiDataResponse(1, 1, 1, Arrays.asList(new CharacterResponse(1L, "Char Name", "A very cool character", new ThumbnailResponse("path", "jpg")))));
        when(marvelClient.getCharacters(any(), any(), any(), anyInt(), anyInt())).thenReturn(marvelApiCharacterResponse);
        List<Long> response = marvelCharacterService.getAllCharacters();

        List<Long> expectedIds = marvelApiCharacterResponse.getData().getResults().stream().map(CharacterResponse::getId).collect(Collectors.toList());
        assertEquals(expectedIds, response);
    }

    @Test
    void testGetAllCharactersAsync() throws ExecutionException, InterruptedException {
        List<Long> expectedIds = Arrays.asList(1L, 2L, 3L);
        MarvelApiCharacterResponse marvelApiCharacterResponse = new MarvelApiCharacterResponse(
                new MarvelApiDataResponse(expectedIds.size(), 1, expectedIds.size(),
                        expectedIds.stream().map(id -> new CharacterResponse(id, "Char Name", "Description", new ThumbnailResponse("path", "jpg"))).toList()));

        when(marvelClient.getCharacters(any(), any(), any(), anyInt(), anyInt())).thenReturn(marvelApiCharacterResponse);

        CompletableFuture<List<Long>> futureResult = marvelCharacterService.getAllCharactersAsync();

        // Wait for the asynchronous operation to complete
        List<Long> result = futureResult.get();

        // Perform assertions based on the result
        assertEquals(expectedIds, result);
    }

    @Test
    void testGetCharacterByIdCorrectId() throws ExecutionException, InterruptedException {

        CharacterResponse expectedCharacterResponse = new CharacterResponse(1L, "Char Name", "A very cool character", new ThumbnailResponse("path", "jpg"));
        MarvelApiCharacterResponse marvelApiCharacterResponse = new MarvelApiCharacterResponse(new MarvelApiDataResponse(1, 1, 1, Arrays.asList(expectedCharacterResponse)));
        when(marvelClient.getCharacterById(any(), anyInt(), anyInt(), any(), any(), any())).thenReturn(marvelApiCharacterResponse);

        CharacterResponse response = marvelCharacterService.getCharacterById(1L);

        assertEquals(expectedCharacterResponse.getId(), response.getId());
        assertEquals(expectedCharacterResponse.getName(), response.getName());
    }

    @Test
    void testGetCharacterByIdIncorrectId() throws ExecutionException, InterruptedException {

        when(marvelClient.getCharacterById(any(), anyInt(), anyInt(), any(), any(), any())).thenThrow(CharacterNotFoundException.class);

        assertThrows(CharacterNotFoundException.class,() -> marvelCharacterService.getCharacterById(1L));
    }

}
