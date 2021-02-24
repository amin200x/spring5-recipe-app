package guru.springframework.commands;

import guru.springframework.domain.Category;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Note;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;

    @NotBlank
    @Size(min = 3, max = 255)
    private String description;

    @Min(3)
    @Max(999)
    private Integer prepTime;

    @Min(3)
    @Max(999)
    private Integer cookTime;

    @Min(3)
    @Max(999)
    private Integer serving;
    private String source;

    @URL
    private String url;

    @NotBlank
    private String direction;
    private Difficulty difficulty;
    private Byte[] image;
    private NotesCommand notes;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Set<CategoryCommand> categories = new HashSet<>();


}
