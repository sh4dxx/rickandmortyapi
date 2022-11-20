package com.rickandmorty.api.controller;

import com.rickandmorty.api.exception.ExceptionBadRequestHandler;
import com.rickandmorty.api.exception.ExceptionNotFountHandler;
import com.rickandmorty.api.service.GetApiServiceImp;
import com.rickandmorty.api.service.GetCharacterResponseImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;


@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/character")
public class CharacterController {

    private final GetCharacterResponseImp getCharacterResponseImp;

    @GetMapping("/{id}")
    public ResponseEntity<?> characterResponse(@PathVariable int id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(getCharacterResponseImp.getOneCharacterResponse(id));
        } catch (HttpClientErrorException.NotFound e) {
            throw new ExceptionNotFountHandler("Entidad no encontrada, " + e.getMessage());
        } catch (NumberFormatException e) {
            throw new ExceptionBadRequestHandler("Formato invalido " + e.getMessage());
        } catch (Exception e) {
            throw new ExceptionBadRequestHandler("Error al realizar peticion, " + e.getMessage());
        }
    }
}
