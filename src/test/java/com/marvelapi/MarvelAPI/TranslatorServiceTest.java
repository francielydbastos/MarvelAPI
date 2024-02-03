package com.marvelapi.MarvelAPI;

import com.marvelapi.MarvelAPI.client.TranslatorClient;
import com.marvelapi.MarvelAPI.exception.TranslatorException;
import com.marvelapi.MarvelAPI.response.*;
import com.marvelapi.MarvelAPI.service.TranslatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TranslatorServiceTest {
    @InjectMocks
    private TranslatorService translatorService;
    @Mock
    TranslatorClient translatorClient;

    @Test
    void testTranslateCorrectLanguageCode() throws Exception {
        TranslatorResponse translatorResponse = new TranslatorResponse(200, "OK", "naranjas");
        when(translatorClient.translateText("es", "oranges")).thenReturn(translatorResponse);

        String serviceResponse = translatorService.translate("es", "oranges");

        assertEquals(translatorResponse.getTranslatedText(), serviceResponse);
    }

    @Test
    void testTranslateIncorrectLanguageCode() throws Exception {

        TranslatorResponse translatorResponse = new TranslatorResponse(400, "Bad Request: The provided language code \"esp\" is not valid.", "");
        when(translatorClient.translateText("esp", "oranges")).thenReturn(translatorResponse);

        assertThrows(TranslatorException.class,() -> translatorService.translate("esp", "oranges"));
    }

}
