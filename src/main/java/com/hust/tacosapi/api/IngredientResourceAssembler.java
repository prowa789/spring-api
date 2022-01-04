package com.hust.tacosapi.api;

import com.hust.tacosapi.entity.Ingredient;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class IngredientResourceAssembler extends RepresentationModelAssemblerSupport<Ingredient,IngredientResource> {
    public IngredientResourceAssembler() {
        super(IngredientController.class, IngredientResource.class);
    }

    @Override
    public IngredientResource toModel(Ingredient ingredient) {
        return createModelWithId(ingredient.getId(),ingredient);
    }

    @Override
    protected IngredientResource instantiateModel(Ingredient ingredient) {
        return new IngredientResource(ingredient);
    }
}
