package com.recipes.recipeController;



import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@PostMapping("/add-recipe")
	public String postAddRecipe(@Valid @ModelAttribute("request") Recipe recipe, BindingResult bindingResult, @RequestParam(value = "addRow", required = false) String addRowBtn) {
	    if (bindingResult.hasErrors()) {
	        return "add-recipe";
	    }
	    if (addRowBtn != null && addRowBtn.equals("true")) {
	        recipe.getIngredients().add("");
	    }
	    var createdRecipe = repository.save(recipe);
	    return "redirect:/recipes/" + createdRecipe.getId();
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
	
	@PutMapping("/edit-recipe/{id}")
	public String editRecipe(@PathVariable long id, @RequestBody Recipe recipe) {
		var updateRecipe = repository.findById(id).get();
		
		updateRecipe.setRecipeName(recipe.getRecipeName());
		updateRecipe.setCookingInstructions(recipe.getCookingInstructions());
		updateRecipe.setCuisine(recipe.getCuisine());
		updateRecipe.setIngredients(recipe.getIngredients());
		updateRecipe.setNumIngredients(recipe.getNumIngredients());
		
		repository.save(updateRecipe);
		
		return "recipe";
	}
	

    
	@PostMapping(value="/add-recipe", params="removeRowBtn")
	public String removeRow(
	        final Recipe recipe, final BindingResult bindingResult, 
	        final HttpServletRequest req) {
	    final Integer rowId = Integer.valueOf(req.getParameter("removeRowBtn"));
	    recipe.getIngredients().remove(rowId.intValue());
	    return "add-recipe";
	}
	
}
