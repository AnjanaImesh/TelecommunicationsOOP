<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="models.Customer"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Customer</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header">
                <h3>Edit Customer</h3>
            </div>
            <div class="card-body">
                <form action="customers" method="post">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="id" value="<%= request.getAttribute("customer") != null ? ((Customer)request.getAttribute("customer")).getCustomerId() : "" %>">

                    <div class="mb-3">
                        <label for="name" class="form-label">Name</label>
                        <input type="text" class="form-control" id="name" name="name" required value="<%= request.getAttribute("customer") != null ? ((Customer)request.getAttribute("customer")).getName() : "" %>">
                    </div>
                    <div class="mb-3">
                        <label for="address" class="form-label">Address</label>
                        <input type="text" class="form-control" id="address" name="address" required value="<%= request.getAttribute("customer") != null ? ((Customer)request.getAttribute("customer")).getAddress() : "" %>">
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" name="email" required value="<%= request.getAttribute("customer") != null ? ((Customer)request.getAttribute("customer")).getEmail() : "" %>">
                    </div>
                    <div class="mb-3">
                        <label for="phoneNumber" class="form-label">Phone Number</label>
                        <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" required value="<%= request.getAttribute("customer") != null ? ((Customer)request.getAttribute("customer")).getPhoneNumber() : "" %>">
                    </div>
                    <div class="mb-3">
                        <label for="problem" class="form-label">Problem</label>
                        <textarea class="form-control" id="problem" name="problem" rows="3"><%= request.getAttribute("customer") != null ? ((Customer)request.getAttribute("customer")).getProblem() : "" %></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Update Customer</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>