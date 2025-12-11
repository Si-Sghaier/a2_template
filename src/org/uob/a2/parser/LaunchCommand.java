package org.uob.a2.parser;

import org.uob.a2.engine.*;
import org.uob.a2.model.BakeryShop;
import java.util.List;

public class LaunchCommand extends Command {
    
    public LaunchCommand(List<String> words) {
        super(words);
    }

    public LaunchCommand() {
        super();
    }

    @Override
    public String execute(Context ctx) {
        if (words == null || words.isEmpty()) {
            return "Error: Please specify a bakery shop name (e.g., 'sell bakeryshop1')";
        }

        String shopName = words.get(0);
        
        BakeryShop shop = null;
        for (Consumer consumer : ctx.state().getConsumers()) {
            if (consumer instanceof BakeryShop && consumer.getName().equals(shopName)) {
                shop = (BakeryShop) consumer;
                break;
            }
        }

        if (shop == null) {
            return "Error: BakeryShop '" + shopName + "' not found.";
        }

        return shop.sell(ctx);
    }
}

