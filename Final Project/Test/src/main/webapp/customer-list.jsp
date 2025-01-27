<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, models.Customer" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function confirmDeletion(customerId) {
            const confirmDelete = confirm("Are you sure you want to delete this customer?");
            if (confirmDelete) {
                window.location.href = "customers?action=delete&id=" + customerId;
            }
        }
    </script>
</head>
<body>
    <div class="container mt-3">
        <h1>Customer Management</h1>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Address</th>
                    <th scope="col">Email</th>
                    <th scope="col">Phone Number</th>
                    <th scope="col">Problem</th>
                    <th scope="col">Actions</th>
                </tr>
            </thead>
            <tbody>
                <% 
                List<Customer> customers = (List<Customer>) request.getAttribute("customers");
                if (customers != null && !customers.isEmpty()) {
                    for (Customer customer : customers) {
                %>
                <tr>
                    <td><%= customer.getCustomerId() %></td>
                    <td><%= customer.getName() %></td>
                    <td><%= customer.getAddress() %></td>
                    <td><%= customer.getEmail() %></td>
                    <td><%= customer.getPhoneNumber() %></td>
                    <td><%= customer.getProblem() %></td>
                    <td>
                        <a href="customers?action=edit&id=<%= customer.getCustomerId() %>" class="btn btn-primary btn-sm">Edit</a>
                        <button onclick="confirmDeletion('<%= customer.getCustomerId() %>');" class="btn btn-danger btn-sm">Delete</button>
                    </td>
                </tr>
                <% 
                    }
                } else {
                %>
                <tr>
                    <td colspan="7" class="text-center">No customers found</td>
                </tr>
                <% 
                }
                %>
            </tbody>
        </table>
        <a href="customers?action=new" class="btn btn-success">Add New Customer</a>
    </div>
</body>
</html>
