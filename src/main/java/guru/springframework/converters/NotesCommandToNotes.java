package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Note;
import lombok.Synchronized;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Note> {

    @Synchronized
    @Override
    public Note convert(NotesCommand source) {
        if(source == null) {
            return null;
        }

        final Note notes = new Note();
        notes.setId(source.getId());
        notes.setRecipeNotes(source.getRecipeNotes());
        return notes;
    }
}