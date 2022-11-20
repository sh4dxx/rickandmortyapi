package com.rickandmorty.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rickandmorty.api.model.*;
import com.rickandmorty.api.service.GetCharacterResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class CharacterControllerTest {

    private MockMvc mockMvc;
    private final String contentTypeExpected = "application/json";
    private HttpHeaders headers;

    @Value("${spring.external.service.base.url}")
    private String basePath;
    private final ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private static final int STATUS_EXPECTED = 200;
    private static final String CONTENT_TYPE_EXPECTED = "application/json";
    private static final String URL_TEMPLATE = "/characters/{id}";

    @BeforeEach
    public void setup() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }


    @Test
    void should_return_status_ok_and_valid_response() throws Exception {
        final int statusExpected = 200;
        final int testId = 1;

        OriginApi apiOrigin = new OriginApi("Earth (C-137)","https://rickandmortyapi.com/api/location/1");
        LocationApi apiLocation = new LocationApi(1,"Citadel of Ricks","Planet","Dimension C-137",List.of("Profesor","habitante"),"URL TEST","2017-11-04T18:50:21.651Z");
        CharacterApi apiCharacter = new CharacterApi(1,"Rick Sanchez","Alive","Human","Electrico","Male",apiOrigin, apiLocation,"img",new ArrayList<>(),"url","2017-11-04T18:50:21.651Z");


//        String urlApiCharacter = basePath +"character/1";
        String urlApiCharacter = "https://www.some-location/character/1";
        ResponseEntity responseOfCharacterApiWithOk = new ResponseEntity(apiCharacter, HttpStatus.OK);
        when(restTemplate.getForObject(urlApiCharacter, CharacterApi.class)).thenReturn(apiCharacter);


        String urlLocationApi = "https://www.some-location/location/1";
        ResponseEntity responseOfLocationApiWithOk = new ResponseEntity<>(LocationApi.class, HttpStatus.OK);
        when(restTemplate.getForObject(urlLocationApi, LocationApi.class)).thenReturn(apiLocation);

        //WHEN
        MvcResult mvcResult = this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/character/".concat(String.valueOf(testId)))
                        .headers(headers))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        CharacterResponse characterResponse = mapper.readValue(mvcResult.getResponse().getContentAsString(), CharacterResponse.class);


        //THEN
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(statusExpected);
        assertThat(mvcResult.getResponse().getContentType()).isEqualTo(contentTypeExpected);
//        assertThat(characterResponse).isEqualTo(responseExpected);
    }






    //   @Test
//    public void characterResponseOk() throws Exception {
//
//        var apiOrigin = new OriginApi("Pickachu","test test");
//        var apiLocation = new LocationApi(1,"Pueblo paleta","Pueblo","200k",List.of("Profesor","habitante"),List.of("test"),null);
//        var apiCharacter = new CharacterApi(1,"Pikachu","Vivo","Pokemon","Electrico","Macho",apiOrigin,apiLocation,"img",new ArrayList<>(),"url",null);
//
//        mvc.perform(MockMvcRequestBuilders
//                .get("/api/character/1")
//                //.content(mapper.writeValueAsBytes(apiCharacter))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
}
