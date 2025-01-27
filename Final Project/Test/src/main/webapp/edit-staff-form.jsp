<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="models.StaffMember"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Staff Member</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header">
                <h3>Edit Staff Member</h3>
            </div>
            <div class="card-body">
                <% StaffMember staffMember = (StaffMember) request.getAttribute("staffMember"); %>
                <form action="staff" method="post">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="id" value="<%= staffMember != null ? staffMember.getStaffMemberId() : "" %>">

                    <div class="mb-3">
                        <label for="name" class="form-label">Name</label>
                        <input type="text" class="form-control" id="name" name="name" required value="<%= staffMember != null ? staffMember.getName() : "" %>">
                    </div>
                    <div class="mb-3">
                        <label for="address" class="form-label">Address</label>
                        <input type="text" class="form-control" id="address" name="address" required value="<%= staffMember != null ? staffMember.getAddress() : "" %>">
                    </div>
                    <div class="mb-3">
                        <label for="phoneNumber" class="form-label">Phone Number</label>
                        <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" required value="<%= staffMember != null ? staffMember.getPhoneNumber() : "" %>">
                    </div>
                    <div class="mb-3">
                        <label for="age" class="form-label">Age</label>
                        <input type="number" class="form-control" id="age" name="age" required value="<%= staffMember != null ? staffMember.getAge() : "" %>">
                    </div>
                    <div class="mb-3">
                        <label for="gender" class="form-label">Gender</label>
                        <select class="form-control" id="gender" name="gender">
                            <option value="Male" <%= staffMember != null && staffMember.getGender().equals("Male") ? "selected" : "" %>>Male</option>
                            <option value="Female" <%= staffMember != null && staffMember.getGender().equals("Female") ? "selected" : "" %>>Female</option>
                            <option value="Other" <%= staffMember != null && staffMember.getGender().equals("Other") ? "selected" : "" %>>Other</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Update Staff Member</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
