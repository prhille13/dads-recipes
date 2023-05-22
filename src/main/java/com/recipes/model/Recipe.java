package com.recipes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
public class Recipe {
    public Recipe(){}
    
    public Recipe(long id, String recipeName, String cuisine, int numIngredients, String ingredients, String cookingInstructions){
        this.id = id;
        this.recipeName = recipeName;
        this.cuisine = cuisine;
        this.ingredients = ingredients;
        this.cookingInstructions = cookingInstructions;
        }
   
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long id;
    @NotEmpty(message="Recipe name cannot be empty.")
    public String recipeName;
    @NotEmpty(message="Cuisine cannot be empty.")
    public String cuisine;
    @Min(value=1)
    public int numIngredients;
    @NotEmpty(message="Ingredients cannot be empty.")
    public String ingredients;
    @NotEmpty(message="Cooking instructions cannot be empty.")
    public String cookingInstructions;
	
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	public int getNumIngredients() {
		return numIngredients;
	}
	
	public void setNumIngredients(int numIngredients) {
		this.numIngredients = numIngredients;
	}
	
	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getCookingInstructions() {
		return cookingInstructions;
	}

	public void setCookingInstructions(String cookingInstructions) {
		this.cookingInstructions = cookingInstructions;
	}
}
