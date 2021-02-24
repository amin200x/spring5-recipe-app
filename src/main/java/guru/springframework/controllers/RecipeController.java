package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
public class RecipeController {
    public static final String RECIPE_RECIPEFORM = "recipe/recipeform";
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

        return RECIPE_RECIPEFORM;
    }

    @PostMapping("recipe")
   // @RequestMapping(value = "recipe", method = RequestMethod.POST)
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand command, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return RECIPE_RECIPEFORM;
        }

        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/show/" + savedRecipeCommand.getId();
    }

    @RequestMapping("/recipe/{id}/update")
    public String update(@PathVariable("id") String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));

        return RECIPE_RECIPEFORM;

    }
    @RequestMapping("/recipe/{id}/delete")
    public String update(@PathVariable("id") String id){
            recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";


    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception ex){
        ModelAndView modelAndView = new ModelAndView("recipe/404error");
        modelAndView.addObject("exception", ex);
        return modelAndView;

    }

   /* @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormatException(Exception ex){
        ModelAndView modelAndView = new ModelAndView("recipe/400error");
        modelAndView.addObject("exception", ex);
        return modelAndView;

    }  */
}
