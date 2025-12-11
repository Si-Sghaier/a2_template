package org.uob.a2;
import org.uob.a2.engine.*;
import org.uob.a2.parser.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize simulation components
        SimulationState state = new SimulationState();
        Engine engine = new Engine(state);
        Context ctx = new Context(engine, state);
        Parser parser = new Parser();
        
        // Create scanner for user input
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to the Bakery Simulation!");
        System.out.println("Type 'help' for available commands or 'quit' to exit.\n");
        
        // Main game loop
        boolean running = true;
        while (running) {
            System.out.print("> ");
            String input = scanner.nextLine();
            
            // Parse command
            Command command = parser.parse(input);
            
            // Execute command
            String result = command.execute(ctx);
            System.out.println(result);
            
            // Check if we should quit
            if (command instanceof QuitCommand) {
                running = false;
            }
            
            System.out.println(); // Blank line for readability
        }
        
        scanner.close();
        System.out.println("Simulation ended.");
    }
} 