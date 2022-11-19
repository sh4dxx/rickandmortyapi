package com.rickandmorty.api.model;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CharacterApi {

    private int id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private OriginApi origin;
    private LocationApi location;
    private String image;
    private List<String> episode;
    private String url;
    private LocalDateTime created;
}
