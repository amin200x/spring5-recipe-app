package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Ingredient;

public interface IngredientService {
    Ingredient findById(Long id);
    IngredientCommand saveIngredientCommand(IngredientCommand command);
    void deleteById(Long id);
    IngredientCommand findByRecipeIdAndId(Long recipeId, Long id);
    void deleteById(Long recipeId, Long ingredientId);
}
