package com.rickandmorty.api.service;

import com.rickandmorty.api.model.CharacterResponse;

public interface GetCharacterResponse {
    CharacterResponse getOneCharacterResponse(int id);
}
