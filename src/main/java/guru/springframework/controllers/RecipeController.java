package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.services.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/show/{id}")
    public String showById(@PathVariable("id") String id, Model model){
         model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));

         return "recipe/show";

    }

    @RequestMapping("/recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    //@PostMapping
    @RequestMapping(value = "recipe", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute RecipeCommand command){
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/show/" + savedRecipeCommand.getId();
    }

    @RequestMapping("/recipe/{id}/update")
    public String update(@PathVariable("id") String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));

        return "recipe/recipeform";

    }
    @RequestMapping("/recipe/{id}/delete")
    public String update(@PathVariable("id") String id){
            recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";


    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(){
        ModelAndView modelAndView = new ModelAndView("recipe/404error");
        return modelAndView;

    }
}
