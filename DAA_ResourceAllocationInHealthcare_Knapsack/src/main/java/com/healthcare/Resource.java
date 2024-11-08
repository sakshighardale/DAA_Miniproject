package com.healthcare;

public class Resource {
    private String name;
    private int value; // Can be used as the "value" for the knapsack
    private int weight;
    private boolean available;
    private String type;

    // Constructor
    public Resource(String name, int value, int weight, boolean available, String type) {
        this.name = name;
        this.value = value;
        this.weight = weight;
        this.available = available;
        this.type = type;
    }

    // Getters
    public String getName() { return name; }
    public int getValue() { return value; }
    public int getWeight() { return weight; }
    public boolean isAvailable() { return available; }
    public String getType() { return type; }

    // Method to mark resource as allocated (unavailable)
    public void allocate() {
        this.available = false; // Mark the resource as unavailable once allocated
    }

    // Method to calculate value-to-weight ratio
    public double getValueToWeightRatio() {
        return (double) value / weight; // Value per weight unit
    }

    @Override
    public String toString() {
        return this.name + " (Weight: " + this.weight + ", Value: " + this.value + ")";
    }
}
