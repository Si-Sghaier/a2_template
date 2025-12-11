package org.uob.a2.parser;

import org.uob.a2.engine.*;
import java.util.List;

public class LoadCommand extends Command {
    
    public LoadCommand(List<String> words) {
        super(words);
    }

    public LoadCommand() {
        super();
    }

    @Override
    public String execute(Context ctx) {
        if (words == null || words.isEmpty()) {
            return "Error: Please specify a filename (e.g., 'load mysave.csv')";
        }

        String filename = words.get(0);
        return ctx.engine().load(filename);
    }
}

