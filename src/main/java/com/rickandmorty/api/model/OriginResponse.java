package com.rickandmorty.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OriginResponse {

    private String name;
    private String url;
    private String dimension;
    private List<String> residents;
}
