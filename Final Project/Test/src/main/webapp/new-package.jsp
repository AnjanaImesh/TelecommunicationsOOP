<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, models.Customer"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Package</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header">
                <h3>Add New Package</h3>
            </div>
            <div class="card-body">
                <form action="packages" method="post">
                    <input type="hidden" name="action" value="add">
                    
                    <div class="mb-3">
                        <label for="customerId" class="form-label">Customer</label>
                        <select class="form-control" id="customerId" name="customerId">
                            <% 
                            List<Customer> customers = (List<Customer>) request.getAttribute("customers");
                            if (customers != null) {
                                for (Customer customer : customers) {
                            %>
                            <option value="<%= customer.getCustomerId() %>"><%= customer.getName() %></option>
                            <% 
                                }
                            }
                            %>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="packageName" class="form-label">Package Name</label>
                        <input type="text" class="form-control" id="packageName" name="packageName" required>
                    </div>
                    <div class="mb-3">
                        <label for="dataLimit" class="form-label">Data Limit</label>
                        <input type="text" class="form-control" id="dataLimit" name="dataLimit" required>
                    </div>
                    <div class="mb-3">
                        <label for="price" class="form-label">Price</label>
                        <input type="number" step="0.01" class="form-control" id="price" name="price" required>
                    </div>
                    <div class="mb-3">
                        <label for="status" class="form-label">Status</label>
                        <select class="form-control" id="status" name="status">
                            <option value="Active">Active</option>
                            <option value="Inactive">Inactive</option>
                        </select>
                    </div>
                    <div class="d-grid">
                        <button type="submit" class="btn btn-primary">Add Package</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
