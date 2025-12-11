package org.uob.a2.model;

import org.uob.a2.engine.*;
import org.uob.a2.model.ResourceType;

public class Oven extends Converter {
    // Oven requires multiple inputs, so we override the base Converter behavior
    private static final int FLOUR_NEEDED = 5;
    private static final int SUGAR_NEEDED = 5;
    private static final int EGGS_NEEDED = 2;
    private static final int MILK_NEEDED = 5;
    private static final int CAKE_PRODUCED = 1;
    
    public Oven(String name) {
        // Use FLOUR as the primary input for base class compatibility
        super(name, ResourceType.FLOUR, FLOUR_NEEDED, ResourceType.CAKE, CAKE_PRODUCED);
        addCost(ResourceType.CREDITS, 300);
    }

    @Override
    public void convert(Context ctx) {
        // Check if we have all required ingredients
        if (ctx.state().getResourceAmount(ResourceType.FLOUR) >= FLOUR_NEEDED &&
            ctx.state().getResourceAmount(ResourceType.SUGAR) >= SUGAR_NEEDED &&
            ctx.state().getResourceAmount(ResourceType.EGGS) >= EGGS_NEEDED &&
            ctx.state().getResourceAmount(ResourceType.MILK) >= MILK_NEEDED) {
            
            // Remove all ingredients
            ctx.state().removeResource(ResourceType.FLOUR, FLOUR_NEEDED);
            ctx.state().removeResource(ResourceType.SUGAR, SUGAR_NEEDED);
            ctx.state().removeResource(ResourceType.EGGS, EGGS_NEEDED);
            ctx.state().removeResource(ResourceType.MILK, MILK_NEEDED);
            
            // Add cake
            ctx.state().addResource(ResourceType.CAKE, CAKE_PRODUCED);
        }
    }

    @Override
    public String toCSV() {
        return getName() + ",FLOUR," + FLOUR_NEEDED + ",SUGAR," + SUGAR_NEEDED + 
               ",EGGS," + EGGS_NEEDED + ",MILK," + MILK_NEEDED + ",CAKE," + CAKE_PRODUCED;
    }
}

