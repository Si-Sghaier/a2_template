package org.uob.a2.engine;

import org.uob.a2.model.*;

import java.util.List;
import java.util.ArrayList;

import java.util.EnumMap;
import java.util.Map;

public class SimulationState {

    private List<Producer> producers = new ArrayList<>();
    private List<Converter> converters = new ArrayList<>();
    private List<Consumer> consumers = new ArrayList<>();
    private Map<ResourceType, Integer> inventory = new EnumMap<>(ResourceType.class);
    private List<Map<ResourceType, Integer>> resourceHistory = new ArrayList<>();

    public List<Producer> getProducers() {
        return producers;
    }

    public List<Converter> getConverters() {
        return converters;
    }

    public List<Consumer> getConsumers() {
        return consumers;
    }

    /**
     * Add the specified amount of a resource to the inventory.
     * @param resource The resource type to add
     * @param amount The amount to add
     */
    public void addResource(ResourceType resource, int amount) {
        int current = inventory.getOrDefault(resource, 0);
        inventory.put(resource, current + amount);
    }

    /**
     * Update the inventory to have exactly the specified amount of a resource.
     * @param resource The resource type to update
     * @param amount The new amount
     */
    public void updateResource(ResourceType resource, int amount) {
        inventory.put(resource, amount);
    }

    /**
     * Get the current amount of a resource in the inventory.
     * @param resource The resource type to check
     * @return The current amount, or 0 if not present
     */
    public int getResourceAmount(ResourceType resource) {
        return inventory.getOrDefault(resource, 0);
    }

    /**
     * Remove the specified amount of a resource from the inventory if sufficient.
     * @param resource The resource type to remove
     * @param amount The amount to remove
     * @return true if removal was successful, false if insufficient resources
     */
    public boolean removeResource(ResourceType resource, int amount) {
        int current = inventory.getOrDefault(resource, 0);
        if (current >= amount) {
            inventory.put(resource, current - amount);
            return true;
        }
        return false;
    }

    /**
     * Update the resource history by taking a snapshot of the current inventory.
     */
    public void updateHistory() {
        Map<ResourceType, Integer> snapshot = new EnumMap<>(inventory);
        resourceHistory.add(snapshot);
    }

    /**
     * Get the resource history.
     * @return List of resource snapshots, one per tick
     */
    public List<Map<ResourceType, Integer>> getHistory() {
        return resourceHistory;
    }
}