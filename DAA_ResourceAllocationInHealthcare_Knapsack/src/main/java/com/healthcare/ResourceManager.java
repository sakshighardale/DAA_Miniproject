package com.healthcare;

import java.util.*;

public class ResourceManager {

    // Sample method that returns available resources
    public List<Resource> getAvailableResources() {
        List<Resource> resources = new ArrayList<>();

        // Sample resources with resource availability (using boolean flag 'available')
        resources.add(new Resource("Dr. Rahul Sharma", 100, 5, true, "doctor"));
        resources.add(new Resource("Dr. Priya Kumar", 80, 4, true, "doctor"));
        resources.add(new Resource("Bed 1", 50, 2, true, "bed"));
        resources.add(new Resource("Room 101", 70, 3, true, "room"));
        resources.add(new Resource("Room 102", 100, 5, false, "room"));
        resources.add(new Resource("Bed 2", 50, 2, false, "bed"));

        // Filter available resources (where availability is true)
        List<Resource> availableResources = new ArrayList<>();
        for (Resource resource : resources) {
            if (resource.isAvailable()) {
                availableResources.add(resource); // Add to available list
            }
        }

        return availableResources; // Return the list of available resources
    }
}
