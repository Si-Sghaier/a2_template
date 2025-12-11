package org.uob.a2.engine;

import org.uob.a2.model.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class Engine {
    private SimulationState state;
    private int currentTick = 0;

    /**
     * Constructor that initializes the engine with a simulation state.
     * Adds 1000 CREDITS to the state for initial purchases.
     * @param state The simulation state to use
     */
    public Engine(SimulationState state) {
        this.state = state;
        state.addResource(ResourceType.CREDITS, 1000);
    }

    /**
     * Get the current tick number.
     * @return The current tick number
     */
    public int getCurrentTick() {
        return currentTick;
    }

    /**
     * Advance the simulation by one tick.
     * Calls tick() on all Tickable entities and updates history.
     * @return A status string describing what happened during the tick
     */
    public String nextTick() {
        currentTick++;
        StringBuilder result = new StringBuilder();
        result.append("Tick ").append(currentTick).append(":\n");

        for (Producer producer : state.getProducers()) {
            producer.tick(new Context(this, state));
        }

        for (Converter converter : state.getConverters()) {
            converter.tick(new Context(this, state));
        }

        for (Consumer consumer : state.getConsumers()) {
            consumer.tick(new Context(this, state));
        }

        state.updateHistory();

        return result.toString();
    }

    /**
     * Save the current simulation state to a CSV file.
     * @param filename The filename to save to (will be saved in data/ directory)
     * @return A message indicating success or failure
     */
    public String save(String filename) {
        try {
            File dataDir = new File("data");
            if (!dataDir.exists()) {
                dataDir.mkdirs();
            }

            File file = new File(dataDir, filename);
            PrintWriter writer = new PrintWriter(new FileWriter(file));

            writer.println("CurrentTick," + currentTick);

            for (ResourceType resource : ResourceType.values()) {
                int amount = state.getResourceAmount(resource);
                writer.println(resource + "," + amount);
            }

            for (Producer producer : state.getProducers()) {
                writer.println("Producer," + producer.getName() + "," + 
                    producer.getClass().getSimpleName() + "," + producer.toCSV());
            }

            for (Converter converter : state.getConverters()) {
                writer.println("Converter," + converter.getName() + "," + 
                    converter.getClass().getSimpleName() + "," + converter.toCSV());
            }

            for (Consumer consumer : state.getConsumers()) {
                writer.println("Consumer," + consumer.getName() + "," + 
                    consumer.getClass().getSimpleName() + "," + consumer.toCSV());
            }

            writer.close();
            return "Game saved to " + filename;
        } catch (IOException e) {
            return "Error saving game: " + e.getMessage();
        }
    }

    /**
     * Load a simulation state from a CSV file.
     * @param filename The filename to load from (will be loaded from data/ directory)
     * @return A message indicating success or failure
     */
    public String load(String filename) {
        try {
            File file = new File("data", filename);
            if (!file.exists()) {
                return "Error: File not found: " + filename;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            // Clear existing entities
            state.getProducers().clear();
            state.getConverters().clear();
            state.getConsumers().clear();

            // Reset tick
            currentTick = 0;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 2) continue;

                if (parts[0].equals("CurrentTick")) {
                    currentTick = Integer.parseInt(parts[1]);
                } else if (parts[0].equals("Producer")) {
                    loadProducer(parts);
                } else if (parts[0].equals("Converter")) {
                    loadConverter(parts);
                } else if (parts[0].equals("Consumer")) {
                    loadConsumer(parts);
                } else {
                    // this is a resource line
                    try {
                        ResourceType resource = ResourceType.valueOf(parts[0]);
                        int amount = Integer.parseInt(parts[1]);
                        state.updateResource(resource, amount);
                    } catch (IllegalArgumentException e) {
                        // skip invalid stuff
                    }
                }
            }

            reader.close();
            return "Game loaded from " + filename;
        } catch (IOException e) {
            return "Error loading game: " + e.getMessage();
        }
    }

    /**
     * Initialize default entities for testing.
     * Creates one instance of each entity type.
     */
    public void initialiseDefaults() {
        state.getProducers().add(new org.uob.a2.model.WheatFarm("wheatfarm1"));
        state.getProducers().add(new org.uob.a2.model.SugarFarm("sugarfarm1"));
        state.getProducers().add(new org.uob.a2.model.ChickenCoop("chickencoop1"));
        state.getProducers().add(new org.uob.a2.model.DairyFarm("dairyfarm1"));
        
        state.getConverters().add(new org.uob.a2.model.FlourMill("flourmill1"));
        state.getConverters().add(new org.uob.a2.model.Oven("oven1"));
        
        state.getConsumers().add(new org.uob.a2.model.BakeryShop("bakeryshop1"));
    }

    /**
     * Load a producer from CSV parts.
     */
    private void loadProducer(String[] parts) {
        if (parts.length < 3) return;
        String name = parts[1];
        String type = parts[2];
        
        try {
            Producer producer = null;
            switch (type) {
                case "WheatFarm":
                    producer = new org.uob.a2.model.WheatFarm(name);
                    break;
                case "SugarFarm":
                    producer = new org.uob.a2.model.SugarFarm(name);
                    break;
                case "ChickenCoop":
                    producer = new org.uob.a2.model.ChickenCoop(name);
                    break;
                case "DairyFarm":
                    producer = new org.uob.a2.model.DairyFarm(name);
                    break;
            }
            if (producer != null) {
                state.getProducers().add(producer);
            }
        } catch (Exception e) {
            // skip if it's broken
        }
    }

    /**
     * Load a converter from CSV parts.
     */
    private void loadConverter(String[] parts) {
        if (parts.length < 3) return;
        String name = parts[1];
        String type = parts[2];
        
        try {
            Converter converter = null;
            switch (type) {
                case "FlourMill":
                    converter = new org.uob.a2.model.FlourMill(name);
                    break;
                case "Oven":
                    converter = new org.uob.a2.model.Oven(name);
                    break;
            }
            if (converter != null) {
                state.getConverters().add(converter);
            }
        } catch (Exception e) {
            // skip if it's broken
        }
    }

    /**
     * Load a consumer from CSV parts.
     * Format: Consumer,name,type,csv_data
     * csv_data for BakeryShop: name,resource,amount,level
     */
    private void loadConsumer(String[] parts) {
        if (parts.length < 3) return;
        String name = parts[1];
        String type = parts[2];
        
        try {
            Consumer consumer = null;
            if (type.equals("BakeryShop")) {
                org.uob.a2.model.BakeryShop shop = new org.uob.a2.model.BakeryShop(name);
                // CSV format: Consumer,name,type,name,resource,amount,level (yeah name is duplicated, idk why)
                // parts[3] = name (duplicate), parts[4] = resource, parts[5] = amount, parts[6] = level
                if (parts.length >= 7) {
                    try {
                        int level = Integer.parseInt(parts[6]);
                        shop.setLevel(level);
                    } catch (NumberFormatException e) {
                        // just use default if parsing fails
                    }
                }
                consumer = shop;
            }
            if (consumer != null) {
                state.getConsumers().add(consumer);
            }
        } catch (Exception e) {
            // skip if it's broken
        }
    }
}