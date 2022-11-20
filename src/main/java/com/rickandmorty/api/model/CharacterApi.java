package com.rickandmorty.api.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private String created;
}
