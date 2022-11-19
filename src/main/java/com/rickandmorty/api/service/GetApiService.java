package com.rickandmorty.api.service;

import com.rickandmorty.api.model.CharacterApi;
import com.rickandmorty.api.model.LocationApi;

public interface GetApiService {

    CharacterApi getCharacterApi(int id);
    LocationApi getLocationApi(int id);
}
