package org.uob.a2.model;

import org.uob.a2.engine.*;
import org.uob.a2.model.ResourceType;

public class WheatFarm extends Producer {
    
    public WheatFarm(String name) {
        super(name, ResourceType.WHEAT, 10);
        addCost(ResourceType.CREDITS, 100);
    }

    @Override
    public void produce(Context ctx) {
        ctx.state().addResource(ResourceType.WHEAT, getAmount());
    }

    @Override
    public String toCSV() {
        return getName() + "," + getProduct() + "," + getAmount();
    }
}

