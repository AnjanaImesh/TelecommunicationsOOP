package servlets;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import controllers.CustomerController;
import models.Customer;

@WebServlet("/customers")
public class CustomerServlet extends HttpServlet {
    private CustomerController customerController = new CustomerController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "new":
                    request.getRequestDispatcher("/new-customer-form.jsp").forward(request, response);
                    break;
                case "edit":
                    int id = Integer.parseInt(request.getParameter("id"));
                    Customer customer = customerController.getCustomerById(id);
                    request.setAttribute("customer", customer);
                    request.getRequestDispatcher("/edit-customer-form.jsp").forward(request, response);
                    break;
                case "delete":
                    int deleteId = Integer.parseInt(request.getParameter("id"));
                    customerController.deleteCustomer(deleteId);
                    response.sendRedirect("customers?action=list&message=Customer Deleted Successfully&type=success");
                    break;
                case "list":
                default:
                    request.setAttribute("customers", customerController.getAllCustomers());
                    request.getRequestDispatcher("/customer-list.jsp").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException("Error handling customer actions", e);
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
                    Customer newCustomer = new Customer(
                        0, // CustomerID is auto-generated
                        request.getParameter("name"),
                        request.getParameter("address"),
                        request.getParameter("email"),
                        request.getParameter("phoneNumber"),
                        request.getParameter("problem")
                    );
                    customerController.addCustomer(newCustomer);
                    response.sendRedirect("customers?action=list&message=New Customer Added Successfully&type=success");
                    break;
                case "update":
                    Customer updatedCustomer = new Customer(
                        Integer.parseInt(request.getParameter("id")),
                        request.getParameter("name"),
                        request.getParameter("address"),
                        request.getParameter("email"),
                        request.getParameter("phoneNumber"),
                        request.getParameter("problem")
                    );
                    customerController.updateCustomer(updatedCustomer);
                    response.sendRedirect("customers?action=list&message=Customer Updated Successfully&type=success");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException("Error posting customer data", e);
        }
    }
}
