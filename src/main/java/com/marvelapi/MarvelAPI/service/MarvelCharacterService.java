package com.marvelapi.MarvelAPI.service;

import com.marvelapi.MarvelAPI.MarvelApiApplication;
import com.marvelapi.MarvelAPI.client.MarvelClient;
import com.marvelapi.MarvelAPI.response.MarvelApiCharacterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class MarvelCharacterService {
    @Value("${PUBLIC_API_KEY}")
    private String PUBLIC_API_KEY;
    @Value("${PRIVATE_API_KEY}")
    private String PRIVATE_API_KEY;

    @Autowired
    private MarvelClient marvelClient;

    @Async
    public CompletableFuture<List<Long>> getAllCharactersAsync() {
        int limit = 100;
        int offset = 0;
        List<Long> allCharacterIds = new ArrayList<>();

        String ts = String.valueOf(Instant.now().toEpochMilli());
        String keys = ts + PRIVATE_API_KEY + PUBLIC_API_KEY;
        String hash = DigestUtils.md5DigestAsHex(keys.getBytes());

        int total;

        do {
            MarvelApiCharacterResponse marvelApiCharacterResponse = marvelClient.getCharacters(ts, PUBLIC_API_KEY, hash, limit, offset);
            allCharacterIds.addAll(marvelApiCharacterResponse.getData().getResults().stream().map(character -> character.getId()).toList());

            offset += limit;
            total = marvelApiCharacterResponse.getData().getTotal();
        } while (offset < total);

        return CompletableFuture.completedFuture(allCharacterIds);
    }

    @EventListener(ApplicationReadyEvent.class)
    @Cacheable("allCharacters")
    public List<Long> getAllCharacters() throws ExecutionException, InterruptedException {
        CompletableFuture<List<Long>> futureResult = getAllCharactersAsync();
        return futureResult.get();
    }
}
