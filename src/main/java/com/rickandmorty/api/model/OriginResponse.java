package com.rickandmorty.api.model;

import lombok.Data;

import java.util.List;

@Data
public class OriginResponse {

    private String name;
    private String url;
    private String dimension;
    private List<String> residents;
}
