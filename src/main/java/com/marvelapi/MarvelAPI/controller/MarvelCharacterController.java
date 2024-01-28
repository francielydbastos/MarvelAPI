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
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/")
public class MarvelCharacterController {
    @Autowired
    MarvelCharacterService marvelCharacterService;

    @GetMapping("/characters")
    public ResponseEntity<List<Long>> getCharacters() throws ExecutionException, InterruptedException {
        List<Long> characterIds = marvelCharacterService.getAllCharacters();
        return ResponseEntity.status(HttpStatus.OK).body(characterIds);
    }

    @GetMapping("/characters/{characterId}")
    public ResponseEntity<CharacterResponse> getCharacterById(@PathVariable Long characterId) throws ExecutionException, InterruptedException {
        Optional<CharacterResponse> characterOpt = marvelCharacterService.getCharacterById(characterId);
        CharacterResponse character = characterOpt.get();
        return ResponseEntity.status(HttpStatus.OK).body(character);
    }

}
