<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, models.ServiceRating"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Service Rating List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function confirmDeletion(ratingId) {
            if (confirm('Are you sure you want to delete this rating?')) {
                window.location.href = 'ratings?action=delete&id=' + ratingId;
            }
        }
    </script>
</head>
<body>
    <div class="container mt-5">
        <h1>Service Ratings</h1>
        <a href="ratings?action=new" class="btn btn-danger btn-sm">Add Rating</a>
        <div style="height:8px"></div>
        <table class="table table-bordered table-hover">
            <thead class="table-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Email</th>
                    <th scope="col">Experience</th>
                    <th scope="col">Likes</th>
                    <th scope="col">Comment</th>
                    <th scope="col">Actions</th>
                </tr>
            </thead>
            <tbody>
                <% 
                List<ServiceRating> ratings = (List<ServiceRating>) request.getAttribute("ratings");
                if (ratings != null) {
                    for (ServiceRating rating : ratings) {
                %>
                <tr>
                    <td><%= rating.getId() %></td>
                    <td><%= rating.getName() %></td>
                    <td><%= rating.getEmail() %></td>
                    <td><%= rating.getExperience() %></td>
                    <td><%= rating.getLikes() %></td>
                    <td><%= rating.getComment() %></td>
                    <td>
                        <a href="ratings?action=edit&id=<%= rating.getId() %>" class="btn btn-primary btn-sm">Edit</a>
                        <button onclick="confirmDeletion(<%= rating.getId() %>);" class="btn btn-danger btn-sm">Delete</button>
                    </td>
                </tr>
                <% 
                    }
                } else {
                %>
                <tr>
                    <td colspan="7" class="text-center">No ratings found</td>
                </tr>
                <% 
                }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
