package org.uob.a2.parser;

import org.uob.a2.engine.*;
import java.util.List;

public class HelpCommand extends Command {
    
    public HelpCommand(List<String> words) {
        super(words);
    }

    public HelpCommand() {
        super();
    }

    @Override
    public String execute(Context ctx) {
        if (words == null || words.isEmpty()) {
            return getGeneralHelp();
        }

        String topic = words.get(0).toLowerCase();
        return getTopicHelp(topic);
    }

    private String getGeneralHelp() {
        StringBuilder sb = new StringBuilder();
        sb.append("Available commands:\n\n");
        sb.append("  build|b <entity>     - Build a new entity (wheatfarm, sugarfarm, chickencoop, dairyfarm, flourmill, oven, bakeryshop)\n");
        sb.append("  info|i [resources|entities] - Display information about resources or entities\n");
        sb.append("  graph|g <resource>   - Display a graph of a resource over time\n");
        sb.append("  save|s <filename>    - Save the current simulation state to a file\n");
        sb.append("  load|l <filename>    - Load a simulation state from a file\n");
        sb.append("  tick|t               - Advance the simulation one tick\n");
        sb.append("  sell <shop name>     - Sell cakes from a bakery shop for credits\n");
        sb.append("  cheat                 - Add large amounts of all resources (for testing)\n");
        sb.append("  help [topic]         - Show this help or get help on a specific topic\n");
        sb.append("  quit                  - Exit the simulation\n");
        sb.append("\nType 'help <command>' for more details on a specific command.");
        return sb.toString();
    }

    private String getTopicHelp(String topic) {
        switch (topic) {
            case "build":
            case "b":
                return "build|b <entity>\n" +
                       "Build a new entity. Available entities:\n" +
                       "  - wheatfarm (costs 100 CREDITS, produces 10 WHEAT per tick)\n" +
                       "  - sugarfarm (costs 100 CREDITS, produces 10 SUGAR per tick)\n" +
                       "  - chickencoop (costs 150 CREDITS, produces 5 EGGS per tick)\n" +
                       "  - dairyfarm (costs 150 CREDITS, produces 10 MILK per tick)\n" +
                       "  - flourmill (costs 200 CREDITS, converts 10 WHEAT -> 5 FLOUR)\n" +
                       "  - oven (costs 300 CREDITS, converts FLOUR+SUGAR+EGGS+MILK -> CAKE)\n" +
                       "  - bakeryshop (costs 500 CREDITS, consumes CAKE to level up)";
            
            case "info":
            case "i":
                return "info|i [resources|entities]\n" +
                       "Display information about the simulation.\n" +
                       "  - 'info' or 'info resources' - Show all resources\n" +
                       "  - 'info entities' - Show all entities";
            
            case "graph":
            case "g":
                return "graph|g <resource>\n" +
                       "Display a text-based graph showing how a resource has changed over time.\n" +
                       "Example: 'graph CAKE'";
            
            case "save":
            case "s":
                return "save|s <filename>\n" +
                       "Save the current simulation state to a CSV file.\n" +
                       "Files are saved in the 'data' directory.\n" +
                       "Example: 'save mysave.csv'";
            
            case "load":
            case "l":
                return "load|l <filename>\n" +
                       "Load a simulation state from a CSV file.\n" +
                       "Files are loaded from the 'data' directory.\n" +
                       "Example: 'load mysave.csv'";
            
            case "tick":
            case "t":
                return "tick|t\n" +
                       "Advance the simulation by one tick.\n" +
                       "All producers produce, converters convert, and consumers consume.";
            
            case "sell":
                return "sell <shop name>\n" +
                       "Sell cakes from a bakery shop to earn credits.\n" +
                       "The credits earned depend on the shop's level.\n" +
                       "Example: 'sell bakeryshop1'";
            
            case "cheat":
                return "cheat\n" +
                       "Add 10000 of each resource to your inventory.\n" +
                       "Useful for testing.";
            
            case "quit":
                return "quit\n" +
                       "Exit the simulation.";
            
            default:
                return "Unknown topic: " + topic + "\n" + getGeneralHelp();
        }
    }
}

