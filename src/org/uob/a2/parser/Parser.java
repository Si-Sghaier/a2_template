package org.uob.a2.parser;

import org.uob.a2.*;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;


public class Parser {
    
    /**
     * Parse a command string and return the appropriate Command object.
     * Sanitizes input (trim, lowercase, handle multiple spaces).
     * Supports both long and short forms of commands.
     * @param command The command string to parse
     * @return A Command object representing the parsed command
     */
    public Command parse(String command) {
        // Sanitize input
        if (command == null) {
            command = "";
        }
        
        command = command.trim();
        
        // Default empty string to tick command
        if (command.isEmpty()) {
            return new TickCommand();
        }
        
        // Convert to lowercase and split into words
        command = command.toLowerCase();
        String[] parts = command.split("\\s+");
        List<String> words = new ArrayList<>(Arrays.asList(parts));
        
        // Remove empty strings
        words.removeIf(String::isEmpty);
        
        if (words.isEmpty()) {
            return new TickCommand();
        }
        
        String firstWord = words.get(0);
        
        // Parse command based on first word
        switch (firstWord) {
            case "tick":
            case "t":
                return new TickCommand(words.size() > 1 ? words.subList(1, words.size()) : new ArrayList<>());
            
            case "build":
            case "b":
                return new BuildCommand(words.size() > 1 ? words.subList(1, words.size()) : new ArrayList<>());
            
            case "info":
            case "i":
                return new InfoCommand(words.size() > 1 ? words.subList(1, words.size()) : new ArrayList<>());
            
            case "graph":
            case "g":
                return new GraphCommand(words.size() > 1 ? words.subList(1, words.size()) : new ArrayList<>());
            
            case "save":
            case "s":
                return new SaveCommand(words.size() > 1 ? words.subList(1, words.size()) : new ArrayList<>());
            
            case "load":
            case "l":
                return new LoadCommand(words.size() > 1 ? words.subList(1, words.size()) : new ArrayList<>());
            
            case "help":
            case "h":
                return new HelpCommand(words.size() > 1 ? words.subList(1, words.size()) : new ArrayList<>());
            
            case "quit":
            case "q":
            case "exit":
                return new QuitCommand();
            
            case "cheat":
                return new CheatCommand(words.size() > 1 ? words.subList(1, words.size()) : new ArrayList<>());
            
            case "sell":
            case "launch":
                return new LaunchCommand(words.size() > 1 ? words.subList(1, words.size()) : new ArrayList<>());
            
            default:
                return new InvalidCommand(words);
        }
    }
}