package org.uob.a2.parser;

import org.uob.a2.engine.*;
import org.uob.a2.model.ResourceType;
import java.util.List;
import java.util.Map;

public class GraphCommand extends Command {
    private static final int GRAPH_WIDTH = 60;
    
    public GraphCommand(List<String> words) {
        super(words);
    }

    public GraphCommand() {
        super();
    }

    @Override
    public String execute(Context ctx) {
        if (words == null || words.isEmpty()) {
            return "Error: Please specify a resource type (e.g., 'graph CAKE')";
        }

        String resourceName = words.get(0).toUpperCase();
        ResourceType resource;
        
        try {
            resource = ResourceType.valueOf(resourceName);
        } catch (IllegalArgumentException e) {
            return "Error: Unknown resource type '" + resourceName + "'";
        }

        List<Map<ResourceType, Integer>> history = ctx.state().getHistory();
        if (history.isEmpty()) {
            return "No history available for " + resourceName + ". Run some ticks first.";
        }

        int maxValue = 0;
        for (Map<ResourceType, Integer> snapshot : history) {
            int value = snapshot.getOrDefault(resource, 0);
            if (value > maxValue) {
                maxValue = value;
            }
        }

        if (maxValue == 0) {
            return "No data for " + resourceName + " in history.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Graph of ").append(resourceName).append(" over time:\n");
        sb.append("Max value: ").append(maxValue).append("\n\n");

        for (int i = 0; i < history.size(); i++) {
            Map<ResourceType, Integer> snapshot = history.get(i);
            int value = snapshot.getOrDefault(resource, 0);
            int barLength = maxValue > 0 ? (int) ((double) value / maxValue * GRAPH_WIDTH) : 0;
            
            sb.append(String.format("Tick %3d: ", i));
            for (int j = 0; j < barLength; j++) {
                sb.append("#");
            }
            sb.append(" ").append(value).append("\n");
        }

        return sb.toString();
    }
}

