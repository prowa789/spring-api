package com.hust.tacosapi.data;

import com.hust.tacosapi.entity.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient , String>{
}
