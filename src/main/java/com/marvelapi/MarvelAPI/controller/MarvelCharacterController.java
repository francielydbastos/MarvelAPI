package com.marvelapi.MarvelAPI.controller;

import com.marvelapi.MarvelAPI.response.CharacterResponse;
import com.marvelapi.MarvelAPI.service.MarvelCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/characters")
public class MarvelCharacterController {
    @Autowired
    MarvelCharacterService marvelCharacterService;

    @GetMapping("")
    public ResponseEntity<List<Long>> getCharacters() throws ExecutionException, InterruptedException {
        List<Long> characterIds = marvelCharacterService.getAllCharacters();
        return ResponseEntity.status(HttpStatus.OK).body(characterIds);
    }

    @GetMapping("/{characterId}")
    public ResponseEntity<CharacterResponse> getCharacterById(@PathVariable Long characterId) {
        CharacterResponse character = marvelCharacterService.getCharacterById(characterId);
        return ResponseEntity.status(HttpStatus.OK).body(character);
    }

}
