<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, models.Customer, models.Package"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Package</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header">
                <h3>Edit Package</h3>
            </div>
            <div class="card-body">
                <% Package pkg = (Package) request.getAttribute("package"); %>
                <% List<Customer> customers = (List<Customer>) request.getAttribute("customers"); %>
                <form action="packages" method="post">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="id" value="<%= pkg != null ? pkg.getPackageId() : "" %>">

                    <div class="mb-3">
                        <label for="customerId" class="form-label">Customer</label>
                        <select class="form-control" id="customerId" name="customerId">
                            <% 
                            if (customers != null) {
                                for (Customer customer : customers) {
                                    String selected = (pkg != null && customer.getCustomerId() == pkg.getCustomerId()) ? "selected" : "";
                            %>
                            <option value="<%= customer.getCustomerId() %>" <%= selected %>><%= customer.getName() %></option>
                            <% 
                                }
                            }
                            %>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="packageName" class="form-label">Package Name</label>
                        <input type="text" class="form-control" id="packageName" name="packageName" required value="<%= pkg != null ? pkg.getPackageName() : "" %>">
                    </div>
                    <div class="mb-3">
                        <label for="dataLimit" class="form-label">Data Limit</label>
                        <input type="text" class="form-control" id="dataLimit" name="dataLimit" required value="<%= pkg != null ? pkg.getDataLimit() : "" %>">
                    </div>
                    <div class="mb-3">
                        <label for="price" class="form-label">Price</label>
                        <input type="number" step="0.01" class="form-control" id="price" name="price" required value="<%= pkg != null ? pkg.getPrice() : "" %>">
                    </div>
                    <div class="mb-3">
                        <label for="status" class="form-label">Status</label>
                        <select class="form-control" id="status" name="status">
                            <option value="Active" <%= pkg != null && pkg.getStatus().equals("Active") ? "selected" : "" %>>Active</option>
                            <option value="Inactive" <%= pkg != null && pkg.getStatus().equals("Inactive") ? "selected" : "" %>>Inactive</option>
                        </select>
                    </div>
                    <div class="d-grid">
                        <button type="submit" class="btn btn-primary">Update Package</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
