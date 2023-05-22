package com.recipes.recipeRepository;

import org.springframework.data.repository.CrudRepository;

import com.recipes.model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
