package com.recipes.recipeController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.recipes.model.Recipe;
import com.recipes.recipeRepository.RecipeRepository;

@Controller
public class RecipeController {
	@Autowired
	private RecipeRepository repository;
	
	@GetMapping("/add-recipe")
	public String getRecipeForm(Model model) {
		model.addAttribute("request", new Recipe());
		return "add-recipe";
	}
	
	//add PostMapping
	//add GetMapping("/records") for list of all recipes by name, cuisine
	//add GetMapping("/records/{id}") to return recipe by id
	
}
