package com.marvelapi.MarvelAPI.service;

import com.marvelapi.MarvelAPI.client.TranslatorClient;
import com.marvelapi.MarvelAPI.exception.TranslatorException;
import com.marvelapi.MarvelAPI.response.TranslatorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TranslatorService {
    @Autowired
    private TranslatorClient translatorClient;

    @Cacheable("translation")
    public String translate(String targetLanguage, String text) {

        try {
            TranslatorResponse translatorResponse = translatorClient.translateText(targetLanguage, text);

            if (translatorResponse.getStatus() == 400) {
                throw new TranslatorException(translatorResponse.getMessage());
            }

            return translatorResponse.getTranslatedText();
        } catch (Exception e) {
            throw e;
        }
    }

}
