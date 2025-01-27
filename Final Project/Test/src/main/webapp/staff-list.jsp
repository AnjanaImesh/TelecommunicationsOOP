<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, models.StaffMember"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Staff Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function confirmDeletion(staffId) {
            if (confirm('Are you sure you want to delete this staff member?')) {
                window.location.href = 'staff?action=delete&id=' + staffId;
            }
        }
    </script>
</head>
<body>
    <div class="container mt-3">
        <h1>Staff List</h1>
        <a href="staff?action=new" class="btn btn-success mb-3">Add New Staff</a>
        <table class="table table-bordered table-hover">
            <thead class="table-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Address</th>
                    <th scope="col">Phone Number</th>
                    <th scope="col">Age</th>
                    <th scope="col">Gender</th>
                    <th scope="col">Actions</th>
                </tr>
            </thead>
            <tbody>
                <% 
                List<StaffMember> staffMembers = (List<StaffMember>) request.getAttribute("staffMembers");
                if (staffMembers != null) {
                    for (StaffMember staffMember : staffMembers) {
                %>
                <tr>
                    <td><%= staffMember.getStaffMemberId() %></td>
                    <td><%= staffMember.getName() %></td>
                    <td><%= staffMember.getAddress() %></td>
                    <td><%= staffMember.getPhoneNumber() %></td>
                    <td><%= staffMember.getAge() %></td>
                    <td><%= staffMember.getGender() %></td>
                    <td>
                        <a href="staff?action=edit&id=<%= staffMember.getStaffMemberId() %>" class="btn btn-primary btn-sm">Edit</a>
                        <button onclick="confirmDeletion(<%= staffMember.getStaffMemberId() %>);" class="btn btn-danger btn-sm">Delete</button>
                    </td>
                </tr>
                <% 
                    }
                } else {
                %>
                <tr>
                    <td colspan="7" class="text-center">No staff members found</td>
                </tr>
                <% 
                }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
