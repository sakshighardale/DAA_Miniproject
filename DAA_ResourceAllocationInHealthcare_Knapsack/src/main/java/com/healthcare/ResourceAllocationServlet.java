package com.healthcare;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/allocate")
public class ResourceAllocationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the requested resources and capacity from the form
        String[] needs = request.getParameterValues("needs"); // 'needs' passed as an array from the form
        int capacity = Integer.parseInt(request.getParameter("capacity"));

        ResourceManager resourceManager = new ResourceManager();
        List<Resource> availableResources = resourceManager.getAvailableResources();
        List<Resource> filteredResources = new ArrayList<>();

        // Filter resources to match only the requested needs
        for (String need : needs) {
            for (Resource resource : availableResources) {
                if (resource.getType().equalsIgnoreCase(need) && resource.isAvailable()) {
                    filteredResources.add(resource);
                }
            }
        }

        // Allocate resources from the filtered list
        List<Resource> allocatedResources = ResourceAllocator.allocateResources(capacity, filteredResources);
        List<String> unavailableResources = new ArrayList<>();

        // Check if all requested needs are fulfilled
        for (String need : needs) {
            boolean needFulfilled = allocatedResources.stream()
                    .anyMatch(resource -> resource.getType().equalsIgnoreCase(need));
            if (!needFulfilled) {
                unavailableResources.add(need + " is unavailable.");
            }
        }

        // Set attributes for the JSP page
        request.setAttribute("allocatedResources", allocatedResources);
        request.setAttribute("unavailableResources", unavailableResources);

        // Forward to the result page
        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }
}
