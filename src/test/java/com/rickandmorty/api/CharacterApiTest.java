package com.rickandmorty.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rickandmorty.api.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CharacterApiTest {

    private MockMvc mockMvc;
    private HttpHeaders headers;

    private final String basePath = "https://rickandmortyapi.com/api/";
    private final ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @BeforeEach
    public void setup() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }


    @Test
    void integrationTest() throws Exception {
        int testStatus = 200;

        OriginApi apiOrigin = new OriginApi("Earth (C-137)","https://rickandmortyapi.com/api/location/1");
        LocationApi apiLocation = new LocationApi(1,"Citadel of Ricks","Planet","Dimension C-137",List.of("https://rickandmortyapi.com/api/character/38","https://rickandmortyapi.com/api/character/42"),"https://rickandmortyapi.com/api/location/1","2017-11-04T18:50:21.651Z");
        CharacterApi apiCharacter = new CharacterApi(1,"Rick Sanchez","Alive","Human","","Male",apiOrigin, apiLocation,"img",new ArrayList<>(),"url","2017-11-04T18:50:21.651Z");

        String urlApiCharacter = basePath +"character/1";
        ResponseEntity responseCharacterApi = new ResponseEntity(apiCharacter, HttpStatus.OK);
        when(restTemplate.getForObject(urlApiCharacter, CharacterApi.class)).thenReturn(apiCharacter);

        String urlLocationApi = basePath +"location/1";
        ResponseEntity responseLocationApi = new ResponseEntity<>(LocationApi.class, HttpStatus.OK);
        when(restTemplate.getForObject(urlLocationApi, LocationApi.class)).thenReturn(apiLocation);

        MvcResult mvcResult = this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/character/1")
                        .headers(headers))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        CharacterResponse characterResponse = mapper.readValue(mvcResult.getResponse().getContentAsString(), CharacterResponse.class);

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(testStatus);
    }
}
