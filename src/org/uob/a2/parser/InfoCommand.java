package org.uob.a2.parser;

import org.uob.a2.engine.*;
import org.uob.a2.model.ResourceType;
import org.uob.a2.model.BakeryShop;
import java.util.List;

public class InfoCommand extends Command {
    
    public InfoCommand(List<String> words) {
        super(words);
    }

    public InfoCommand() {
        super();
    }

    @Override
    public String execute(Context ctx) {
        if (words == null || words.isEmpty()) {
            return getResourcesInfo(ctx) + "\n\n" + getEntitiesInfo(ctx);
        }
        
        String subcommand = words.get(0).toLowerCase();
        if (subcommand.equals("resources") || subcommand.equals("r")) {
            return getResourcesInfo(ctx);
        } else if (subcommand.equals("entities") || subcommand.equals("e")) {
            return getEntitiesInfo(ctx);
        } else {
            return getResourcesInfo(ctx) + "\n\n" + getEntitiesInfo(ctx);
        }
    }

    private String getResourcesInfo(Context ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append("Resources:\n");
        for (ResourceType resource : ResourceType.values()) {
            int amount = ctx.state().getResourceAmount(resource);
            if (amount > 0) {
                sb.append("  ").append(resource).append(": ").append(amount).append("\n");
            }
        }
        return sb.toString().trim();
    }

    private String getEntitiesInfo(Context ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append("Entities:\n");
        
        sb.append("  Producers (").append(ctx.state().getProducers().size()).append("):\n");
        for (org.uob.a2.engine.Producer p : ctx.state().getProducers()) {
            sb.append("    - ").append(p.getName()).append(" (").append(p.getClass().getSimpleName())
              .append(") produces ").append(p.getAmount()).append(" ").append(p.getProduct()).append("\n");
        }
        
        sb.append("  Converters (").append(ctx.state().getConverters().size()).append("):\n");
        for (org.uob.a2.engine.Converter c : ctx.state().getConverters()) {
            sb.append("    - ").append(c.getName()).append(" (").append(c.getClass().getSimpleName())
              .append(") converts ").append(c.getInputAmount()).append(" ").append(c.getInput())
              .append(" -> ").append(c.getOutputAmount()).append(" ").append(c.getOutput()).append("\n");
        }
        
        sb.append("  Consumers (").append(ctx.state().getConsumers().size()).append("):\n");
        for (org.uob.a2.engine.Consumer c : ctx.state().getConsumers()) {
            String details = c.getName() + " (" + c.getClass().getSimpleName() + ") consumes " + 
                           c.getAmount() + " " + c.getProduct();
            if (c instanceof org.uob.a2.model.BakeryShop) {
                org.uob.a2.model.BakeryShop shop = (org.uob.a2.model.BakeryShop) c;
                details += " (Level: " + shop.getLevel() + ")";
            }
            sb.append("    - ").append(details).append("\n");
        }
        
        return sb.toString().trim();
    }
}

