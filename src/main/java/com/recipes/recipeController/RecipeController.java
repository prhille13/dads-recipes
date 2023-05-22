package com.recipes.recipeController;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
	
	@PostMapping("/recipes")
	public String postRecipe(@Valid @ModelAttribute("request") Recipe recipe, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "add-recipe";
		}
		var createdRecipe = repository.save(recipe);
		return "redirect:/recipes/" + createdRecipe.id;
	}
	
	//add GetMapping("/records") for list of all recipes by name, cuisine
	@GetMapping("/recipes")
	public String getRecipes(Model model) {
		var recipes = repository.findAll();
		model.addAttribute("recipes", recipes);
		return "recipes";
	}
	
	@GetMapping("/recipes/{id}")
	public String getRecipe(@PathVariable long id, Model model) {
		var recipe = repository.findById(id).get();
		
		model.addAttribute("recipeName", recipe.recipeName);
		model.addAttribute("cuisine", recipe.cuisine);
		model.addAttribute("ingredients", recipe.ingredients);
		model.addAttribute("cookingInstructions", recipe.cookingInstructions);
		
		return "recipe";
	}
	
}
