package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import controllers.StaffMemberController;
import models.StaffMember;

@WebServlet("/staff")
public class StaffMemberServlet extends HttpServlet {
    private StaffMemberController staffMemberController = new StaffMemberController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "new":
                    request.getRequestDispatcher("/new-staff-form.jsp").forward(request, response);
                    break;
                case "edit":
                    int id = Integer.parseInt(request.getParameter("id"));
                    StaffMember staffMember = staffMemberController.getStaffMemberById(id);
                    request.setAttribute("staffMember", staffMember);
                    request.getRequestDispatcher("/edit-staff-form.jsp").forward(request, response);
                    break;
                case "delete":
                    int deleteId = Integer.parseInt(request.getParameter("id"));
                    staffMemberController.deleteStaffMember(deleteId);
                    response.sendRedirect("staff?action=list");
                    break;
                case "list":
                default:
                    request.setAttribute("staffMembers", staffMemberController.getAllStaffMembers());
                    request.getRequestDispatcher("/staff-list.jsp").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException("Error handling staff member actions", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "add":
                    StaffMember newStaffMember = new StaffMember(
                        0, // Assuming ID is auto-generated.
                        request.getParameter("name"),
                        request.getParameter("address"),
                        request.getParameter("phoneNumber"),
                        Integer.parseInt(request.getParameter("age")),
                        request.getParameter("gender")
                    );
                    staffMemberController.addStaffMember(newStaffMember);
                    response.sendRedirect("staff?action=list");
                    break;
                case "update":
                    StaffMember updatedStaffMember = new StaffMember(
                        Integer.parseInt(request.getParameter("id")),
                        request.getParameter("name"),
                        request.getParameter("address"),
                        request.getParameter("phoneNumber"),
                        Integer.parseInt(request.getParameter("age")),
                        request.getParameter("gender")
                    );
                    staffMemberController.updateStaffMember(updatedStaffMember);
                    response.sendRedirect("staff?action=list");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException("Error posting staff member data", e);
        }
    }
}
