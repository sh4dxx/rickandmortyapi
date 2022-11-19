package com.rickandmorty.api.model;

import lombok.Data;

@Data
public class CharacterResponse {
    private int id;
    private String name;
    private String status;
    private String species;
    private String type;
    private int episode_count;
    private OriginResponse origin;
}
