<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, models.Package" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Package Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function showMessage(type, message) {
            const container = document.getElementById('message-container');
            container.innerHTML = `<div class='alert alert-${type} alert-dismissible fade show' role='alert'>
                                        ${message}
                                        <button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>
                                   </div>`;
            window.scrollTo(0, 0);
        }

        function confirmDeletion(packageId) {
            if (confirm("Are you sure you want to delete this package?")) {
                document.location.href = 'packages?action=delete&id=' + packageId;
            }
        }

        window.onload = function() {
            const urlParams = new URLSearchParams(window.location.search);
            const message = urlParams.get('message');
            const messageType = urlParams.get('type');
            if (message && messageType) {
                showMessage(messageType, message);
            }
        };
    </script>
</head>
<body>
    <div class="container mt-3">
        <h1>Package Management</h1>
        <div id="message-container"></div>
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Package Name</th>
                    <th scope="col">Data Limit</th>
                    <th scope="col">Price</th>
                    <th scope="col">Status</th>
                    <th scope="col">Actions</th>
                </tr>
            </thead>
            <tbody>
                <% 
                List<Package> packages = (List<Package>) request.getAttribute("packages");
                if (packages != null) {
                    for (Package pkg : packages) {
                %>
                <tr>
                    <td><%= pkg.getPackageId() %></td>
                    <td><%= pkg.getPackageName() %></td>
                    <td><%= pkg.getDataLimit() %></td>
                    <td>$<%= String.format("%.2f", pkg.getPrice()) %></td>
                    <td><%= pkg.getStatus() %></td>
                    <td>
                        <a href="packages?action=edit&id=<%= pkg.getPackageId() %>" class="btn btn-primary btn-sm">Edit</a>
                        <button onclick="confirmDeletion(<%= pkg.getPackageId() %>);" class="btn btn-danger btn-sm">Delete</button>
                    </td>
                </tr>
                <% 
                    }
                }
                %>
            </tbody>
        </table>
        <hr>
        <a href="packages?action=new">Add Pacakge</a>
    </div>
</body>
</html>
