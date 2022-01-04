package com.hust.tacosapi.api;

import com.hust.tacosapi.entity.Ingredient;
import com.hust.tacosapi.entity.Taco;
import lombok.Getter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.Date;
import java.util.List;

@Relation(value = "taco", collectionRelation = "tacos")
public class TacoResource extends RepresentationModel<TacoResource> {

    private static final IngredientResourceAssembler ingredientAssembler
            = new IngredientResourceAssembler();

    @Getter
    private String name;

    @Getter
    private Date createdAt;

    @Getter
    private CollectionModel<IngredientResource> ingredients;

    public TacoResource(Taco taco){
        this.name = taco.getName();
        this.createdAt = taco.getCreatedAt();
        this.ingredients = ingredientAssembler.toCollectionModel(taco.getIngredients());
    }
}
