package com.marvelapi.MarvelAPI.client;

import com.marvelapi.MarvelAPI.response.TranslatorResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="translator-client", url="https://script.google.com/macros/s/AKfycbx8uVW9KD1YcGdLtv33r7jxbj5x3NcUMHprp7GQk4u91U-6OAbgM9w51KRb-9wyvvoGNQ/exec")
public interface TranslatorClient {

    @GetMapping(value = "", produces = {"application/json"})
    TranslatorResponse translateText(@RequestParam("targetLanguage") String targetLanguage, @RequestParam("text") String text);

}
