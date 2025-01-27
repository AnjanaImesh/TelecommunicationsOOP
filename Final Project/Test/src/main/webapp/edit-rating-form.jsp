<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="models.ServiceRating"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Service Rating</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header">
                <h3>Edit Service Rating</h3>
            </div>
            <div class="card-body">
                <% ServiceRating rating = (ServiceRating) request.getAttribute("rating"); %>
                <form action="ratings" method="post">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="id" value="<%= rating != null ? rating.getId() : "" %>">

                    <div class="mb-3">
                        <label for="name" class="form-label">Name</label>
                        <input type="text" class="form-control" id="name" name="name" required value="<%= rating != null ? rating.getName() : "" %>">
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" name="email" required value="<%= rating != null ? rating.getEmail() : "" %>">
                    </div>
                    <div class="mb-3">
                        <label for="experience" class="form-label">Experience</label>
                        <select class="form-control" id="experience" name="experience">
                            <option value="Excellent" <%= rating != null && rating.getExperience().equals("Excellent") ? "selected" : "" %>>Excellent</option>
                            <option value="Good" <%= rating != null && rating.getExperience().equals("Good") ? "selected" : "" %>>Good</option>
                            <option value="Average" <%= rating != null && rating.getExperience().equals("Average") ? "selected" : "" %>>Average</option>
                            <option value="Poor" <%= rating != null && rating.getExperience().equals("Poor") ? "selected" : "" %>>Poor</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="likes" class="form-label">What did you like?</label>
                        <input type="text" class="form-control" id="likes" name="likes" required value="<%= rating != null ? rating.getLikes() : "" %>">
                    </div>
                    <div class="mb-3">
                        <label for="comment" class="form-label">Additional Comments</label>
                        <textarea class="form-control" id="comment" name="comment" rows="3"><%= rating != null ? rating.getComment() : "" %></textarea>
                    </div>
                    <div class="d-grid">
                        <button type="submit" class="btn btn-primary">Update Rating</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
