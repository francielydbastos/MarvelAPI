package com.marvelapi.MarvelAPI.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ThumbnailResponse {
    private String path;
    private String extension;
}
