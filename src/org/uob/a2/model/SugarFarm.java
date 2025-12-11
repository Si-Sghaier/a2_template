package org.uob.a2.model;

import org.uob.a2.engine.*;
import org.uob.a2.model.ResourceType;

public class SugarFarm extends Producer {
    
    public SugarFarm(String name) {
        super(name, ResourceType.SUGAR, 10);
        addCost(ResourceType.CREDITS, 100);
    }

    @Override
    public void produce(Context ctx) {
        ctx.state().addResource(ResourceType.SUGAR, getAmount());
    }

    @Override
    public String toCSV() {
        return getName() + "," + getProduct() + "," + getAmount();
    }
}

