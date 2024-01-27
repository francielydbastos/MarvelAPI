package com.marvelapi.MarvelAPI.response;

import lombok.Data;

import java.util.List;

@Data
public class MarvelApiDataResponse {
    private int offset;
    private int total;
    private int count;
    private List<CharacterResponse> results;
}
