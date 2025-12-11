package org.uob.a2.parser;

import org.uob.a2.engine.*;
import org.uob.a2.model.*;
import java.util.List;
import java.util.Map;

public class BuildCommand extends Command {
    
    public BuildCommand(List<String> words) {
        super(words);
    }

    public BuildCommand() {
        super();
    }

    @Override
    public String execute(Context ctx) {
        if (words == null || words.isEmpty()) {
            return "Error: Please specify an entity type to build (e.g., 'build wheatfarm')";
        }

        String entityType = words.get(0).toLowerCase();
        
        org.uob.a2.engine.Entity newEntity = createEntity(entityType, ctx);
        
        if (newEntity == null) {
            return "Error: Unknown entity type '" + entityType + "'. Available types: " +
                   "wheatfarm, sugarfarm, chickencoop, dairyfarm, flourmill, oven, bakeryshop";
        }

        Map<ResourceType, Integer> costs = newEntity.getCosts();
        for (Map.Entry<ResourceType, Integer> cost : costs.entrySet()) {
            if (ctx.state().getResourceAmount(cost.getKey()) < cost.getValue()) {
                return "Error: Insufficient resources to build " + entityType + 
                       ". Need " + cost.getValue() + " " + cost.getKey();
            }
        }

        for (Map.Entry<ResourceType, Integer> cost : costs.entrySet()) {
            ctx.state().removeResource(cost.getKey(), cost.getValue());
        }

        if (newEntity instanceof Producer) {
            ctx.state().getProducers().add((Producer) newEntity);
        } else if (newEntity instanceof Converter) {
            ctx.state().getConverters().add((Converter) newEntity);
        } else if (newEntity instanceof Consumer) {
            ctx.state().getConsumers().add((Consumer) newEntity);
        }

        return "Built " + newEntity.getName() + " (" + newEntity.getClass().getSimpleName() + ")";
    }

    private org.uob.a2.engine.Entity createEntity(String entityType, Context ctx) {
        String baseName = entityType;
        int counter = 1;
        String name = baseName + counter;
        
        while (entityExists(name, ctx)) {
            counter++;
            name = baseName + counter;
        }

        switch (entityType) {
            case "wheatfarm":
                return new WheatFarm(name);
            case "sugarfarm":
                return new SugarFarm(name);
            case "chickencoop":
                return new ChickenCoop(name);
            case "dairyfarm":
                return new DairyFarm(name);
            case "flourmill":
                return new FlourMill(name);
            case "oven":
                return new Oven(name);
            case "bakeryshop":
                return new BakeryShop(name);
            default:
                return null;
        }
    }

    private boolean entityExists(String name, Context ctx) {
        for (Producer p : ctx.state().getProducers()) {
            if (p.getName().equals(name)) return true;
        }
        for (Converter c : ctx.state().getConverters()) {
            if (c.getName().equals(name)) return true;
        }
        for (Consumer c : ctx.state().getConsumers()) {
            if (c.getName().equals(name)) return true;
        }
        return false;
    }
}

