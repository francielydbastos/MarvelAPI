package com.marvelapi.MarvelAPI.response;

import lombok.Data;

@Data
public class CharacterResponse {
    private long id;
    private String name;
    private String description;
    private ThumbnailResponse thumbnail;
}
