package com.marvelapi.MarvelAPI.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CharacterResponse {
    private long id;
    private String name;
    private String description;
    private ThumbnailResponse thumbnail;
}
