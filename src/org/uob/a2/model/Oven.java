package org.uob.a2.model;

import org.uob.a2.engine.*;
import org.uob.a2.model.ResourceType;

public class Oven extends Converter {
    // oven needs multiple ingredients so we gotta override the base class
    private static final int FLOUR_NEEDED = 5;
    private static final int SUGAR_NEEDED = 5;
    private static final int EGGS_NEEDED = 2;
    private static final int MILK_NEEDED = 5;
    private static final int CAKE_PRODUCED = 1;
    
    public Oven(String name) {
        // using FLOUR as primary input cause the base class needs it
        super(name, ResourceType.FLOUR, FLOUR_NEEDED, ResourceType.CAKE, CAKE_PRODUCED);
        addCost(ResourceType.CREDITS, 300);
    }

    @Override
    public void convert(Context ctx) {
        // check if we got all the ingredients
        if (ctx.state().getResourceAmount(ResourceType.FLOUR) >= FLOUR_NEEDED &&
            ctx.state().getResourceAmount(ResourceType.SUGAR) >= SUGAR_NEEDED &&
            ctx.state().getResourceAmount(ResourceType.EGGS) >= EGGS_NEEDED &&
            ctx.state().getResourceAmount(ResourceType.MILK) >= MILK_NEEDED) {
            
            ctx.state().removeResource(ResourceType.FLOUR, FLOUR_NEEDED);
            ctx.state().removeResource(ResourceType.SUGAR, SUGAR_NEEDED);
            ctx.state().removeResource(ResourceType.EGGS, EGGS_NEEDED);
            ctx.state().removeResource(ResourceType.MILK, MILK_NEEDED);
            
            ctx.state().addResource(ResourceType.CAKE, CAKE_PRODUCED);
        }
    }

    @Override
    public String toCSV() {
        return getName() + ",FLOUR," + FLOUR_NEEDED + ",SUGAR," + SUGAR_NEEDED + 
               ",EGGS," + EGGS_NEEDED + ",MILK," + MILK_NEEDED + ",CAKE," + CAKE_PRODUCED;
    }
}

