package com.rickandmorty.api.mapper;

import com.rickandmorty.api.model.CharacterApi;
import com.rickandmorty.api.model.CharacterResponse;
import com.rickandmorty.api.model.LocationApi;
import com.rickandmorty.api.model.OriginResponse;
import org.springframework.stereotype.Component;

@Component
public class MappingResponse {

    public CharacterResponse converterCharacterRequest(CharacterApi characterApi, LocationApi locationApi){

        OriginResponse originResponse = new OriginResponse();
        originResponse.setName(characterApi.getOrigin().getName());
        originResponse.setUrl(characterApi.getOrigin().getUrl());
        originResponse.setDimension(locationApi.getDimension());
        originResponse.setResidents(locationApi.getResidents());

        CharacterResponse characterResponse = new CharacterResponse();
        characterResponse.setId(characterApi.getId());
        characterResponse.setName(characterApi.getName());
        characterResponse.setStatus(characterApi.getStatus());
        characterResponse.setSpecies(characterApi.getSpecies());
        characterResponse.setType(characterApi.getType());
        characterResponse.setEpisode_count(characterApi.getEpisode().size());
        characterResponse.setOrigin(originResponse);

        return characterResponse;
    }
}
