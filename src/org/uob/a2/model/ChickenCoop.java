package org.uob.a2.model;

import org.uob.a2.engine.*;
import org.uob.a2.model.ResourceType;

public class ChickenCoop extends Producer {
    
    public ChickenCoop(String name) {
        super(name, ResourceType.EGGS, 5);
        addCost(ResourceType.CREDITS, 150);
    }

    @Override
    public void produce(Context ctx) {
        ctx.state().addResource(ResourceType.EGGS, getAmount());
    }

    @Override
    public String toCSV() {
        return getName() + "," + getProduct() + "," + getAmount();
    }
}

