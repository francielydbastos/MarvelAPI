package com.marvelapi.MarvelAPI.response;

import lombok.Data;

@Data
public class TranslatorResponse {
    private int status;
    private String message;
    private String translatedText;
}
