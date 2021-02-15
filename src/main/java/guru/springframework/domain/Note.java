package guru.springframework.domain;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = "recipe")
@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Recipe recipe;
    @Lob // For above 250 char and array of bytes like images
    private String recipeNotes;

    public Note() {
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Note;
    }

}
