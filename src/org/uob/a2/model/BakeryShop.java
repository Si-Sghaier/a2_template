package org.uob.a2.model;

import org.uob.a2.engine.*;
import org.uob.a2.model.ResourceType;

public class BakeryShop extends Consumer {
    private int level = 1;
    
    public BakeryShop(String name) {
        super(name, ResourceType.CAKE, 10);
        addCost(ResourceType.CREDITS, 500);
    }

    @Override
    public void consume(Context ctx) {
        int required = getAmount() * level;
        if (ctx.state().removeResource(consumedResource, required)) {
            level++;
        }
    }

    /**
     * Sell cakes and earn credits based on the shop's level.
     * @param ctx The simulation context
     * @return A message describing the sale
     */
    public String sell(Context ctx) {
        if (level <= 1) {
            return "BakeryShop " + getName() + " has no cakes to sell. Level: " + level;
        }
        
        int creditsEarned = (level - 1) * 100;
        ctx.state().addResource(ResourceType.CREDITS, creditsEarned);
        int oldLevel = level;
        level = 1; // Reset level after selling
        
        return "BakeryShop " + getName() + " sold cakes! Earned " + creditsEarned + 
               " CREDITS (was level " + oldLevel + ", now level " + level + ")";
    }

    /**
     * Get the current level of the bakery shop.
     * @return The current level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Set the level of the bakery shop (used for loading saved games).
     * @param level The level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toCSV() {
        return getName() + "," + consumedResource + "," + getAmount() + "," + level;
    }
}

