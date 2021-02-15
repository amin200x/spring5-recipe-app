package guru.springframework.controllers;

import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositores.CategoryRepository;
import guru.springframework.repositores.UnitOfMeasureRepository;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//@Slf4j
@Controller
public class IndexController {
   private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getIndexPage(Model model) {
        //log.debug("Getting Index Page");
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}
