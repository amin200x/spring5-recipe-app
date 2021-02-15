package guru.springframework.repositores;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UnitOfMeasureRepositoryIT {
    @Autowired
    UnitOfMeasureRepository measureRepository;

    @Test
    void findByDescription() {
        Optional<UnitOfMeasure> unoOptional = measureRepository.findByDescription("Teaspoon");
        assertEquals("Teaspoon", unoOptional.get().getDescription());
    }

    @Test
    void findByDescriptionCup() {
        Optional<UnitOfMeasure> unoOptional = measureRepository.findByDescription("Cup");
        assertEquals("Cup", unoOptional.get().getDescription());
    }
}