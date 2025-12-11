package org.uob.a2.parser;

import org.uob.a2.engine.*;
import org.uob.a2.model.ResourceType;
import java.util.List;

public class CheatCommand extends Command {
    
    public CheatCommand(List<String> words) {
        super(words);
    }

    public CheatCommand() {
        super();
    }

    @Override
    public String execute(Context ctx) {
        // give us tons of resources
        for (ResourceType resource : ResourceType.values()) {
            ctx.state().addResource(resource, 10000);
        }
        return "Cheat activated! Added 10000 of each resource.";
    }
}

