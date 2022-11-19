package com.rickandmorty.api.service;

import com.rickandmorty.api.exception.mapper.MappingResponse;
import com.rickandmorty.api.model.CharacterApi;
import com.rickandmorty.api.model.CharacterResponse;
import com.rickandmorty.api.model.LocationApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetApiServiceImp implements GetApiService, GetCharacterResponse {

    @Value("${spring.external.service.base.url}")
    private String basePath;

    private final RestTemplate restTemplate;

    private MappingResponse mappingResponse;

    public GetApiServiceImp(RestTemplate restTemplate, MappingResponse mappingResponse) {
        this.restTemplate = restTemplate;
        this.mappingResponse = mappingResponse;
    }

    @Override
    public CharacterApi getCharacterApi(int id) {
        String characterUrl = basePath+"character/"+ id;
        return restTemplate.getForObject(characterUrl, CharacterApi.class);
    }

    @Override
    public LocationApi getLocationApi(int id) {
        String locationUrl = basePath+"location/"+ id;
        return restTemplate.getForObject(locationUrl, LocationApi.class);
    }

    @Override
    public CharacterResponse getOneCharacterResponse(int id) {
        CharacterApi characterApi = getCharacterApi(id);
        LocationApi locationApi = getLocationApi(id);

        CharacterResponse characterResponse = mappingResponse.converterCharacterRequest(characterApi,locationApi);
        return characterResponse;
    }


}
