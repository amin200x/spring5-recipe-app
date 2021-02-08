package guru.springframework.domain;

import javax.persistence.*;

@Entity
public class UnitOfMeasure {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
   // @OneToOne
   // private Ingredient ingredient;


}
