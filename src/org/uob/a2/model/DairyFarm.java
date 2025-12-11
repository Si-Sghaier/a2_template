package org.uob.a2.model;

import org.uob.a2.engine.*;
import org.uob.a2.model.ResourceType;

public class DairyFarm extends Producer {
    
    public DairyFarm(String name) {
        super(name, ResourceType.MILK, 10);
        addCost(ResourceType.CREDITS, 150);
    }

    @Override
    public void produce(Context ctx) {
        ctx.state().addResource(ResourceType.MILK, getAmount());
    }

    @Override
    public String toCSV() {
        return getName() + "," + getProduct() + "," + getAmount();
    }
}

