package com.rickandmorty.api.service;

import com.rickandmorty.api.mapper.MappingResponse;
import com.rickandmorty.api.model.CharacterApi;
import com.rickandmorty.api.model.CharacterResponse;
import com.rickandmorty.api.model.LocationApi;
import org.springframework.stereotype.Service;

@Service
public class GetCharacterResponseImp implements GetCharacterResponse {
    private final GetApiServiceImp getApiServiceImp;
    private final MappingResponse mappingResponse;

    public GetCharacterResponseImp(GetApiServiceImp getApiServiceImp, MappingResponse mappingResponse) {
        this.getApiServiceImp = getApiServiceImp;
        this.mappingResponse = mappingResponse;
    }

    @Override
    public CharacterResponse getOneCharacterResponse(int id) {
        CharacterApi characterApi = getApiServiceImp.getCharacterApi(id);
        LocationApi locationApi = getApiServiceImp.getLocationApi(id);

        CharacterResponse characterResponse = mappingResponse.converterCharacterRequest(characterApi,locationApi);
        //System.out.println("---GetApiServiceImp:getOneCharacterResponse  --- CharacterResponse:" + characterResponse.toString());
        return characterResponse;
    }
}
