package org.uob.a2;
import org.uob.a2.engine.*;
import org.uob.a2.parser.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SimulationState state = new SimulationState();
        Engine engine = new Engine(state);
        Context ctx = new Context(engine, state);
        Parser parser = new Parser();
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to the Bakery Simulation!");
        System.out.println("Type 'help' for available commands or 'quit' to exit.\n");
        
        boolean running = true;
        while (running) {
            System.out.print("> ");
            String input = scanner.nextLine();
            
            Command command = parser.parse(input);
            
            String result = command.execute(ctx);
            System.out.println(result);
            
            if (command instanceof QuitCommand) {
                running = false;
            }
            
            System.out.println();
        }
        
        scanner.close();
        System.out.println("Simulation ended.");
    }
} 