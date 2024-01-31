package com.marvelapi.MarvelAPI.controller;

import com.marvelapi.MarvelAPI.response.CharacterResponse;
import com.marvelapi.MarvelAPI.service.MarvelCharacterService;
import com.marvelapi.MarvelAPI.service.TranslatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/characters")
public class MarvelCharacterController {
    @Autowired
    MarvelCharacterService marvelCharacterService;
    @Autowired
    TranslatorService translatorService;

    @GetMapping("")
    public ResponseEntity<List<Long>> getCharacters() throws ExecutionException, InterruptedException {
        List<Long> characterIds = marvelCharacterService.getAllCharacters();
        return ResponseEntity.status(HttpStatus.OK).body(characterIds);
    }

//    @GetMapping("/{characterId}")
//    public ResponseEntity<CharacterResponse> getCharacterById(@PathVariable Long characterId) {
//        CharacterResponse character = marvelCharacterService.getCharacterById(characterId);
//        return ResponseEntity.status(HttpStatus.OK).body(character);
//    }

    @GetMapping("/{characterId}")
    public ResponseEntity<CharacterResponse> getCharacterById(@PathVariable Long characterId, @RequestParam(name = "language", required = false, defaultValue = "") String language) {
        CharacterResponse character = marvelCharacterService.getCharacterById(characterId);
        if(language != null && !language.isEmpty()) {
            String translatedDescription = translatorService.translate(language, character.getDescription());

            character.setDescription(translatedDescription);
        }
        return ResponseEntity.status(HttpStatus.OK).body(character);
    }

}
