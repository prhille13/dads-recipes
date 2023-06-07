package com.recipes.model;

import java.util.ArrayList;

public class RecipeRequest {
    public long id;
    public String recipeName;
    public String cuisine;
    public int numIngredients;
    public ArrayList<String> ingredients;    
    public String cookingInstructions;
}
