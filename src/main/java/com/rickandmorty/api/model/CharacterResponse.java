package com.rickandmorty.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterResponse {
    private int id;
    private String name;
    private String status;
    private String species;
    private String type;
    private int episode_count;
    private OriginResponse origin;
}
