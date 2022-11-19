package com.rickandmorty.api.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class LocationApi {

    private int id;
    private String name;
    private String type;
    private String dimension;
    private List<String> residents;
    private List<String> urls;
    private LocalDateTime created;
}
