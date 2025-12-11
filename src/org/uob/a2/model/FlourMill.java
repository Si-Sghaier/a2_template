package org.uob.a2.model;

import org.uob.a2.engine.*;
import org.uob.a2.model.ResourceType;

public class FlourMill extends Converter {
    
    public FlourMill(String name) {
        super(name, ResourceType.WHEAT, 10, ResourceType.FLOUR, 5);
        addCost(ResourceType.CREDITS, 200);
    }

    @Override
    public void convert(Context ctx) {
        if (ctx.state().removeResource(getInput(), getInputAmount())) {
            ctx.state().addResource(getOutput(), getOutputAmount());
        }
    }

    @Override
    public String toCSV() {
        return getName() + "," + getInput() + "," + getInputAmount() + "," + 
               getOutput() + "," + getOutputAmount();
    }
}

