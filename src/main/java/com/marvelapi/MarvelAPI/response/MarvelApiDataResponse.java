package com.marvelapi.MarvelAPI.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MarvelApiDataResponse {
    private int offset;
    private int total;
    private int count;
    private List<CharacterResponse> results;
}
