package com.healthcare;

import java.util.*;

public class ResourceAllocator {

    // Greedy knapsack allocation based on value-to-weight ratio
    public static List<Resource> allocateResources(int capacity, List<Resource> availableResources) {
        List<Resource> allocatedResources = new ArrayList<>();

        // Sort resources by value-to-weight ratio in descending order
        availableResources.sort((r1, r2) -> Double.compare(r2.getValueToWeightRatio(), r1.getValueToWeightRatio()));

        // Greedily allocate resources based on the knapsack logic
        for (Resource res : availableResources) {
            if (res.isAvailable() && capacity >= res.getWeight()) {
                allocatedResources.add(res);
                res.allocate(); // Mark the resource as allocated
                capacity -= res.getWeight(); // Reduce available capacity
            }
        }

        return allocatedResources;
    }
}
