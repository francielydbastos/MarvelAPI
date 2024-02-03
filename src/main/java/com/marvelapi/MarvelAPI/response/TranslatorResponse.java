package com.marvelapi.MarvelAPI.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TranslatorResponse {
    private int status;
    private String message;
    private String translatedText;
}
