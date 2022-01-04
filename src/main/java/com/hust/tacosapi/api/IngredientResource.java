package com.hust.tacosapi.api;

import com.hust.tacosapi.entity.Ingredient;
import com.hust.tacosapi.entity.Ingredient.Type;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;


public class IngredientResource extends RepresentationModel<IngredientResource> {

    @Getter
    private String name;
    @Getter
    private Type type;

    public IngredientResource(Ingredient ingredient) {
        this.name = ingredient.getName();
        this.type = ingredient.getType();
    }

}
