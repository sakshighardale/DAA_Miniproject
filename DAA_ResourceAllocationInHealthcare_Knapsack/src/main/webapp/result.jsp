<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.healthcare.Resource" %>  <!-- Import the Resource class -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Resource Allocation Results</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet" />
</head>
<body>

<div class="container mt-4">
    <h2>Resource Allocation Results</h2>
    
    <div class="mt-4">
        <h4>Allocated Resources</h4>
        <ul class="list-group">
            <%-- Check if allocated resources list is empty --%>
            <%
                List<Resource> allocatedResources = (List<Resource>) request.getAttribute("allocatedResources");
                if (allocatedResources == null || allocatedResources.isEmpty()) {
            %>
                <li class="list-group-item list-group-item-danger">No resources allocated.</li>
            <%
                } else {
                    // Loop through allocated resources
                    for (Resource resource : allocatedResources) {
            %>
                        <li class="list-group-item list-group-item-success">
                            <i class="fa fa-check-circle"></i> <%= resource.getName() %> (Weight: <%= resource.getWeight() %>)
                        </li>
            <%
                    }
                }
            %>
        </ul>
    </div>

    <div class="mt-4">
        <h4>Unavailable Resources</h4>
        <ul class="list-group">
            <%-- Check if unavailable resources list is empty --%>
            <%
                List<String> unavailableResources = (List<String>) request.getAttribute("unavailableResources");
                if (unavailableResources == null || unavailableResources.isEmpty()) {
            %>
                <li class="list-group-item list-group-item-success">All requested resources are available.</li>
            <%
                } else {
                    // Loop through unavailable resources
                    for (String resource : unavailableResources) {
            %>
                        <li class="list-group-item list-group-item-warning">
                            <i class="fa fa-exclamation-circle"></i> <%= resource %>
                        </li>
            <%
                    }
                }
            %>
        </ul>
    </div>

    <div class="mt-4">
        <a href="index.html" class="btn btn-primary">Go Back</a>
    </div>

</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
</body>
</html>
