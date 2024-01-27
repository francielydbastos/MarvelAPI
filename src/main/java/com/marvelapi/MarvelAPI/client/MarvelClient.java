package com.marvelapi.MarvelAPI.client;

import com.marvelapi.MarvelAPI.response.MarvelApiCharacterResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="marvel-client", url="http://gateway.marvel.com/v1/public")
public interface MarvelClient {

    @GetMapping(value = "/characters")
    MarvelApiCharacterResponse getCharacters(@RequestParam("ts") String ts, @RequestParam("apikey") String apikey, @RequestParam("hash") String hash, @RequestParam("limit") int limit, @RequestParam("offset") int offset);

    @GetMapping(value = "/characters/{characterId}")
    MarvelApiCharacterResponse getCharacterById(@PathVariable("characterId") String characterId, @RequestParam("limit") int limit, @RequestParam("offset") int offset, @RequestParam("ts") String ts, @RequestParam("apikey") String apikey, @RequestParam("hash") String hash);

}
