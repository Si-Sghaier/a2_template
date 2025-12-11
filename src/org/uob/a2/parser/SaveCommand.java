package org.uob.a2.parser;

import org.uob.a2.engine.*;
import java.util.List;

public class SaveCommand extends Command {
    
    public SaveCommand(List<String> words) {
        super(words);
    }

    public SaveCommand() {
        super();
    }

    @Override
    public String execute(Context ctx) {
        if (words == null || words.isEmpty()) {
            return "Error: Please specify a filename (e.g., 'save mysave.csv')";
        }

        String filename = words.get(0);
        return ctx.engine().save(filename);
    }
}

