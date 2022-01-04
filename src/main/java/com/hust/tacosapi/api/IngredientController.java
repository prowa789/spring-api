package com.hust.tacosapi.api;

import com.hust.tacosapi.data.IngredientRepository;
import com.hust.tacosapi.entity.Ingredient;
import com.hust.tacosapi.entity.Taco;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/ingredientsx", produces = "application/json")
public class IngredientController {

    private IngredientRepository ingredientRepo;

    public IngredientController(IngredientRepository ingredientRepo){
        this.ingredientRepo = ingredientRepo;
    }

    @GetMapping
    public Iterable<Ingredient> allIngredient(){
        return ingredientRepo.findAll();
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<Ingredient> ingredientById(@PathVariable String id) {
        Optional<Ingredient> optIngredient = ingredientRepo.findById(id);
        if (optIngredient.isPresent()) {
            return new ResponseEntity<>(optIngredient.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
