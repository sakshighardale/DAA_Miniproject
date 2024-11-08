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
        List<Resource> allocatedResources = new ArrayList<>();
        List<String> unavailableResources = new ArrayList<>(); // List for unavailable resources

        // Call the greedy knapsack allocation logic
        allocatedResources = ResourceAllocator.allocateResources(capacity, availableResources);

        // Now check for needs and unavailable resources based on the requested needs
        for (String need : needs) {
            boolean allocated = false;
            System.out.println(need+" this is need");
            // Check if the requested resource is already in the allocated resources list
            for (Resource resource : allocatedResources) {
                // Check if the exact resource name matches the requested need
                if (resource.getType().equalsIgnoreCase(need)) {
                    allocated = true;
                    System.out.println(need+" is allocated "+allocated);
                    break;
                }
            }

            // If the resource wasn't allocated, add it to the unavailable resources list
            if (!allocated) {
                unavailableResources.add(need + " is unavailable.");
            }
        }

        // Set the attributes for the JSP page
        request.setAttribute("allocatedResources", allocatedResources);
        request.setAttribute("unavailableResources", unavailableResources);

        // Forward to the result page
        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }
}
