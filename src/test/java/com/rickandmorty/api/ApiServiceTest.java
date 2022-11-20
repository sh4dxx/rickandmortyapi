package com.rickandmorty.api;

import com.rickandmorty.api.model.CharacterApi;
import com.rickandmorty.api.model.LocationApi;
import com.rickandmorty.api.model.OriginApi;
import com.rickandmorty.api.service.GetApiServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApiServiceTest {

    private final String basePath = "https://rickandmortyapi.com/api/";

    private GetApiServiceImp getApiServiceImp;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void init() {
        getApiServiceImp = new GetApiServiceImp(basePath, restTemplate);
    }

    @Test
    void testCharapterApi() {
        int count = 1;
        int characterId = 1;

        CharacterApi apiCharacter = getCharacterApiEntity();

        when(restTemplate.getForObject(basePath+"character/1", CharacterApi.class)).thenReturn(apiCharacter);
        CharacterApi characterApiResponse = getApiServiceImp.getCharacterApi(characterId);
        verify(restTemplate, times(count)).getForObject(basePath+"character/1", CharacterApi.class);

        assertThat(characterApiResponse.getName()).isEqualTo("Rick Sanchez");
        assertThat(characterApiResponse.getOrigin().getName()).isEqualTo("Earth (C-137)");
    }

    @Test
    void testLocationApi() {
        int count = 1;
        int locationId = 1;

        LocationApi apiLocation = getLocationApiEntity();
        when(restTemplate.getForObject(basePath+"location/"+locationId, LocationApi.class)).thenReturn(apiLocation);
        LocationApi characterApiResponse = getApiServiceImp.getLocationApi(locationId);
        verify(restTemplate, times(count)).getForObject(basePath+"location/"+locationId, LocationApi.class);
        assertThat(characterApiResponse.getName()).isEqualTo("Citadel of Ricks");

    }

    public CharacterApi getCharacterApiEntity(){
        OriginApi apiOrigin = new OriginApi("Earth (C-137)","https://rickandmortyapi.com/api/location/1");
        LocationApi apiLocation = new LocationApi(1,"Citadel of Ricks","Planet","Dimension C-137", List.of("https://rickandmortyapi.com/api/character/38","https://rickandmortyapi.com/api/character/42"),"https://rickandmortyapi.com/api/location/1","2017-11-04T18:50:21.651Z");
        CharacterApi apiCharacter = new CharacterApi(1,"Rick Sanchez","Alive","Human","","Male",apiOrigin, apiLocation,"img",new ArrayList<>(),"url","2017-11-04T18:50:21.651Z");
        return apiCharacter;
    }

    public LocationApi getLocationApiEntity(){
        LocationApi apiLocation = new LocationApi(1,"Citadel of Ricks","Planet","Dimension C-137", List.of("https://rickandmortyapi.com/api/character/38","https://rickandmortyapi.com/api/character/42"),"https://rickandmortyapi.com/api/location/1","2017-11-04T18:50:21.651Z");
        return apiLocation;
    }
}
